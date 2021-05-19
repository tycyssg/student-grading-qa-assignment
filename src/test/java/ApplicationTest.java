import models.Criteria;
import models.StudentGrade;
import  org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ApplicationTest {

    @Test
    public void initializeStudentGrade(){
        List<Criteria> criteriaList = Arrays.asList(new Criteria("C1"),new Criteria("C2"));
        String studentSubject = "test";
        Map<String,Double> scoresSubject = new HashMap<>();
        scoresSubject.put("C1", 0.0);
        scoresSubject.put("C2", 0.0);
        StudentGrade mock = new StudentGrade(studentSubject,criteriaList);
        Assertions.assertEquals(scoresSubject,mock.getScores());
        Assertions.assertEquals(studentSubject,mock.getStudentName());
    }



}
