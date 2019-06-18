package com.sih.resthousefe.pojo;


public class Station
{
    private String chart_prepared;

    private String response_code;

    private String pnr;

    private String total_passengers;

    private To_station to_station;

    private Passengers[] passengers;

    private Reservation_upto reservation_upto;

    private From_station from_station;

    private Train train;

    private String debit;

    private Boarding_point boarding_point;

    private String doj;

    private Journey_class journey_class;

    public String getChart_prepared ()
    {
        return chart_prepared;
    }

    public void setChart_prepared (String chart_prepared)
    {
        this.chart_prepared = chart_prepared;
    }

    public String getResponse_code ()
    {
        return response_code;
    }

    public void setResponse_code (String response_code)
    {
        this.response_code = response_code;
    }

    public String getPnr ()
    {
        return pnr;
    }

    public void setPnr (String pnr)
    {
        this.pnr = pnr;
    }

    public String getTotal_passengers ()
    {
        return total_passengers;
    }

    public void setTotal_passengers (String total_passengers)
    {
        this.total_passengers = total_passengers;
    }

    public To_station getTo_station ()
    {
        return to_station;
    }

    public void setTo_station (To_station to_station)
    {
        this.to_station = to_station;
    }

    public Passengers[] getPassengers ()
    {
        return passengers;
    }

    public void setPassengers (Passengers[] passengers)
    {
        this.passengers = passengers;
    }

    public Reservation_upto getReservation_upto ()
    {
        return reservation_upto;
    }

    public void setReservation_upto (Reservation_upto reservation_upto)
    {
        this.reservation_upto = reservation_upto;
    }

    public From_station getFrom_station ()
    {
        return from_station;
    }

    public void setFrom_station (From_station from_station)
    {
        this.from_station = from_station;
    }

    public Train getTrain ()
    {
        return train;
    }

    public void setTrain (Train train)
    {
        this.train = train;
    }

    public String getDebit ()
    {
        return debit;
    }

    public void setDebit (String debit)
    {
        this.debit = debit;
    }

    public Boarding_point getBoarding_point ()
    {
        return boarding_point;
    }

    public void setBoarding_point (Boarding_point boarding_point)
    {
        this.boarding_point = boarding_point;
    }

    public String getDoj ()
    {
        return doj;
    }

    public void setDoj (String doj)
    {
        this.doj = doj;
    }

    public Journey_class getJourney_class ()
    {
        return journey_class;
    }

    public void setJourney_class (Journey_class journey_class)
    {
        this.journey_class = journey_class;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [chart_prepared = "+chart_prepared+", response_code = "+response_code+", pnr = "+pnr+", total_passengers = "+total_passengers+", to_station = "+to_station+", passengers = "+passengers+", reservation_upto = "+reservation_upto+", from_station = "+from_station+", train = "+train+", debit = "+debit+", boarding_point = "+boarding_point+", doj = "+doj+", journey_class = "+journey_class+"]";
    }
}

