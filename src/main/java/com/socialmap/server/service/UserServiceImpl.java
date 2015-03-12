package com.socialmap.server.service;

import com.socialmap.server.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yy on 3/4/15.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    HibernateTemplate ht;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List r = ht.find("from User where username=?", username);
        if (r.size() == 0) {
            throw new UsernameNotFoundException("用户不存在");
        } else {
            return (User)r.get(0);
        }
    }
}
