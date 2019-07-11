package com.jacky.innovex;

import org.parceler.Parcel;

@Parcel

public class UserData {
    private String principal;
    private String rate;
    private String months;
    private String lowerLimit;
    private String highestLimit;


    public UserData(String principal, String rate, String months, String lowerLimit, String highestLimit) {
        this.principal = principal;
        this.months = months;
        this.rate = rate;
        this.lowerLimit = lowerLimit;
        this.highestLimit = highestLimit;
    }

    public UserData(){}

    public String getPrincipal() {
        return principal;
    }

    public String  getMonths() {
        return months;
    }

    public String getRate() {
        return rate;
    }

    public String getLowerLimit() {
        return lowerLimit;
    }

    public String getHighestLimit() {
        return highestLimit;
    }


}
