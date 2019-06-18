package com.sih.resthousefe.pojo;




public class Days
{
    private String runs;

    private String code;

    public String getRuns ()
    {
        return runs;
    }

    public void setRuns (String runs)
    {
        this.runs = runs;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [runs = "+runs+", code = "+code+"]";
    }
}