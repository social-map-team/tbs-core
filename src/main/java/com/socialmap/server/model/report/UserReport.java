package com.socialmap.server.model.report;

import com.socialmap.server.model.common.Image;
import com.socialmap.server.model.common.Video;
import com.socialmap.server.model.user.User;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by yy on 3/6/15.
 */
@Entity
public class UserReport {
    private int id;
    private User from;
    private User against;
    private String reason;
    private Set<Image> testifiedImages;
    private Set<Video> testifiedVideos;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne
    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    @OneToOne
    public User getAgainst() {
        return against;
    }

    public void setAgainst(User against) {
        this.against = against;
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

    @OneToMany
    public Set<Video> getTestifiedVideos() {
        return testifiedVideos;
    }

    public void setTestifiedVideos(Set<Video> testifiedVideos) {
        this.testifiedVideos = testifiedVideos;
    }
}
