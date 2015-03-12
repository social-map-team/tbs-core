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
    private int id;
    private String name;
    private Set<Schedule> schedules;
    private float price;
    private String status;
    private Date startTime;
    private Date endTime;
    private Set<User> members;
    private Set<User> leaders;
    private User sponsor; //发起人
    private String remarks;
    private String requirements;
    private String level;
    private int sosCount;
    private int assemblyCount;
    private int scheduleChangeCount;

    @OneToMany
    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    @OneToMany
    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    @OneToMany
    public Set<User> getLeaders() {
        return leaders;
    }

    public void setLeaders(Set<User> leaders) {
        this.leaders = leaders;
    }

    @OneToOne
    public User getSponsor() {
        return sponsor;
    }

    public void setSponsor(User sponsor) {
        this.sponsor = sponsor;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getSosCount() {
        return sosCount;
    }

    public void setSosCount(int sosCount) {
        this.sosCount = sosCount;
    }

    public int getAssemblyCount() {
        return assemblyCount;
    }

    public void setAssemblyCount(int assemblyCount) {
        this.assemblyCount = assemblyCount;
    }

    public int getScheduleChangeCount() {
        return scheduleChangeCount;
    }

    public void setScheduleChangeCount(int scheduleChangeCount) {
        this.scheduleChangeCount = scheduleChangeCount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
