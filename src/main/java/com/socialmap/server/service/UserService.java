package com.socialmap.server.service;

import com.socialmap.server.model.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by yy on 3/4/15.
 */
public interface UserService extends UserDetailsService {
    public User getCurrentUser();
}
