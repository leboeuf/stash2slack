package com.pragbits.stash.models;

import com.atlassian.stash.nav.NavBuilder;
import com.atlassian.stash.repository.Repository;

import java.util.ArrayList;
import java.util.Collection;

public class PullRequest {
    public PullRequestParticipant author;
    public String title;
    public String description;
    public Collection<PullRequestParticipant> reviewers;
    public Collection<PullRequestParticipant> participants;

    public String url;

    public PullRequest(com.atlassian.stash.pull.PullRequest pullRequest, NavBuilder navBuilder){
        author = new PullRequestParticipant(pullRequest.getAuthor(), navBuilder);
        description = pullRequest.getDescription();
        title = pullRequest.getTitle();
        reviewers = new ArrayList<PullRequestParticipant>();
        for (com.atlassian.stash.pull.PullRequestParticipant reviewer : pullRequest.getReviewers()) {
            reviewers.add(new PullRequestParticipant(reviewer, navBuilder));
        }
        participants = new ArrayList<PullRequestParticipant>();
        for (com.atlassian.stash.pull.PullRequestParticipant participant : pullRequest.getParticipants()) {
            participants.add(new PullRequestParticipant(participant, navBuilder));
        }

        setUrl(pullRequest, navBuilder);
    }

    private void setUrl(com.atlassian.stash.pull.PullRequest pullRequest, NavBuilder navBuilder){
        Repository repository = pullRequest.getToRef().getRepository();
        String repoName = repository.getSlug();
        String projectName = repository.getProject().getKey();
        long pullRequestId = pullRequest.getId();

        url = navBuilder
                .project(projectName)
                .repo(repoName)
                .pullRequest(pullRequestId)
                .overview()
                .buildAbsolute();
    }
}
