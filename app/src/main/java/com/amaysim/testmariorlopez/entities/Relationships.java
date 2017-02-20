package com.amaysim.testmariorlopez.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mario on 19/02/2017.
 */

public class Relationships {
    @SerializedName("service")
    RelService relService;

    @SerializedName("product")
    private RelProduct relProduct;

}
