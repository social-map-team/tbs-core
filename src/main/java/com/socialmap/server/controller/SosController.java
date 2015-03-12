package com.socialmap.server.controller;

import com.socialmap.server.ConsoleConnector;
import com.socialmap.server.model.sos.Sos;
import com.socialmap.server.model.user.User;
import com.socialmap.server.share.SosCaller;
import com.socialmap.server.share.SosRequest;
import com.socialmap.server.share.SosTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yy on 3/4/15.
 */
@RestController
public class SosController {
    @Autowired
    HibernateTemplate ht;

    @Autowired
    ConsoleConnector console;

    @RequestMapping(value = "/api/sos/{id}", method = RequestMethod.GET)
    public Sos read2(@PathVariable int id) {
        return ht.get(Sos.class, id);
    }

    @RequestMapping(value = "/api/sos", method = RequestMethod.POST)
    @Transactional
    public void create2(@Valid Sos sos) {
        ht.save(sos);
    }

    @RequestMapping(value = "/api/sos/{id}", method = RequestMethod.PUT)
    @Transactional
    public void update2(@PathVariable int id) {
        ht.update(ht.get(Sos.class, id));
    }

    @RequestMapping(value = "/api/sos/{id}", method = RequestMethod.DELETE)
    @Transactional
    public void delete2(@PathVariable int id) {
        ht.delete(id);
    }

    @RequestMapping(value = "/sos", method = {RequestMethod.POST, RequestMethod.GET})
    @Transactional
    public boolean publishAllSos(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SosCaller caller = new SosCaller();
        caller.setId(user.getId());
        caller.setPhone(user.getPhone());
        caller.setRealname(user.getRealname());
        caller.setUsername(user.getUsername());
        List<Sos> soses = (List<Sos>)ht.find("select u.soses from User u where u.id=?", user.getId());
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

    @RequestMapping(value = "/sos/confirm", method = RequestMethod.POST)
    public void confirmSos(){

    }


}
