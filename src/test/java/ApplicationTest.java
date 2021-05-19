import Exceptions.ExistException;
import Exceptions.InvalidException;
import Exceptions.NotExistException;
import Exceptions.RequiredException;
import models.Criteria;
import models.Rubric;
import models.StudentGrade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Constants;
import utils.Controller;

import java.util.*;

import static utils.Constants.*;

public class ApplicationTest {

    private List<Criteria> criteriaList;
    private Controller controller;

    @BeforeEach
    public void init() {
        criteriaList = Arrays.asList(new Criteria("C1"), new Criteria("C2"), new Criteria("C3"));
        controller = new Controller();
    }

    private List<Criteria> generateCriteria(Integer criteriaSize) {
        List<Criteria> criteria = new ArrayList<>();
        for (int i = 0; i < criteriaSize; i++) {
            criteria.add(new Criteria("Name" + i));
        }
        return criteria;
    }

    @Test
    public void initializeStudentGrade() {
        String studentSubject = "test";
        Map<String, Double> scoresSubject = new HashMap<>();
        scoresSubject.put("C1", 0.0);
        scoresSubject.put("C2", 0.0);
        scoresSubject.put("C3", 0.0);
        StudentGrade mock = new StudentGrade(studentSubject, criteriaList);
        Assertions.assertEquals(scoresSubject, mock.getScores());
        Assertions.assertEquals(studentSubject,mock.getStudentName());
    }

    @Test
    public void addRubricWithNameNull(){
        Exception exception = Assertions.assertThrows(RequiredException.class, () -> controller.addRubric(null, criteriaList));

        String message = exception.getMessage();
        Assertions.assertEquals(Constants.nameParamRequired("rubric"),message);
    }

    @Test
    public void addRubricWithSameName() throws RequiredException, InvalidException, ExistException {
        controller.addRubric("Test",criteriaList);

        Exception exception = Assertions.assertThrows(ExistException.class, () -> controller.addRubric("Test", criteriaList));

        String message = exception.getMessage();
        Assertions.assertTrue(message.contains(RUBRIC_EXIST));
    }

    @Test
    public void addRubricWithEmptyCriteria() {
        List<Criteria> criteria = new ArrayList<>();

        Exception exception = Assertions.assertThrows(InvalidException.class, () -> controller.addRubric("Test", criteria));

        String message = exception.getMessage();
        Assertions.assertTrue(message.contains(MIN_RUBRIC_CRITERIA));
    }

    @Test
    public void addRubricWithMaxCriteria() {
        List<Criteria> criteria = generateCriteria(11);

        Exception exception = Assertions.assertThrows(InvalidException.class, () -> controller.addRubric("Test", criteria));

        String message = exception.getMessage();
        Assertions.assertTrue(message.contains(MAX_RUBRIC_CRITERIA));
    }

    @Test
    public void getRubricByName() throws RequiredException, InvalidException, ExistException, NotExistException {
        Rubric mock = controller.addRubric("Test", criteriaList);
        Rubric data = controller.getRubric("Test");

        Assertions.assertEquals(mock, data);
    }

    @Test
    public void getRubricByNameWrongName() throws RequiredException, InvalidException, ExistException {
        controller.addRubric("Test", criteriaList);

        Exception exception = Assertions.assertThrows(NotExistException.class, () -> controller.getRubric("Test1"));

        String message = exception.getMessage();
        Assertions.assertTrue(message.contains(INVALID_RUBRIC));
    }

    @Test
    public void addCriteriaWithRubricNameNull() {
        Exception exception = Assertions.assertThrows(RequiredException.class, () -> controller.addCriteriaToARubric(null, "test"));

        String message = exception.getMessage();
        Assertions.assertEquals(Constants.nameParamRequired("rubric"), message);
    }

        @Test
        public void addCriteriaWithRubricInvalidName() throws RequiredException, InvalidException, ExistException {
            controller.addRubric("Test", criteriaList);
            Exception exception = Assertions.assertThrows(NotExistException.class, () -> controller.addCriteriaToARubric("Test1", "test"));

            String message = exception.getMessage();
            Assertions.assertTrue(message.contains(INVALID_RUBRIC));
        }

    @Test
    public void addCriteriaOnRubricWith10Criteria() throws RequiredException, InvalidException, ExistException {
        List<Criteria> criteria = generateCriteria(10);
        controller.addRubric("Test", criteria);

        Exception exception = Assertions.assertThrows(InvalidException.class, () -> controller.addCriteriaToARubric("Test", "nextOne"));

        String message = exception.getMessage();
        Assertions.assertTrue(message.contains(MAX_RUBRIC_CRITERIA));
    }

    @Test
    public void addCriteriaWithNameNull() throws RequiredException, InvalidException, ExistException {
        controller.addRubric("Test", criteriaList);
        Exception exception = Assertions.assertThrows(RequiredException.class, () -> controller.addCriteriaToARubric("Test", null));

        String message = exception.getMessage();
        Assertions.assertEquals(Constants.nameParamRequired("criteria"), message);
    }

