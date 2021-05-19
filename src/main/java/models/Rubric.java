package models;


import Exceptions.ExistException;
import Exceptions.InvalidException;
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

    public StudentGrade addStudentGrade(String studentName) throws RequiredException, ExistException {
        boolean studentExist = checkIfStudentExist(studentName);

        if (studentExist) {
            throw new ExistException(STUDENT_EXIST);
        }

        StudentGrade sg = new StudentGrade(studentName, criteriaList);
        studentGrades.add(sg);
        return sg;
    }

    public StudentGrade getStudentGrade(String studentName) throws RequiredException, NotExistException {
        boolean studentExist = checkIfStudentExist(studentName);

        if (!studentExist) {
            throw new NotExistException(STUDENT_NOT_EXIST);
        }
        return studentGrades.stream().filter(r -> r.getStudentName().equals(studentName)).findFirst().orElse(null);
    }

    public double getGrade(String studentName) throws RequiredException, InvalidException, NotExistException {
        boolean studentExist = checkIfStudentExist(studentName);

        if (!studentExist) {
            throw new NotExistException(STUDENT_NOT_EXIST);
        }

        return studentGrades.stream().filter(s -> s.getStudentName().equals(studentName)).findFirst().get().getGrade();
    }

    public double getMinGrade() throws InvalidException {
        checkStudentGradesSize();
        return studentGrades.stream().mapToDouble(s -> {
            try {
                return s.getGrade();
            } catch (InvalidException e) {
                e.printStackTrace();
            }
            return 0;
        }).min().orElse(0);
    }

    public double getMaxGrade() throws InvalidException {
        checkStudentGradesSize();
        return studentGrades.stream().mapToDouble(s -> {
            try {
                return s.getGrade();
            } catch (InvalidException e) {
                e.printStackTrace();
            }
            return 0;
        }).max().orElse(0);
    }

    public double getStandardDeviation() throws InvalidException {
        double avg = getAvgGrade();
        double stDev = 0;

        for (StudentGrade s : studentGrades) {
            stDev += Math.pow(s.getGrade() - avg, 2);
        }

        return Math.sqrt(stDev / studentGrades.size());
    }

    public double getAvgGrade() throws InvalidException {
        checkStudentGradesSize();
        double sum = studentGrades.stream().mapToDouble(s -> {
            try {
                return s.getGrade();
            } catch (InvalidException e) {
                e.printStackTrace();
            }
            return 0;
        }).sum();
        return sum / studentGrades.size();
    }

    private boolean checkIfStudentExist(String studentName) throws RequiredException {
        if (studentName == null) {
            throw new RequiredException(Constants.nameParamRequired("student"));
        }

        return studentGrades.stream().anyMatch(s -> s.getStudentName().equals(studentName));
    }

    private void checkStudentGradesSize() throws InvalidException {
        if (studentGrades.isEmpty()) throw new InvalidException(STUDENT_GRADES_EMPTY);
    }
}
