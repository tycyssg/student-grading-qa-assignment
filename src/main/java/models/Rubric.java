package models;


import Exceptions.ExistException;
import Exceptions.RequiredException;
import lombok.Data;
import utils.Constants;

import java.util.ArrayList;
import java.util.List;

import static utils.Constants.CRITERIA_EXIST;


@Data
public class Rubric {
    private String name;
    private List<Criteria> criteriaList;
    private List<StudentGrade> studentGrades = new ArrayList<>();

    public Rubric(String name, List<Criteria> criteriaList) {
        this.name = name;
        this.criteriaList = criteriaList;
    }

    public void addCriteria(String criteriaName) throws ExistException, RequiredException {
        if (criteriaName == null) {
            throw new RequiredException(Constants.nameParamRequired("criteria"));
        }

        if (criteriaList.stream().anyMatch(c -> c.getName().equals(criteriaName))) {
            throw new ExistException(CRITERIA_EXIST);
        }

        criteriaList.add(new Criteria(criteriaName));
    }
}