    @Test
    public void addCriteriaWithSameName() throws RequiredException, InvalidException, ExistException {
        controller.addRubric("Test", criteriaList);
        Exception exception = Assertions.assertThrows(ExistException.class, () -> controller.addCriteriaToARubric("Test", "C1"));

        String message = exception.getMessage();
        Assertions.assertTrue(message.contains(CRITERIA_EXIST));
    }

    @Test
    public void createStudentGradeWithStudentNameNull() throws RequiredException, InvalidException, ExistException {
        Rubric r = controller.addRubric("Test", criteriaList);
        Exception exception = Assertions.assertThrows(RequiredException.class, () -> r.addStudentGrade(null));

        String message = exception.getMessage();
        Assertions.assertEquals(Constants.nameParamRequired("student"), message);
    }

    @Test
    public void createStudentGradeWithExistingStudent() throws RequiredException, InvalidException, ExistException {
        Rubric r = controller.addRubric("Test", criteriaList);
        r.addStudentGrade("Ciprian");

        Exception exception = Assertions.assertThrows(ExistException.class, () -> r.addStudentGrade("Ciprian"));

        String message = exception.getMessage();
        Assertions.assertTrue(message.contains(STUDENT_EXIST));
    }

    @Test
    public void getStudentGradeWithByNotExistingStudent() throws RequiredException, InvalidException, ExistException {
        Rubric r = controller.addRubric("Test", criteriaList);
        r.addStudentGrade("Ciprian");

        Exception exception = Assertions.assertThrows(NotExistException.class, () -> r.getStudentGrade("CiprianTest"));

        String message = exception.getMessage();
        Assertions.assertTrue(message.contains(STUDENT_NOT_EXIST));
    }

    @Test
    public void setScoreToAStudentInvalidCriteria() throws RequiredException, InvalidException, ExistException {
        Rubric r = controller.addRubric("Test", criteriaList);
        StudentGrade sg = r.addStudentGrade("Ciprian");

        Exception exception = Assertions.assertThrows(InvalidException.class, () -> sg.setScore("test", 5.0));

        String message = exception.getMessage();
        Assertions.assertTrue(message.contains(INVALID_CRITERIA));
    }

    @Test
    public void setScoreToAStudentScoreZero() throws RequiredException, InvalidException, ExistException {
        Rubric r = controller.addRubric("Test", criteriaList);
        StudentGrade sg = r.addStudentGrade("Ciprian");

        Exception exception = Assertions.assertThrows(InvalidException.class, () -> sg.setScore("C1", 0.0));

        String message = exception.getMessage();
        Assertions.assertTrue(message.contains(INVALID_SCORE));
    }

    @Test
    public void setScoreToAStudentScorePlusOneOverMax() throws RequiredException, InvalidException, ExistException {
        Rubric r = controller.addRubric("Test", criteriaList);
        StudentGrade sg = r.addStudentGrade("Ciprian");

        Exception exception = Assertions.assertThrows(InvalidException.class, () -> sg.setScore("C1", 6.0));

        String message = exception.getMessage();
        Assertions.assertTrue(message.contains(INVALID_SCORE));
    }

    @Test
    public void setScoreToAStudentScoreNegative() throws RequiredException, InvalidException, ExistException {
        Rubric r = controller.addRubric("Test", criteriaList);
        StudentGrade sg = r.addStudentGrade("Ciprian");

        Exception exception = Assertions.assertThrows(InvalidException.class, () -> sg.setScore("C1", -2.0));

        String message = exception.getMessage();
        Assertions.assertTrue(message.contains(INVALID_SCORE));
    }

    @Test
    public void setScoreToAStudentScoreNull() throws RequiredException, InvalidException, ExistException {
        Rubric r = controller.addRubric("Test", criteriaList);
        StudentGrade sg = r.addStudentGrade("Ciprian");

        Exception exception = Assertions.assertThrows(RequiredException.class, () -> sg.setScore("C1", null));

        String message = exception.getMessage();
        Assertions.assertTrue(message.contains(SCORE_NULL));
    }

    @Test
    public void getGradeWithScoresZero() throws RequiredException, InvalidException, ExistException {
        Rubric r = controller.addRubric("Test", criteriaList);
        StudentGrade sg = r.addStudentGrade("Ciprian");

        Exception exception = Assertions.assertThrows(InvalidException.class, sg::getGrade);

        String message = exception.getMessage();
        Assertions.assertTrue(message.contains(RUBRIC_STUDENT_GRADES_EMPTY));
    }

    @Test
    public void getGradeOfStudentWhichNotExits() throws RequiredException, InvalidException, ExistException {
        Rubric r = controller.addRubric("Test", criteriaList);
        r.addStudentGrade("Ciprian");
        Exception exception = Assertions.assertThrows(NotExistException.class, () -> r.getGrade("Ciprian1"));

        String message = exception.getMessage();
        Assertions.assertTrue(message.contains(STUDENT_NOT_EXIST));
    }

    @Test
    public void getGradeOfStudent() throws RequiredException, InvalidException, ExistException, NotExistException {
        Rubric r = controller.addRubric("Test", criteriaList);
        StudentGrade sg = r.addStudentGrade("Ciprian");
        sg.setScore("C1", 2.0);
        sg.setScore("C2", 2.0);
        sg.setScore("C3", 2.0);

        Assertions.assertEquals(2, r.getGrade("Ciprian"));
    }

