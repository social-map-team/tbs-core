package com.socialmap.server.model.user;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * Created by yy on 3/6/15.
 */
@Entity
public class Role implements GrantedAuthority {
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }

    @Transient
    public String getAuthority() {
        return name;
    }

    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
