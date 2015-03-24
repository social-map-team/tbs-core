package com.socialmap.server.model.report;

import com.socialmap.server.model.common.Image;
import com.socialmap.server.model.user.User;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

/**
 * Created by yy on 3/6/15.
 */
//@Entity
public class UserReport {
    private User   against;
    private User   from;
    private int    id;
    private String reason;
    private Set<Image> testifiedImages;

    @OneToOne
    public User getAgainst() {
        return against;
    }

    public void setAgainst(User against) {
        this.against = against;
    }

    @OneToOne
    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @OneToMany
    public Set<Image> getTestifiedImages() {
        return testifiedImages;
    }

    public void setTestifiedImages(Set<Image> testifiedImages) {
        this.testifiedImages = testifiedImages;
    }
}
