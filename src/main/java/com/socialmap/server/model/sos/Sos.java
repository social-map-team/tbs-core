package com.socialmap.server.model.sos;

import com.socialmap.server.share.SosTarget;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by yy on 3/6/15.
 */
@Entity
public class Sos {
    private int    id;
    private boolean isRegisteredUser;
    private String name;
    private String phone;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public SosTarget toSosTarget() {
        SosTarget target = new SosTarget();
        target.setName(name);
        target.setPhone(phone);
        return target;
    }
}
