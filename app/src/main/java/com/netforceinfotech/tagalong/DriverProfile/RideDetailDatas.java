package com.netforceinfotech.tagalong.driverProfile;

/**
 * Created by xyz on 11/21/2016.
 */

public class RideDetailDatas {

    private String fromText;
    private String toText;
    private Date dateofRides;
    private String numberofSeat;
    private String hoursTextView;
    private String costPerSeat;


    public RideDetailDatas(String fromText, String toText, Date dateofRides, String numberofSeat, String hoursTextView, String costPerSeat) {
        this.fromText = fromText;
        this.toText = toText;
        this.dateofRides = dateofRides;
        this.numberofSeat = numberofSeat;
        this.hoursTextView = hoursTextView;
        this.costPerSeat = costPerSeat;
    }

    public String getFromText() {
        return fromText;
    }

    public void setFromText(String fromText) {
        this.fromText = fromText;
    }

    public String getToText() {
        return toText;
    }

    public void setToText(String toText) {
        this.toText = toText;
    }

    public Date getDateofRides() {
        return dateofRides;
    }

    public void setDateofRides(Date dateofRides) {
        this.dateofRides = dateofRides;
    }

    public String getNumberofSeat() {
        return numberofSeat;
    }

    public void setNumberofSeat(String numberofSeat) {
        this.numberofSeat = numberofSeat;
    }

    public String getHoursTextView() {
        return hoursTextView;
    }

    public void setHoursTextView(String hoursTextView) {
        this.hoursTextView = hoursTextView;
    }

    public String getCostPerSeat() {
        return costPerSeat;
    }

    public void setCostPerSeat(String costPerSeat) {
        this.costPerSeat = costPerSeat;
    }
}
