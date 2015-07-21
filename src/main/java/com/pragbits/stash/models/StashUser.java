package com.pragbits.stash.models;

import com.atlassian.stash.nav.NavBuilder;

public class StashUser {
    public int id;
    public String email;
    public String name;
    public String displayName;
    public String slug;

    public String url;

    public StashUser(com.atlassian.stash.user.StashUser user, NavBuilder navBuilder){
        id = user.getId();
        email = user.getEmailAddress();
        name = user.getName();
        displayName = user.getDisplayName();
        slug = user.getSlug();

        setUrl(user, navBuilder);
    }

    private void setUrl(com.atlassian.stash.user.StashUser user, NavBuilder navBuilder){
        url = navBuilder
                .userBySlug(user.getSlug())
                .buildAbsolute();
    }
}
