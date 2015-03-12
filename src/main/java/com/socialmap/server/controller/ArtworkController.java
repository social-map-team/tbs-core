package com.socialmap.server.controller;

import com.socialmap.server.model.resource.good.Artwork;
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
@RequestMapping("/api/artwork")
public class ArtworkController {
    @Autowired
    HibernateTemplate ht;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Artwork read(@PathVariable int id) {
        return ht.get(Artwork.class, id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void create(@Valid Artwork artwork) {
        ht.save(artwork);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable int id) {
        ht.update(ht.get(Artwork.class, id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        ht.delete(id);
    }
}
