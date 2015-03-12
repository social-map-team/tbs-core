package com.socialmap.server.model.resource.good;

import com.socialmap.server.model.social.Comment;
import com.socialmap.server.model.resource.place.Commerce;
import com.socialmap.server.model.common.Image;
import com.socialmap.server.model.common.Video;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Created by yy on 3/6/15.
 */
@Entity
public class Food {
    private int id;
    private String name;
    private String type;
    private float price;
    private String description;
    private Set<Image> images;
    private Set<Video> videos;
    private Set<Comment> comments;
    private Set<Commerce> vendors;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @OneToMany
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @OneToMany
    public Set<Commerce> getVendors() {
        return vendors;
    }

    public void setVendors(Set<Commerce> vendors) {
        this.vendors = vendors;
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
