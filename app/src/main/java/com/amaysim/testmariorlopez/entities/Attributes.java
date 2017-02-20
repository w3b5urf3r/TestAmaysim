package com.amaysim.testmariorlopez.entities;

import com.google.gson.annotations.SerializedName;
import com.trivago.triava.util.UnitComponent;
import com.trivago.triava.util.UnitSystem;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by mario on 19/02/2017.
 */

public class Attributes {
    @SerializedName("title")
    private String title;

    @SerializedName("first-name")
    private String firstName;

    @SerializedName("last-name")
    private String lastName;

    @SerializedName("payment-type")
    private String paymentType;

    @SerializedName("msn")
    private String msn;

    @SerializedName("credit")
    private int credit;

    @SerializedName("credit-expiry")
    private String creditExpiryStringDate;

    @SerializedName("date-of-birth")
    private String dob;

    @SerializedName("email-address")
    private String email;

    @SerializedName("included-data-balance")
    private Long includedDataBalance;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private Double price;

    @SerializedName("data-usage-threshold")
    private Boolean dataTreshhold;

    @SerializedName("unlimited-text")
    private String unlimitedText;

    @SerializedName("next-billing-date")
    private String nextBillingDate;

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getMsn() {
        return msn;
    }

    public int getCredit() {
        return credit;
    }

    public String getCreditExpiryStringDate() {
        return creditExpiryStringDate;
    }

    public String getDob() {
        return dob;
    }

    public String getFirstNAme() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public Long getIncludedDataBalance() {
        return includedDataBalance;
    }

    public String getIncludedDataBalanceFormatted() {
        //i prefer to use this library instead of doing something like
        //((float) Math.round((sizeInBytes / (1024 * 1024 * 1024)) * 10) / 10)
        //includedDataBalance is expressed in megabyte, Unitcomponent need bytes
        UnitComponent uc = new UnitComponent(includedDataBalance * 1024 * 1024, UnitSystem.IEC);
        return uc.giga() + "GB"; //not considering localisation for GB
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getPriceFormatted() {
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
        return n.format(price / 100.0);
    }

    public Boolean getDataTreshhold() {
        return dataTreshhold;
    }

    public String getUnlimitedText() {
        return unlimitedText;
    }

    public String getNextBillingDate() {
        return nextBillingDate;
    }
}