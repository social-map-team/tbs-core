package com.socialmap.server.controller;

import com.socialmap.server.ConsoleConnector;
import com.socialmap.server.model.sos.Sos;
import com.socialmap.server.model.user.User;
import com.socialmap.server.service.UserService;
import com.socialmap.server.share.SosCaller;
import com.socialmap.server.share.SosRequest;
import com.socialmap.server.share.SosTarget;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by yy on 3/4/15.
 */
@RestController
public class SosController {
    @Autowired
    HibernateTemplate ht;

    @Autowired
    SessionFactory sf;

    @Autowired
    ConsoleConnector console;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/sos/contact", method = POST)
    @Transactional
    public void addSosContact(@Valid Sos sos, @RequestParam String relationship) {
        // 获取当前用户
        User user = userService.getCurrentUser();
        // 添加新的sos联系人
        user.getSosContact().put(relationship, sos);
        // 保存
        ht.saveOrUpdate(user);
    }

    @RequestMapping(value = "/sos/contact/{id}", method = PUT)
    @Transactional
    public void addSosContact(@Valid Sos sos, @PathVariable int id) {
        // 获取当前用户
        User user = userService.getCurrentUser();
        Session session = sf.getCurrentSession();
        String hql = String.format("select s.id from User u, u.sosContact s where u.id=%d and s.id=%d", user.getId(), id);
        int sid = (int) session.createQuery(hql).uniqueResult();
        sos.setId(sid);
        session.update(sid);
    }

    @RequestMapping(value = "/sos/confirm", method = POST)
    public void confirmSos() {

    }

    @RequestMapping(value = "/api/sos", method = POST)
    @Transactional
    public void create2(@Valid Sos sos) {
        ht.save(sos);
    }

    @RequestMapping(value = "/api/sos/{id}", method = DELETE)
    @Transactional
    public void delete2(@PathVariable int id) {
        ht.delete(id);
    }

    @RequestMapping(value = "/sos/contact", method = GET)
    @Transactional
    public List getSosContact() {
        // 获取当前用户
        User user = userService.getCurrentUser();
        // 获取当前用户所有的sos联系人
        List<Sos> soses = (List<Sos>) ht.find("select u.sosContact from User u where u.id=?", user.getId());
        return soses;
    }

    @RequestMapping(value = "/sos", method = {POST, GET})
    @Transactional
    public boolean publishAllSos(){
        User user = userService.getCurrentUser();
        SosCaller caller = new SosCaller();
        caller.setId(user.getId());
        caller.setPhone(user.getPhone());
        caller.setRealname(user.getRealname());
        caller.setUsername(user.getUsername());
        List<Sos> soses = (List<Sos>) ht.find("select u.sosContact from User u where u.id=?", user.getId());
        //Set<Sos> soses = user.getSoses();
        SosRequest request = new SosRequest();
        request.setCaller(caller);
        request.setReason("有大老虎");
        Set<SosTarget> targets = new HashSet<SosTarget>();
        for(Sos s: soses){
            targets.add(s.toSosTarget());
        }
        request.setTargets(targets);
        request.getCaller().setPosition("123:321:444");
        return console.publishSos(request);
    }

    @RequestMapping(value = "/api/sos/{id}", method = GET)
    public Sos read2(@PathVariable int id) {
        return ht.get(Sos.class, id);
    }

    @RequestMapping(value = "/api/sos/{id}", method = PUT)
    @Transactional
    public void update2(@PathVariable int id) {
        ht.update(ht.get(Sos.class, id));
    }

}
