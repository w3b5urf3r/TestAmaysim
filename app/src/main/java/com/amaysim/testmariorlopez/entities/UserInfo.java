package com.amaysim.testmariorlopez.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mario on 19/02/2017.
 */

public class UserInfo {
    @SerializedName("data")
    private Data data;

    @SerializedName("included")
    private List<PlanItem> planItems;

    public Data getData() {
        return data;
    }

    public List<PlanItem> getPlanItems() {
        return planItems;
    }
}
