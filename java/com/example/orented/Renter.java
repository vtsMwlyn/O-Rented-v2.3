package com.example.orented;

import java.util.ArrayList;

public class Renter extends User{
    ArrayList<Schedule> scheduleList = new ArrayList<>();

    public Renter(String id, String username, String password, String name, String birthDate, String address, String phone, String email){
        super(id, username, password, name, birthDate, address, phone, email);
    }

    public void addSchedule(Schedule s){
        scheduleList.add(s);
    }

    public ArrayList<Schedule> getScheduleList(){
        return scheduleList;
    }
}
