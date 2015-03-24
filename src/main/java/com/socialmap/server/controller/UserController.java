package com.socialmap.server.controller;

import com.socialmap.server.model.sharing.PositionSharing;
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
import java.util.List;

/**
 * Created by yy on 3/4/15.
 */
@RestController
@Transactional
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    HibernateTemplate ht;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public int create(@Valid User user) {
        System.out.println("!!!!!!!!!!!");
        encryptPassword(user);
        ht.save(user);
        return user.getId();
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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        ht.delete(id);
    }

    @RequestMapping(value = "/{id}/position", method = RequestMethod.GET)
    public String getPosition(@PathVariable int id) {
        //User current = App.currentUser();
        User u = ht.get(User.class, id);
        List<PositionSharing> sharings = (List<PositionSharing>) ht.find("from PositionSharing ps where ps.sponsor.id = ?", u.getId());
        for (PositionSharing ps : sharings) {
            if (ps.getTargets().size() == 0) {
                // TODO 存在目标是所有人的分享
            }
        }
        return "";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User read(@PathVariable int id) {
        User u = ht.get(User.class, id);
        return u;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable int id) {
        ht.update(ht.get(User.class, id));
    }
}
