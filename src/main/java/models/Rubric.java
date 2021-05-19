package models;


import Exceptions.ExistException;
import Exceptions.NotExistException;
import Exceptions.RequiredException;
import lombok.Data;
import utils.Constants;

import java.util.ArrayList;
import java.util.List;

import static utils.Constants.*;


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

    public void addStudentGrade(String studentName, List<Criteria> criteriaList) throws RequiredException, ExistException {
        boolean studentExist = checkIfStudentExist(studentName);

        if (studentExist) {
            throw new ExistException(STUDENT_EXIST);
        }

        studentGrades.add(new StudentGrade(studentName, criteriaList));
    }

    public StudentGrade getStudentGrade(String studentName) throws RequiredException, NotExistException {
        boolean studentExist = checkIfStudentExist(studentName);

        if (!studentExist) {
            throw new NotExistException(STUDENT_NOT_EXIST);
        }
        return studentGrades.stream().filter(r -> r.getStudentName().equals(studentName)).findFirst().orElse(null);
    }

    private boolean checkIfStudentExist(String studentName) throws RequiredException {
        if (studentName == null) {
            throw new RequiredException(Constants.nameParamRequired("student"));
        }

        return studentGrades.stream().anyMatch(s -> s.getStudentName().equals(studentName));
    }
}
