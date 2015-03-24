package com.socialmap.server.service;

import com.socialmap.server.model.user.MyAuthority;
import com.socialmap.server.model.user.MyUserDetails;
import com.socialmap.server.model.user.Role;
import com.socialmap.server.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by yy on 3/4/15.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    HibernateTemplate ht;

    public User getCurrentUser() {
        MyUserDetails details = (MyUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return (User) ht.find("from User where username=?", details.getUsername()).get(0);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List r = ht.find("from User where username=?", username);
        if (r.size() == 0) {
            throw new UsernameNotFoundException("用户不存在");
        } else {
            User u = (User) r.get(0);
            MyUserDetails details = new MyUserDetails(u.getUsername(), u.getPassword(), u.isEnabled());
            for (Role role : u.getRoles()) {
                details.getAuthorities().add(new MyAuthority(role.getName()));
            }
            return details;
        }
    }
}
