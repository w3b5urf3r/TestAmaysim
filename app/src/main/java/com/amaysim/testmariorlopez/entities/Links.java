package com.amaysim.testmariorlopez.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mario on 19/02/2017.
 */

public class Links {

    @SerializedName("related")
    private String related;

    public String getRelated() {
        return related;
    }
}