package com.socialmap.server.model.log;

import com.socialmap.server.model.sos.Sos;
import com.socialmap.server.model.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by yy on 3/10/15.
 */
@Entity
public class SosLog {
    private int id;
    private User caller;
    private Set<Sos> targets;
    private String reason;
    private String location;
    private Date startTime;
    private Date finishTime;
    private String result;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne
    public User getCaller() {
        return caller;
    }

    public void setCaller(User caller) {
        this.caller = caller;
    }

    @OneToMany
    public Set<Sos> getTargets() {
        return targets;
    }

    public void setTargets(Set<Sos> targets) {
        this.targets = targets;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
