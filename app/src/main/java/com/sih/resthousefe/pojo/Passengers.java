package com.sih.resthousefe.pojo;



public class Passengers
{
    private String no;

    private String booking_status;

    private String current_status;

    public String getNo ()
    {
        return no;
    }

    public void setNo (String no)
    {
        this.no = no;
    }

    public String getBooking_status ()
    {
        return booking_status;
    }

    public void setBooking_status (String booking_status)
    {
        this.booking_status = booking_status;
    }

    public String getCurrent_status ()
    {
        return current_status;
    }

    public void setCurrent_status (String current_status)
    {
        this.current_status = current_status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [no = "+no+", booking_status = "+booking_status+", current_status = "+current_status+"]";
    }
}
