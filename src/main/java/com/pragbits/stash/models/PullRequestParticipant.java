package com.pragbits.stash.models;

import com.atlassian.stash.nav.NavBuilder;

public class PullRequestParticipant {
    public StashUser user;
    public boolean approved;

    public PullRequestParticipant(com.atlassian.stash.pull.PullRequestParticipant participant, NavBuilder navBuilder){
        user = new StashUser(participant.getUser(), navBuilder);
        approved = participant.isApproved();
    }
}
