package com.pragbits.stash.models;

import java.util.ArrayList;
import java.util.Collection;

public class PullRequestMergeability {

    public Boolean canMerge;
    public Collection<PullRequestMergeVeto> vetoes;
    public Boolean isConflicted;

    public PullRequestMergeability(com.atlassian.stash.pull.PullRequestMergeability pullRequestMergeability){
        this.canMerge = pullRequestMergeability.canMerge();
        vetoes = new ArrayList<PullRequestMergeVeto>();
        for (com.atlassian.stash.pull.PullRequestMergeVeto pullRequestMergeVeto : pullRequestMergeability.getVetos()) {
            vetoes.add(new PullRequestMergeVeto(pullRequestMergeVeto));
        }
        this.isConflicted = pullRequestMergeability.isConflicted();
    }
}
