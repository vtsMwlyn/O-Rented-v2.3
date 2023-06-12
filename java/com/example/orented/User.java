package com.example.orented;

import java.util.ArrayList;

public class User{
    protected String id, username, password, name, birthDate, address, phone, email;
    protected ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();

    public User(String id, String username, String password, String name, String birthDate, String address, String phone, String email){
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getName(){
        return name;
    }

    public String getId(){ return id; }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getBirthDate(){
        return birthDate;
    }

    public String getAddress(){
        return address;
    }

    public String getPhone(){
        return phone;
    }

    public String getEmail(){
        return email;
    }

    public void addSchedule(Schedule s){
        scheduleList.add(s);
    }

    public void removeSchedule(Schedule s){
        scheduleList.remove(s);
    }

    public Schedule retrieveLatestSchedule(){
        return scheduleList.get(0);
    }

    public boolean checkList(){
        return scheduleList.isEmpty();
    }

}
