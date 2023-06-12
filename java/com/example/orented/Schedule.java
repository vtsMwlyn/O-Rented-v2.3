package com.example.orented;

public class Schedule{
    private String id, pickUpDate, pickUpTime, pickUpLocation, payment;
    private int duration;
    private User borrower;
    private User owner;
    private Vehicle car;
    private Driver driver;

    public Schedule(String id, String pickUpDate, String pickUpTime, String pickUpLocation, String payment, int duration, User u, Vehicle v, Driver d){
        this.id = id;
        this.pickUpDate = pickUpDate;
        this.pickUpTime = pickUpTime;
        this.pickUpLocation = pickUpLocation;
        this.duration = duration;
        this.payment = payment;
        this.borrower = u;
        this.car = v;
        this.owner = v.getOwner();
        this.driver = d;
    }

    public Vehicle getVehicle(){
        return car;
    }

    public String getId(){
        return id;
    }

    public String getPickUpDate(){
        return pickUpDate;
    }

    public String getPickUpTime(){
        return pickUpTime;
    }

    public String getPickUpLocation(){
        return pickUpLocation;
    }

    public int getDuration(){
        return duration;
    }

    public String getPayment(){
        return payment;
    }

    public User getBorrower(){
        return borrower;
    }

    public User getOwner(){
        return owner;
    }

    public Driver getDriver(){
        return driver;
    }
}
