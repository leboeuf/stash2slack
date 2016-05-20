package com.pragbits.bitbucketserver.models;

import com.atlassian.bitbucket.nav.NavBuilder;
import com.atlassian.bitbucket.user.UserType;

public class StashUser {
    public int id;
    public String email;
    public String name;
    public String displayName;
    public String slug;

    public String url;

    public StashUser(com.atlassian.bitbucket.user.ApplicationUser user, NavBuilder navBuilder){
        id = user.getId();
        email = user.getEmailAddress();
        name = user.getName();
        displayName = user.getDisplayName();
        slug = user.getSlug();

        setUrl(user, navBuilder);
    }

    private void setUrl(com.atlassian.bitbucket.user.ApplicationUser user, NavBuilder navBuilder){
        url = navBuilder
                .userBySlug(user.getSlug(), UserType.NORMAL)
                .buildAbsolute();
    }
}
