package com.sih.resthousefe.pojo;


public class Train
{
    private Classes[] classes;

    private Days[] days;

    private String name;

    private String number;

    public Classes[] getClasses ()
    {
        return classes;
    }

    public void setClasses (Classes[] classes)
    {
        this.classes = classes;
    }

    public Days[] getDays ()
    {
        return days;
    }

    public void setDays (Days[] days)
    {
        this.days = days;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getNumber ()
    {
        return number;
    }

    public void setNumber (String number)
    {
        this.number = number;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [classes = "+classes+", days = "+days+", name = "+name+", number = "+number+"]";
    }
}
