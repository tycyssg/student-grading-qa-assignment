import Exceptions.ExistException;
import Exceptions.InvalidException;
import Exceptions.NotExistException;
import Exceptions.RequiredException;
import models.Criteria;
import models.Rubric;
import models.StudentGrade;
import utils.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] args) throws RequiredException, InvalidException, ExistException, NotExistException {
        List<Criteria> c1 = new ArrayList<>(Arrays.asList(
                new Criteria("Design"),
                new Criteria("Implementation"),
                new Criteria("Testing"),
                new Criteria("Documentation")
        ));

        List<Criteria> c2 = new ArrayList<>(Arrays.asList(
                new Criteria("Planning"),
                new Criteria("Gitflow process"),
                new Criteria("Content"),
                new Criteria("Organisation")
        ));

        List<Criteria> c3 = new ArrayList<>(Arrays.asList(
                new Criteria("Determination of the Pattern"),
                new Criteria("Pattern Implementation"),
                new Criteria("Code Functionality")
        ));

        Controller controller = new Controller();
        controller.addRubric("Distributed Systems", c1);
        controller.addRubric("QA", c2);
        controller.addRubric("Patterns", c3);
        System.out.println("Rubrics saved.");


        Rubric rubric = controller.getRubric("QA");
        Rubric rubric1 = controller.getRubric("Patterns");
        rubric.addCriteria("Writing");
        System.out.println("Criteria added.");

        rubric.addStudentGrade("Ciprian");
        rubric.addStudentGrade("John");
        rubric.addStudentGrade("Alex");

        rubric1.addStudentGrade("Mihai");
        rubric1.addStudentGrade("Elsa");
        rubric1.addStudentGrade("Dwayne");
        System.out.println("Student grades saved.");

        StudentGrade studentGrade = rubric.getStudentGrade("Ciprian");
        studentGrade.setScore("Planning", 3.0);
        studentGrade.setScore("Gitflow process", 5.0);
        studentGrade.setScore("Content", 4.0);
        studentGrade.setScore("Organisation", 3.0);
        studentGrade.setScore("Writing", 1.0);

        StudentGrade studentGrade1 = rubric.getStudentGrade("John");
        studentGrade1.setScore("Planning", 2.0);
        studentGrade1.setScore("Gitflow process", 4.0);
        studentGrade1.setScore("Content", 1.0);
        studentGrade1.setScore("Organisation", 5.0);
        studentGrade1.setScore("Writing", 4.0);

        StudentGrade studentGrade2 = rubric.getStudentGrade("Alex");
        studentGrade2.setScore("Planning", 4.0);
        studentGrade2.setScore("Gitflow process", 1.0);
        studentGrade2.setScore("Content", 3.0);
        studentGrade2.setScore("Organisation", 5.0);
        studentGrade2.setScore("Writing", 2.0);

        System.out.println("Students score saved.");

        System.out.println();
        System.out.println("Grade details for the student " + studentGrade.getStudentName());
        System.out.println("Grade: " + studentGrade.getGrade());
        System.out.println("Max Score: " + studentGrade.getMaxScore());
        System.out.println("Min Score: " + studentGrade.getMinScore());
        System.out.println("Standard Deviation Score: " + studentGrade.getStandardDeviation());

        System.out.println();
        System.out.println("Rubric details for the rubric " + rubric.getName());
        System.out.println("Max Grade: " + rubric.getMaxGrade());
        System.out.println("Min Grade: " + rubric.getMinGrade());
        System.out.println("Standard Deviation Grade: " + rubric.getStandardDeviation());

        System.out.println();
        System.out.println("Grade of Ciprian of rubric: " + rubric.getName() + " is " + rubric.getGrade("Ciprian"));
        System.out.println("Grade of John of rubric: " + rubric.getName() + " is " + rubric.getGrade("John"));

        System.out.println();
        System.out.println("All available Rubrics");
        for (Rubric r : controller.getRubricList()) {
            System.out.println(r.getName());
        }
    }

}
