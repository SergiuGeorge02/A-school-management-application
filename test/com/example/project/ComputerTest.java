package com.example.project;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComputerTest {
    @Test
    void ComputerTest(){
        List<String> subjects=new ArrayList<>();
        subjects.add("subject1");
        subjects.add("subject2");
        Computer computer=new Computer(subjects);
        assertEquals(subjects,computer.getSubjects(),"Wrong subject list");
        boolean ok=false;
        if(computer instanceof Department)
            ok=true;
        assertEquals(ok,true,"Computer class should inherit Department");
    }


}