package com.socialmap.server.controller;

import com.socialmap.server.model.resource.place.Hotel;
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
@RequestMapping("/api/hotel")
public class HotelController {
    @Autowired
    HibernateTemplate ht;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Hotel read1(@PathVariable int id) {
        return ht.get(Hotel.class, id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void create1(@Valid Hotel hotel) {
        ht.save(hotel);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update1(@PathVariable int id) {
        ht.update(ht.get(Hotel.class, id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete1(@PathVariable int id) {
        ht.delete(id);
    }
}
