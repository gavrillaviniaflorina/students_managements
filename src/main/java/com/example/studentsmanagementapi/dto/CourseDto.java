package com.example.studentsmanagementapi.dto;

import lombok.Data;

@Data
public class CourseDto {

    private String Name;
    private String Departament;

    public CourseDto(String name, String departament) {
        Name = name;
        Departament = departament;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDepartament() {
        return Departament;
    }

    public void setDepartament(String departament) {
        Departament = departament;
    }
}
