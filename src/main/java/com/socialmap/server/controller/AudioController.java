package com.socialmap.server.controller;

import com.socialmap.server.model.common.Audio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Created by yy on 3/4/15.
 */
@RestController
@Transactional
public class AudioController {
    @Autowired
    HibernateTemplate ht;

    @RequestMapping(value = "/api/audio/{id}", method = RequestMethod.GET)
    public Audio read1(@PathVariable int id) {
        return ht.get(Audio.class, id);
    }

    @RequestMapping(value = "/api/audio", method = RequestMethod.POST)
    public void create1(@Valid Audio audio) {
        ht.save(audio);
    }

    @RequestMapping(value = "/api/audio/{id}", method = RequestMethod.PUT)
    public void update1(@PathVariable int id) {
        ht.update(ht.get(Audio.class, id));
    }

    @RequestMapping(value = "/api/audio/{id}", method = RequestMethod.DELETE)
    public void delete1(@PathVariable int id) {
        ht.delete(id);
    }
}
