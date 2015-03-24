package com.socialmap.server.controller;

import com.socialmap.server.dao.UserDao;
import com.socialmap.server.model.common.Image;
import com.socialmap.server.model.user.Role;
import com.socialmap.server.model.user.User;
import com.socialmap.server.service.UserService;
import com.socialmap.server.utils.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yy on 3/6/15.
 */
@RestController
public class AccountController {

    @Autowired
    UserDao userDao;

    @Autowired
    HibernateTemplate ht;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/profile/avatar", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getAvatar() {
        User u = userService.getCurrentUser();
        System.out.println(u.getUsername());
        byte[] image = (byte[]) ht.find("select u.avatar.data from User u where u.id=?", u.getId()).get(0);
        return image;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @Transactional
    public void login() {
        User u = userService.getCurrentUser();
        u.setStatus("online");
        ht.update(u);
    }

    @RequestMapping(value = "/login", method = RequestMethod.DELETE)
    @Transactional
    public void logout() {
        User u = userService.getCurrentUser();
        u.setStatus("offline");
        ht.update(u);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @Transactional
    public void register(@Valid User user){
        //加个默认角色
        Role r = (Role)ht.find("from Role where name=?", "ROLE_USER").get(0);
        Set<Role> roles = new HashSet<Role>();
        roles.add(r);
        user.setRoles(roles);

        //设置一个默认头像
        Image avatar = (Image)ht.find("from Image i where i.remarks=?", "默认用户头像").get(0);
        user.setAvatar(avatar);

        App.encryptPassword(user);
        ht.save(user);
    }

    @RequestMapping(value = "/register", method = RequestMethod.DELETE)
    public void unregister(){
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
