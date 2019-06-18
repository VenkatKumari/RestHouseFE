package com.sih.resthousefe;

public class Passengers {
    private String sno,roomno,passname;

    public Passengers() {
    }

    public Passengers(String sno, String roomno, String passname) {
        this.sno = sno;
        this.roomno = roomno;
        this.passname = passname;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno= roomno;
    }

    public String getPassname (){
        return passname;
    }

    public void setPassname(String passname) {
        this.passname= passname;
    }
}

