package com.socialmap.server.controller;

import com.socialmap.server.model.sharing.PositionSharing;
import com.socialmap.server.model.user.User;
import com.socialmap.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by yy on 3/4/15.
 */
@RestController
public class PositionSharingController {
    @Autowired
    HibernateTemplate ht;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/share/position", method = POST)
    @Transactional
    public void toggle() {
        PositionSharing sharing = new PositionSharing();
        User user = userService.getCurrentUser();
        sharing.setSponsor(user);
        ht.save(sharing);
    }


}
