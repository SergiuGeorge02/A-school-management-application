package com.example.project;

import java.util.ArrayList;
import java.util.List;

public class Science extends Department implements Paid{
    private List<String> subjects=new ArrayList<>();
    public Science(List<String> subjects){
        super("Science");
        this.subjects=subjects;
    }

    public List<String> getSubjects() {
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
        return 3000;
    }
    public String toString(){
        return "Science with subjects: "+subjects;
    }
}
