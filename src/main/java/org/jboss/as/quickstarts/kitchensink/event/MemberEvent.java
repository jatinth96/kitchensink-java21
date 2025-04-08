package org.jboss.as.quickstarts.kitchensink.event;

import org.jboss.as.quickstarts.kitchensink.model.Member;

public abstract class MemberEvent {
    private final Member member;

    protected MemberEvent(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return member;
    }
}
