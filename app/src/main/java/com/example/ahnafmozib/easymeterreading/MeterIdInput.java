package com.example.ahnafmozib.easymeterreading;

public class                                                                                                                                                                                                                                                MeterIdInput {
    String meterId;
    String date;
    String currentReading;
    public MeterIdInput(){
        //this constructor is required
    }
    public MeterIdInput(String meterId,String date,String currentReading) {
        this.meterId = meterId;
        this.date = date;
        this.currentReading = currentReading;
    }
    public String getMeterId() {
        return meterId;
    }
    public String getDate() {
        return date;
    }
    public String getCurrentReading() {
        return currentReading;
    }
}
