package com.socialmap.server.model.resource.place;

import com.socialmap.server.model.resource.good.Artwork;
import com.socialmap.server.model.common.Image;
import com.socialmap.server.model.common.Video;
import com.socialmap.server.model.resource.good.Food;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Created by yy on 3/6/15.
 */
@Entity
public class Commerce {
    private int id;
    private String name;
    private int affiliated; // 加盟级别，默认是0，未加盟，驿站大于0
    private String description;
    private Set<Image> images;
    private Set<Video> videos;
    private Set<Artwork> artworks;
    private Set<Food> foods;

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
    public Set<Artwork> getArtworks() {
        return artworks;
    }

    public void setArtworks(Set<Artwork> artworks) {
        this.artworks = artworks;
    }

    @OneToMany
    public Set<Food> getFoods() {
        return foods;
    }

    public void setFoods(Set<Food> foods) {
        this.foods = foods;
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

    public int getAffiliated() {
        return affiliated;
    }

    public void setAffiliated(int affiliated) {
        this.affiliated = affiliated;
    }
}
