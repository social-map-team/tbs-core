package com.socialmap.server.controller;

import com.socialmap.server.model.resource.good.Food;
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
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    HibernateTemplate ht;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Food read(@PathVariable int id) {
        return ht.get(Food.class, id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void create(@Valid Food food) {
        ht.save(food);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable int id) {
        ht.update(ht.get(Food.class, id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        ht.delete(id);
    }
}
