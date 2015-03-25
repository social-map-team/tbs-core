package com.socialmap.server.model.user;

import javax.persistence.Embeddable;

/**
 * Created by yy on 3/18/15.
 */
@Embeddable
public class Position {
    private float lat; //纬度
    private float lng; //经度
    private float radius; //误差范围，单位：米

    public Position() {

    }

    /**
     * @param p 格式是“经度:纬度:半径”
     */
    public Position(String p) {
        String[] parts = p.split(":");
        lng = Float.parseFloat(parts[0]);
        lat = Float.parseFloat(parts[1]);
        radius = Float.parseFloat(parts[2]);
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
