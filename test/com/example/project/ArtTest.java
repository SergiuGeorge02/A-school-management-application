package com.example.project;
import com.example.project.Art;
import com.example.project.Department;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArtTest {
    @Test
    void ArtTest(){
        List<String> subjects=new ArrayList<>();
        subjects.add("subject1");
        subjects.add("subject2");
        Art art=new Art(subjects);
        assertEquals(subjects,art.getSubjects(),"Wrong subject list");
        boolean ok=false;
        if(art instanceof Department)
            ok=true;
        assertEquals(ok,true,"Art class should inherit Department");
    }


}