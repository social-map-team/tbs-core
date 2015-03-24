package com.socialmap.server.model.resource;

import com.socialmap.server.model.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by yy on 3/6/15.
 */
@Entity
public class Team {
    private int       assemblyCount;
    private Date      endTime;
    private int       id;
    private Set<User> leaders;
    private String    level;
    private Set<User> members;
    private String    name;
    private float     price;
    private String    remarks;
    private String    requirements;
    private int       scheduleChangeCount;
    private Set<Schedule> schedules;
    private int       sosCount;
    private User      sponsor; //发起人
    private Date      startTime;
    private String    status;

    public int getAssemblyCount() {
        return assemblyCount;
    }

    public void setAssemblyCount(int assemblyCount) {
        this.assemblyCount = assemblyCount;
    }

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

    @OneToMany
    public Set<User> getLeaders() {
        return leaders;
    }

    public void setLeaders(Set<User> leaders) {
        this.leaders = leaders;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @OneToMany
    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public int getScheduleChangeCount() {
        return scheduleChangeCount;
    }

    public void setScheduleChangeCount(int scheduleChangeCount) {
        this.scheduleChangeCount = scheduleChangeCount;
    }

    @OneToMany
    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    public int getSosCount() {
        return sosCount;
    }

    public void setSosCount(int sosCount) {
        this.sosCount = sosCount;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
