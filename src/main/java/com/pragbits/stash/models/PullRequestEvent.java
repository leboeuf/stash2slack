package com.pragbits.stash.models;

import com.atlassian.stash.nav.NavBuilder;

public class PullRequestEvent {
    public String action;
    public StashUser user;
    public PullRequest pullRequest;

    public PullRequestEvent(com.atlassian.stash.event.pull.PullRequestActivityEvent event, NavBuilder navBuilder)
    {
        action = event.getAction().name();
        user = new StashUser(event.getUser(), navBuilder);
        pullRequest = new PullRequest(event.getPullRequest(), navBuilder);
    }
}
