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
package org.jboss.as.quickstarts.kitchensink.model;

import jakarta.validation.constraints.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "members")
public class Member{
        @Id String id;

        @Field(name = "name")
        @NotBlank(message = "Name cannot be empty")
        @Size(min = 2, max = 50, message = "Name must be 2-50 characters")
        String name;

        @Email(message = "Invalid email format")
        @NotBlank(message = "Email cannot be empty")
        @Field(name = "email")
        String email;

        @Pattern(regexp = "^[6-9]\\d{9}$", message = "Phone must be 10 digits")
        @Field(name = "phone_number")
        String phoneNumber;

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPhoneNumber() {
                return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public Member(String id, String name, String email, String phoneNumber) {
                this.id = id;
                this.name = name;
                this.email = email;
                this.phoneNumber = phoneNumber;
        }

        public Member() {
        }
}