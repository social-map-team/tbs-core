package com.socialmap.server.model.user;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by yy on 3/22/15.
 */
public class MyAuthority implements GrantedAuthority {
    private String name;

    public MyAuthority(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
