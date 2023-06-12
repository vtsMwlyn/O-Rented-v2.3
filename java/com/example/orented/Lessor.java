package com.example.orented;

import java.util.ArrayList;

public class Lessor extends User{
    private ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();

    public Lessor(String id, String username, String password, String name, String birthDate, String address, String phone, String email){
        super(id, username, password, name, birthDate, address, phone, email);
    }

    public void addVehicle(Vehicle v){
        vehicleList.add(v);
    }

    public ArrayList<Vehicle> getOwnedVehicleList(){
        return vehicleList;
    }
}
