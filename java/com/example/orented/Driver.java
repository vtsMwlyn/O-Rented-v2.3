package com.example.orented;

public class Driver {
    private String id, name;
    private float rating;

    public Driver(String id, String name, float rating){
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public float getRating(){
        return rating;
    }

    public void setRating(float rating){
        this.rating = rating;
    }

}
