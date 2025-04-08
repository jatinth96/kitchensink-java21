package org.jboss.as.quickstarts.kitchensink.util;

import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.jboss.as.quickstarts.kitchensink.repository.MemberRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MemberListHolder {

    private final MemberRepository memberRepository;
    private List<Member> members;

    public MemberListHolder(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        initialize();
    }

    private void initialize() {
        this.members = memberRepository.findAllOrderedByName();
    }

    public List<Member> getMembers() {
        return Collections.unmodifiableList(members);
    }

    public void refresh() {
        initialize();
    }
}
