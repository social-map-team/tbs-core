package com.socialmap.server.model.user;

import javax.persistence.Embeddable;

/**
 * Created by yy on 3/18/15.
 */
@Embeddable
public class Position {
    private float lat; //纬度
    private float lng; //经度
    private float range; //误差范围，单位：米

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

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }
}
