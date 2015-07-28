package com.pragbits.stash.models;

public class PullRequestMergeVeto {

    public String detailedMessage;
    public String summaryMessage;

    public PullRequestMergeVeto(com.atlassian.stash.pull.PullRequestMergeVeto pullRequestMergeVeto){
        this.detailedMessage = pullRequestMergeVeto.getDetailedMessage();
        this.summaryMessage = pullRequestMergeVeto.getSummaryMessage();
    }
}