    @Test
    public void getMinGradeOfRubricEmptyGradeSize() throws RequiredException, InvalidException, ExistException {
        Rubric r = controller.addRubric("Test", criteriaList);
        Exception exception = Assertions.assertThrows(InvalidException.class, r::getMinGrade);

        String message = exception.getMessage();
        Assertions.assertTrue(message.contains(STUDENT_GRADES_EMPTY));
    }

    @Test
    public void getMaxGradeOfRubricEmptyGradeSize() throws RequiredException, InvalidException, ExistException {
        Rubric r = controller.addRubric("Test", criteriaList);
        Exception exception = Assertions.assertThrows(InvalidException.class, r::getMaxGrade);

        String message = exception.getMessage();
        Assertions.assertTrue(message.contains(STUDENT_GRADES_EMPTY));
    }

    @Test
    public void getMinGradeOfRubric() throws RequiredException, InvalidException, ExistException {
        Rubric r = controller.addRubric("Test", criteriaList);
        StudentGrade sg = r.addStudentGrade("Ciprian");
        sg.setScore("C1", 2.0);
        sg.setScore("C2", 2.0);
        sg.setScore("C3", 2.0);

        StudentGrade sg1 = r.addStudentGrade("John");
        sg1.setScore("C1", 3.0);
        sg1.setScore("C2", 3.0);
        sg1.setScore("C3", 3.0);

        Assertions.assertEquals(2, r.getMinGrade());
    }

    @Test
    public void getMaxGradeOfRubric() throws RequiredException, InvalidException, ExistException {
        Rubric r = controller.addRubric("Test", criteriaList);
        StudentGrade sg = r.addStudentGrade("Ciprian");
        sg.setScore("C1", 2.0);
        sg.setScore("C2", 2.0);
        sg.setScore("C3", 2.0);

        StudentGrade sg1 = r.addStudentGrade("John");
        sg1.setScore("C1", 3.0);
        sg1.setScore("C2", 3.0);
        sg1.setScore("C3", 3.0);

        Assertions.assertEquals(3, r.getMaxGrade());
    }

    @Test
    public void getAvgOfRubric() throws RequiredException, InvalidException, ExistException {
        Rubric r = controller.addRubric("Test", criteriaList);
        StudentGrade sg = r.addStudentGrade("Ciprian");
        sg.setScore("C1", 3.0);
        sg.setScore("C2", 3.0);
        sg.setScore("C3", 3.0);

        StudentGrade sg1 = r.addStudentGrade("John");
        sg1.setScore("C1", 3.0);
        sg1.setScore("C2", 3.0);
        sg1.setScore("C3", 3.0);

        Assertions.assertEquals(3, r.getAvgGrade());
    }

    @Test
    public void getStandardDeviationOfRubric() throws RequiredException, InvalidException, ExistException {
        Rubric r = controller.addRubric("Test", criteriaList);
        StudentGrade sg = r.addStudentGrade("Ciprian");
        sg.setScore("C1", 2.0);
        sg.setScore("C2", 3.0);
        sg.setScore("C3", 4.0);

        StudentGrade sg1 = r.addStudentGrade("John");
        sg1.setScore("C1", 2.0);
        sg1.setScore("C2", 1.0);
        sg1.setScore("C3", 3.0);

        StudentGrade sg2 = r.addStudentGrade("Alex");
        sg2.setScore("C1", 4.0);
        sg2.setScore("C2", 4.0);
        sg2.setScore("C3", 4.0);

        Assertions.assertEquals(0.816496580927726, r.getStandardDeviation());
    }

    @Test
    public void getMinScore() throws RequiredException, InvalidException, ExistException {
        Rubric r = controller.addRubric("Test", criteriaList);
        StudentGrade sg = r.addStudentGrade("Ciprian");
        sg.setScore("C1", 2.0);
        sg.setScore("C2", 4.0);
        sg.setScore("C3", 1.0);

        Assertions.assertEquals(1, sg.getMinScore());
    }

    @Test
    public void getMaxScore() throws RequiredException, InvalidException, ExistException {
        Rubric r = controller.addRubric("Test", criteriaList);
        StudentGrade sg = r.addStudentGrade("Ciprian");
        sg.setScore("C1", 2.0);
        sg.setScore("C2", 4.0);
        sg.setScore("C3", 1.0);

        Assertions.assertEquals(4, sg.getMaxScore());
    }

    @Test
    public void getSDScore() throws RequiredException, InvalidException, ExistException {
        Rubric r = controller.addRubric("Test", generateCriteria(6));
        StudentGrade sg = r.addStudentGrade("Ciprian");
        sg.setScore("Name0", 2.0);
        sg.setScore("Name1", 3.0);
        sg.setScore("Name2", 1.0);
        sg.setScore("Name3", 5.0);
        sg.setScore("Name4", 3.0);
        sg.setScore("Name5", 4.0);

        Assertions.assertEquals(1.2909944487358056, sg.getStandardDeviation());
    }
}
