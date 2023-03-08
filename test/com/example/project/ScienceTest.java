package com.example.project;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScienceTest {
    @Test
    void ScienceTest(){
        List<String> subjects=new ArrayList<>();
        subjects.add("subject1");
        subjects.add("subject2");
        Science science=new Science(subjects);
        assertEquals(subjects,science.getSubjects(),"Wrong subject list");
        boolean ok=false;
        if(science instanceof Department)
            ok=true;
        assertEquals(ok,true,"Science class should inherit Department");

    }


}