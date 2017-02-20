package com.amaysim.testmariorlopez.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mario on 19/02/2017.
 */

public class Data {

    @SerializedName("type")
    private String type;

    @SerializedName("id")
    private String id;

    @SerializedName("attributes")
    private Attributes attributes;

    public Attributes getAttributes() {
        return attributes;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}
