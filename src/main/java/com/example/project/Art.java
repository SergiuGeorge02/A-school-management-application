package com.example.project;

import java.util.ArrayList;
import java.util.List;

public class Art extends Department implements unPaid{
    private List<String> subjects;
    public Art(List<String> subjects){
        super("Art");
        this.subjects=subjects;
    }

    public List<String>getSubjects() {
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
        return "Art with subjects: "+subjects;
    }
}
