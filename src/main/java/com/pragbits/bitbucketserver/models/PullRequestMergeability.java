package com.pragbits.bitbucketserver.models;

import java.util.ArrayList;
import java.util.Collection;

public class PullRequestMergeability {

    public Boolean canMerge;
    public Collection<PullRequestMergeVeto> vetoes;
    public Boolean isConflicted;

    public PullRequestMergeability(com.atlassian.bitbucket.pull.PullRequestMergeability pullRequestMergeability){
        this.canMerge = pullRequestMergeability.canMerge();
        vetoes = new ArrayList<PullRequestMergeVeto>();
        for (com.atlassian.bitbucket.pull.PullRequestMergeVeto pullRequestMergeVeto : pullRequestMergeability.getVetoes()) {
            vetoes.add(new PullRequestMergeVeto(pullRequestMergeVeto));
        }
        this.isConflicted = pullRequestMergeability.isConflicted();
    }
}
