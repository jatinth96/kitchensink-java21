/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.kitchensink.service;

import org.jboss.as.quickstarts.kitchensink.event.MemberCreatedEvent;
import org.jboss.as.quickstarts.kitchensink.model.Member;

import java.util.List;
import org.jboss.as.quickstarts.kitchensink.repository.MemberRepository;
import org.jboss.as.quickstarts.kitchensink.util.MemberListHolder;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Service
public class MemberRegistration {

    private final MemberRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    public MemberRegistration(MemberRepository repository, MemberListHolder memberListHolder, ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    @Transactional
    public Member registerMember(Member member) {
        // Business rule validation
        if (repository.existsByEmail(member.email())) {
            throw new IllegalStateException("Email already registered");
        }

        // Phone number uniqueness check
        if (repository.existsByPhoneNumber(member.phoneNumber())) {
            throw new IllegalStateException("Phone number already in use");
        }

        Member savedMember = repository.save(member);
        eventPublisher.publishEvent(new MemberCreatedEvent(savedMember));
        return savedMember;
    }
}
