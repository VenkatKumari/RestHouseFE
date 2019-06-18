package com.sih.resthousefe;

public class Tdata {
    private String lname, address, contact;

    public Tdata() {
    }

    public Tdata(String lname) {
        this.lname = lname;

    }

    public String getName() {

        return lname;
    }

    public void setName(String lname) {
        this.lname = lname;
    }

}
