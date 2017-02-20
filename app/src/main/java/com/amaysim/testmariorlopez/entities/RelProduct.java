package com.amaysim.testmariorlopez.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mario on 19/02/2017.
 */

class RelProduct {
    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }
}
