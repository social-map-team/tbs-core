package com.socialmap.server.controller;

import com.socialmap.server.model.report.UserReport;
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
@RequestMapping("/api/report/report")
public class UserReportController {
    @Autowired
    HibernateTemplate ht;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserReport read1(@PathVariable int id) {
        return ht.get(UserReport.class, id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void create1(@Valid UserReport userReport) {
        ht.save(userReport);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update1(@PathVariable int id) {
        ht.update(ht.get(UserReport.class, id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete1(@PathVariable int id) {
        ht.delete(id);
    }
}
