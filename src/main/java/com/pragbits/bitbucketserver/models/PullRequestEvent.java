package com.pragbits.bitbucketserver.models;

import com.atlassian.bitbucket.nav.NavBuilder;
import com.atlassian.bitbucket.pull.PullRequestService;

public class PullRequestEvent {
    public String action;
    public StashUser user;
    public PullRequest pullRequest;

    public PullRequestEvent(com.atlassian.bitbucket.event.pull.PullRequestActivityEvent event,
                            NavBuilder navBuilder,
                            PullRequestService pullRequestService)
    {
        action = event.getAction().name();
        user = new StashUser(event.getUser(), navBuilder);
        pullRequest = new PullRequest(event.getPullRequest(), navBuilder, pullRequestService);
    }
}
