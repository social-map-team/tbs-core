package com.socialmap.server.dao;

import com.socialmap.server.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by yy on 3/4/15.
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @Autowired
    HibernateTemplate ht;

    @Override
    public User findUserByUsername(String username) {
        List r = ht.find("from users where username=?", username);
        if (r.size() == 0) throw new RuntimeException(username + "用户不存在");
        return (User) r.get(0);
    }

    @Override
    public void saveOrUpdate(User u) {
        ht.saveOrUpdate(u);
    }

    @Override
    public void delete(User u) {
        ht.delete(u);
    }
}
