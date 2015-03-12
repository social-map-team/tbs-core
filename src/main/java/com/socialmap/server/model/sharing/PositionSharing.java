package com.socialmap.server.model.sharing;

import com.socialmap.server.model.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by yy on 3/6/15.
 */
@Entity
public class PositionSharing {
    private int id;
    private String name;
    private String type;
    private User sponsor;
    private Set<User> targets;
    private Date startTime;
    private Date endTime;
    private String rendezvous;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @OneToOne
    public User getSponsor() {
        return sponsor;
    }

    public void setSponsor(User sponsor) {
        this.sponsor = sponsor;
    }

    @OneToMany
    public Set<User> getTargets() {
        return targets;
    }

    public void setTargets(Set<User> targets) {
        this.targets = targets;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRendezvous() {
        return rendezvous;
    }

    public void setRendezvous(String rendezvous) {
        this.rendezvous = rendezvous;
    }
}
