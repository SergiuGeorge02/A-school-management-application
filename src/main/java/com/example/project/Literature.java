package com.example.project;

import java.util.ArrayList;
import java.util.List;

public class Literature extends Department implements unPaid{
    private List<String> subjects=new ArrayList<>();
    public Literature(List<String> subjects){
        super("Literature");
        this.subjects=subjects;
    }

    public List<String> getSubjects() {
        return subjects;
    }
    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    @Override
    public boolean isunPaid() {
        return true;
    }

    public String toString(){
        return "Literature with subjects: "+subjects;
    }
}
