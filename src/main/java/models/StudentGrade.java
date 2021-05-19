package models;

import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Getter
@ToString
public class StudentGrade {

    private final String studentName;
    private final Map<String,Double> scores;

    public StudentGrade(String studentName, List<Criteria> criteriaList) {
        this.studentName = studentName;
        this.scores = initializeScoreMap(criteriaList);
    }


    private Map<String,Double> initializeScoreMap(List<Criteria> criteriaList){
        return criteriaList.stream().collect(Collectors.toMap(Criteria::getName,score -> 0.0));
    }

}
