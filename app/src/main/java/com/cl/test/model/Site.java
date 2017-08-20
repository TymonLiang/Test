package com.cl.test.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ｃ.l on 17/08/2017
 * site bean
 */

public class Site {
    @SerializedName("id")
    private Integer siteId;

    private String name;

    private FromCentral fromcentral;

    private Location location;

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FromCentral getFromcentral() {
        return fromcentral;
    }

    public void setFromcentral(FromCentral fromcentral) {
        this.fromcentral = fromcentral;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

