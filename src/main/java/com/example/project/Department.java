package com.example.project;

public class  Department {
    private String name;
    public Department(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public String toString(){
        return name;
    }
}
