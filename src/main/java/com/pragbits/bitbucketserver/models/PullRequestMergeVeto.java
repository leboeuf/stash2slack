package com.pragbits.bitbucketserver.models;

public class PullRequestMergeVeto {

    public String detailedMessage;
    public String summaryMessage;

    public PullRequestMergeVeto(com.atlassian.bitbucket.pull.PullRequestMergeVeto pullRequestMergeVeto){
        this.detailedMessage = pullRequestMergeVeto.getDetailedMessage();
        this.summaryMessage = pullRequestMergeVeto.getSummaryMessage();
    }
}
