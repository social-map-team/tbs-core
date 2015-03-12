package com.socialmap.server.controller;

import com.socialmap.server.model.user.User;
import com.socialmap.server.utils.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yy on 3/4/15.
 */
@RestController
@Transactional
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    HibernateTemplate ht;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User read(@PathVariable int id) {
        User u = ht.get(User.class, id);
        return u;
    }

    @RequestMapping(value = "/sss", method = RequestMethod.POST)
    public int create(@Valid User user) {
        System.out.println("!!!!!!!!!!!");
        encryptPassword(user);
        ht.save(user);
        return user.getId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable int id) {
        ht.update(ht.get(User.class, id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        ht.delete(id);
    }

    public void encryptPassword(User user){
        String s = String.format("%s:%s:%s", user.getUsername(), App.realm, user.getPassword());
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }
        String hex = new String(Hex.encode(digest.digest(s.getBytes())));
        user.setPassword(hex);
    }
}
