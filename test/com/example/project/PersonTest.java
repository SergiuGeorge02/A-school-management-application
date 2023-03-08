package com.example.project;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonTest {
    @Test
    void PersonTest(){
        Person p=new Person("Andrei", 22,Gender.Male,0);
        assertEquals("Andrei", p.getName(), "Wrong name");
        assertEquals(22,p.getAge(),"Wrong age");
        assertEquals(Gender.Male,p.getGender(),"Wrong gender");
    }


}
