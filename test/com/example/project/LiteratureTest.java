package com.example.project;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LiteratureTest{
    @Test
    void ScienceTest(){
        List<String> subjects=new ArrayList<>();
        subjects.add("subject1");
        subjects.add("subject2");
        Literature literature=new Literature(subjects);
        assertEquals(subjects,literature.getSubjects(),"Wrong subject list");
        boolean ok=false;
        if(literature instanceof Department)
            ok=true;
        assertEquals(ok,true,"Literature class should inherit Department");

    }


}