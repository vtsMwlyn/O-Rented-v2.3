package com.example.orented;

public class Vehicle{
    private String id, name, manufacturer, control;
    private int seat, door, rentPrice;
    private User owner;
    private boolean isAvailable;

    public Vehicle(String id, String name, String manufacturer, int seat, int door, String control, User owner, int rentPrice){
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.seat = seat;
        this.door = door;
        this.control = control;
        this.owner = owner;
        this.rentPrice = rentPrice;

        isAvailable = true;
    }

    public User getOwner(){
        return owner;
    }

    public String getFullName(){
        return getManufacturer() + " " + getName();
    }

    public int getRentPrice(){
        return rentPrice;
    }

    public int getSeat(){
        return seat;
    }

    public int getDoor(){
        return door;
    }

    public String getControl(){
        return control;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getManufacturer(){
        return manufacturer;
    }

    public void setAvailability(boolean available){
        if(available){
            isAvailable = true;
        } else {
            isAvailable = false;
        }
    }

    public String checkAvailability(){
        if(isAvailable){
            return "yes";
        } else {
            return "no";
        }
    }
}
