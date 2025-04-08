package org.jboss.as.quickstarts.kitchensink.event;

import org.jboss.as.quickstarts.kitchensink.model.Member;

public class MemberCreatedEvent extends MemberEvent {
    public MemberCreatedEvent(Member member) {
        super(member);
    }
}
