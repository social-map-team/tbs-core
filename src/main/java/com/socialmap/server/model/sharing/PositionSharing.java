package com.socialmap.server.model.sharing;

import com.socialmap.server.model.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yy on 3/6/15.
 */
@Entity
public class PositionSharing {
    private Date endTime;
    private int  id;
    private User sponsor;
    private Date startTime;
    private Set<User> targets = new HashSet<>();

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne
    public User getSponsor() {
        return sponsor;
    }

    public void setSponsor(User sponsor) {
        this.sponsor = sponsor;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "position_sharing_id")},
            inverseJoinColumns = {@JoinColumn(name = "target_user_id")}
    )
    public Set<User> getTargets() {
        return targets;
    }

    public void setTargets(Set<User> targets) {
        this.targets = targets;
    }
}
