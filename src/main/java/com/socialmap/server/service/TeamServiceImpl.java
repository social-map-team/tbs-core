package com.socialmap.server.service;

import com.socialmap.server.model.resource.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yy on 3/4/15.
 */
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    HibernateTemplate ht;

    @Override
    public Team findTeamById(int id) {
        List r = ht.find("from Team where id = ?", id);
        if (r.size() == 0) {
            throw new TeamNotFoundException("团队ID：" + id);
        }
        return (Team) r.get(0);
    }
}
