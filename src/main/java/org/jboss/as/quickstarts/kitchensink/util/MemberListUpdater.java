package org.jboss.as.quickstarts.kitchensink.util;

import org.jboss.as.quickstarts.kitchensink.event.MemberCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MemberListUpdater {

    private final MemberListHolder memberListHolder;

    public MemberListUpdater(MemberListHolder memberListHolder) {
        this.memberListHolder = memberListHolder;
    }

    @EventListener
    public void handleMemberChanged(MemberCreatedEvent event) {
        memberListHolder.refresh();
    }
}
