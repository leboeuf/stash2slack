package com.pragbits.bitbucketserver.models;

import com.atlassian.bitbucket.nav.NavBuilder;
import com.atlassian.bitbucket.pull.PullRequestService;
import com.atlassian.bitbucket.repository.Repository;

import java.util.ArrayList;
import java.util.Collection;

public class PullRequest {
    public PullRequestParticipant author;
    public String title;
    public String description;
    public Collection<PullRequestParticipant> reviewers;
    public Collection<PullRequestParticipant> participants;
    public PullRequestMergeability pullRequestMergeability;

    public String url;

    public PullRequest(com.atlassian.bitbucket.pull.PullRequest pullRequest,
                       NavBuilder navBuilder,
                       PullRequestService pullRequestService){
        author = new PullRequestParticipant(pullRequest.getAuthor(), navBuilder);
        description = pullRequest.getDescription();
        title = pullRequest.getTitle();

        reviewers = new ArrayList<PullRequestParticipant>();
        for (com.atlassian.bitbucket.pull.PullRequestParticipant reviewer : pullRequest.getReviewers()) {
            reviewers.add(new PullRequestParticipant(reviewer, navBuilder));
        }

        participants = new ArrayList<PullRequestParticipant>();
        for (com.atlassian.bitbucket.pull.PullRequestParticipant participant : pullRequest.getParticipants()) {
            participants.add(new PullRequestParticipant(participant, navBuilder));
        }

        int repositoryId = pullRequest.getToRef().getRepository().getId();
        this.pullRequestMergeability = new PullRequestMergeability(pullRequestService.canMerge(repositoryId, pullRequest.getId()));

        setUrl(pullRequest, navBuilder);
    }

    private void setUrl(com.atlassian.bitbucket.pull.PullRequest pullRequest, NavBuilder navBuilder){
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
