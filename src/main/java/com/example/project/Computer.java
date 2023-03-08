package com.example.project;

import java.util.ArrayList;
import java.util.List;

public class Computer extends Department implements Paid{
    private List<String> subjects=new ArrayList<>();
    public Computer(List<String> subjects){
        super("Computers");
        this.subjects=subjects;
    }
    public List<String> getSubjects(){
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    @Override
    public boolean isPaid() {
        return true;
    }

    @Override
    public int gettax() {
       return 2000;
    }
    public String toString(){
        return "Computers with subjects: "+subjects;
    }
}
