package models;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;



@Data
public class Rubric {
    private String name;
    private List<Criteria> criteriaList = new ArrayList<>();
    private List<StudentGrade> studentGrades = new ArrayList<>();

    public Rubric(String name, List<Criteria> criteriaList) {
        this.name = name;
        this.criteriaList = criteriaList;
    }


}
