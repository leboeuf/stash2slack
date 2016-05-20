package com.pragbits.bitbucketserver.models;

import com.atlassian.bitbucket.nav.NavBuilder;

public class PullRequestParticipant {
    public StashUser user;
    public boolean approved;

    public PullRequestParticipant(com.atlassian.bitbucket.pull.PullRequestParticipant participant, NavBuilder navBuilder){
        user = new StashUser(participant.getUser(), navBuilder);
        approved = participant.isApproved();
    }
}
