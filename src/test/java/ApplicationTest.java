import Exceptions.ExistException;
import Exceptions.InvalidException;
import Exceptions.NotExistException;
import Exceptions.RequiredException;
import models.Criteria;
import models.Rubric;
import models.StudentGrade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Constants;
import utils.Controller;

import java.util.*;

import static utils.Constants.*;

public class ApplicationTest {

    private final List<Criteria> criteriaList = Arrays.asList(new Criteria("C1"),new Criteria("C2"),new Criteria("C3"));
    private final Controller controller = new Controller();

    @Test
    public void initializeStudentGrade(){
        String studentSubject = "test";
        Map<String,Double> scoresSubject = new HashMap<>();
        scoresSubject.put("C1", 0.0);
        scoresSubject.put("C2", 0.0);
        scoresSubject.put("C3", 0.0);
        StudentGrade mock = new StudentGrade(studentSubject,criteriaList);
        Assertions.assertEquals(scoresSubject,mock.getScores());
        Assertions.assertEquals(studentSubject,mock.getStudentName());
    }

    @Test
    public void addRubricWithNameNull(){
        Exception exception = Assertions.assertThrows(RequiredException.class, () -> {
            controller.addRubric(null,criteriaList);
        });

        String message = exception.getMessage();
        Assertions.assertEquals(Constants.nameParamRequired("rubric"),message);
    }

    @Test
    public void addRubricWithSameName() throws RequiredException, InvalidException, ExistException {
        controller.addRubric("Test",criteriaList);

        Exception exception = Assertions.assertThrows(ExistException.class, () -> {
            controller.addRubric("Test", criteriaList);
        });

        String message = exception.getMessage();
        Assertions.assertTrue(message.contains(RUBRIC_EXIST));
    }

    @Test
    public void addRubricWithEmptyCriteria() {
        List<Criteria> criteria = new ArrayList<>();

        Exception exception = Assertions.assertThrows(InvalidException.class, () -> {
            controller.addRubric("Test",criteria);
        });

        String message = exception.getMessage();
        Assertions.assertTrue(message.contains(MIN_RUBRIC_CRITERIA));
    }

    @Test
    public void addRubricWithMaxCriteria() {
        List<Criteria> criteria = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            criteria.add(new Criteria("Name"+i));
        }

        Exception exception = Assertions.assertThrows(InvalidException.class, () -> {
            controller.addRubric("Test", criteria);
        });

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

        Exception exception = Assertions.assertThrows(NotExistException.class, () -> {
            controller.getRubric("Test1");
        });

        String message = exception.getMessage();
        Assertions.assertTrue(message.contains(INVALID_RUBRIC));
    }


}
