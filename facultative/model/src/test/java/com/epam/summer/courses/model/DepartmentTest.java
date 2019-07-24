package com.epam.summer.courses.model;

import com.epam.summer.courses.model.Department;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DepartmentTest {

    Department department = new Department();
    @Test
    public void getDepartmentId() {
        department.setDepartmentId(15);
        assertEquals(Integer.valueOf(15), department.getDepartmentId());
    }

}