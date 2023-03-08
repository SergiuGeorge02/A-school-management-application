package com.example.project;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepartmentTest {
    @Test
    void DepartmentTest(){
        Department d=new Department("Department");
        assertEquals("Department",d.getName(),"Wrong name");
    }


}