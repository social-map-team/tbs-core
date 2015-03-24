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
    private User   caller;
    private Date   finishTime;
    private int    id;
    private String location;
    private String reason;
    private String result;
    private Date   startTime;
    private Set<Sos> targets;

    @OneToOne
    public User getCaller() {
        return caller;
    }

    public void setCaller(User caller) {
        this.caller = caller;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @OneToMany
    public Set<Sos> getTargets() {
        return targets;
    }

    public void setTargets(Set<Sos> targets) {
        this.targets = targets;
    }
}
