package models;

import java.util.Map;
import Exceptions.InvalidException;
import lombok.Getter;
import lombok.ToString;
import utils.Constants;
import Exceptions.RequiredException;
import java.util.List;
import java.util.stream.Collectors;

import static utils.Constants.*;


@Getter
@ToString
public class StudentGrade {

    private final String studentName;
    private final Map<String, Double> scores;

    public StudentGrade(String studentName, List<Criteria> criteriaList) {
        this.studentName = studentName;
        this.scores = initializeScoreMap(criteriaList);
    }


    private Map<String, Double> initializeScoreMap(List<Criteria> criteriaList) {
        return criteriaList.stream().collect(Collectors.toMap(Criteria::getName, score -> 0.0));
    }

    private double getScoresSum() {
        return scores.values().stream().mapToDouble(v -> v).sum();
    }

    private Double checkScore(Double score) throws InvalidException, RequiredException {
        if (score == null) {
            throw new RequiredException(SCORE_NULL);
        }

        if (score < 1 || score > 5)
            throw new InvalidException(INVALID_SCORE);

        return score;
    }


            public void setScore(String criteriaName, Double score) throws InvalidException, RequiredException {
                if (!this.scores.containsKey(criteriaName))
                    throw new InvalidException(INVALID_CRITERIA);

                this.scores.put(criteriaName, checkScore(score));
            }

    public Double getGrade() throws InvalidException {
        checkScoreListSize();
        double sum = getScoresSum();
        return sum / this.scores.size();
    }

    private void checkScoreListSize() throws InvalidException {
        double sum = getScoresSum();
        if (sum == 0) {
            throw new InvalidException(RUBRIC_STUDENT_GRADES_EMPTY);
        }
    }


}
