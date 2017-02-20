package com.amaysim.testmariorlopez.entities;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mario on 19/02/2017.
 */

public class PlanItem {

    public static String SUBSCRIPTION_KEY = "subscriptions";
    public static String SERVICES_KEY = "services";
    public static String PRODUCTS_KEY = "products";

    @SerializedName("type")
    private String type;

    @SerializedName("id")
    private String id;

    @SerializedName("attributes")
    private Attributes attributes;

    @SerializedName("relationships")
    private Relationships relationships;

    public static PlanItem getPlan(List<PlanItem> plans, String keyPlan) {
        if (plans == null) {
            return null;
        }
        for (PlanItem plan : plans) {
            if (TextUtils.equals(plan.type, keyPlan)) {
                return plan;
            }
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public Relationships getRelationships() {
        return relationships;
    }
}
