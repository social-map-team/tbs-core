package com.socialmap.server.model.social;

import com.socialmap.server.model.common.Image;
import com.socialmap.server.model.common.Video;
import com.socialmap.server.model.user.User;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by yy on 3/6/15.
 */
@Entity
public class Comment {
    private int id;
    private String text;
    private Set<Image> images;
    private Set<Video> videos;
    private User creator;
    private String target;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @OneToMany
    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    @OneToMany
    public Set<Video> getVideos() {
        return videos;
    }

    public void setVideos(Set<Video> videos) {
        this.videos = videos;
    }

    @OneToOne
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
