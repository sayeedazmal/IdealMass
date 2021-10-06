package com.example.idealhome.Pojo;

import android.widget.Spinner;

import java.util.List;

public class MillInfo {

    private String year;
    private String months;
    private  String days;
    private String millCount;
    private List<User> username;

    public MillInfo(String year, String months, String days, String millCount, List<User> username) {
        this.year = year;
        this.months = months;
        this.days = days;
        this.millCount = millCount;
        this.username = username;
    }


    public List<User> getUsername() {
        return username;
    }

    public void setUsername(List<User> username) {
        this.username = username;
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getMillCount() {
        return millCount;
    }

    public void setMillCount(String millCount) {
        this.millCount = millCount;
    }
}
