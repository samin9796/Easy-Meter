package com.example.ahnafmozib.customerapp;

public class InfoOfSignUp {
    String name;
    String address;
    String meterId;
    String samityNo;
    String email;
    String mobileNo;

    public InfoOfSignUp(){
        //this constructor is required
    }

    public InfoOfSignUp(String name,String address,String meterId,String samityNo,String email,String mobileNo) {
        this.name = name;
        this.address = address;
        this.meterId = meterId;
        this.samityNo = samityNo;
        this.email = email;
        this.mobileNo = mobileNo;

    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getMeterId() {
        return meterId;
    }
    public String getSamityNo() {
        return samityNo;
    }
    public String getEmail() {
        return email;
    }
    public String getMobileNo() {
        return mobileNo;
    }
}
