package com.socialmap.server.dao;

import com.socialmap.server.model.user.User;

/**
 * Created by yy on 3/4/15.
 */
public interface UserDao {
    public User findUserByUsername(String username);
    public void saveOrUpdate(User u);
    public void delete(User u);
}
