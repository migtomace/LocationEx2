package com.websitesinseattle.locationex;

public class Meeting {

    private String name;
    private String address;

    //create constructors
    public Meeting(){
        this.name = getName();
        this.address = getAddress();

    }
    public Meeting(String name, String address){
        this.name = name;
        this.address = address;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
