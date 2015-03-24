package com.socialmap.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by yy on 3/6/15.
 */
@Component
@Transactional
public class ContextRefreshListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    HibernateTemplate ht;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        /*Role rUser = new Role("ROLE_USER");
        ht.save(rUser);
        Role rAdmin = new Role("ROLE_ADMIN");
        ht.save(rAdmin);

        Sos s1 = new Sos();
        s1.setPhone("11111111111");
        s1.setName("李四");
        ht.save(s1);
        Sos s2 = new Sos();
        s2.setPhone("22222222222");
        s2.setName("王五");
        ht.save(s2);

        // 添加头像
        Image avatar = new Image();
        avatar.setRemarks("默认用户头像");
        try {
            avatar.setData(IOUtils.toByteArray(getClass().getResourceAsStream("/exciting.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ht.save(avatar);

        User u1 = new User();
        u1.setUsername("12345678901");
        u1.setPassword("123");
        u1.setRealname("张三");
        u1.setPhone("12345678901");
        u1.setEnabled(true);
        u1.getRoles().add(rUser);
        u1.getRoles().add(rAdmin);
        // TODO u1.getSoses().put("朋友", s1);
        //u1.getSoses().put("父亲", s2);
        u1.setAvatar(avatar);
        App.encryptPassword(u1);
        ht.save(u1);

        User u2 = new User();
        u2.setUsername("12345678902");
        u2.setPassword("123");
        u2.setRealname("李四");
        u2.setPhone("12345678902");
        u2.setEnabled(true);
        u2.getRoles().add(rUser);
        u2.setAvatar(avatar);
        App.encryptPassword(u2);
        ht.save(u2);*/
    }
}
