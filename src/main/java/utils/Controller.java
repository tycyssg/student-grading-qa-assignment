package utils;

import Exceptions.ExistException;
import Exceptions.InvalidException;
import Exceptions.NotExistException;
import Exceptions.RequiredException;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Criteria;
import models.Rubric;

import java.util.ArrayList;
import java.util.List;

import static utils.Constants.*;

@NoArgsConstructor
@Data
public class Controller {
    private List<Rubric> rubricList = new ArrayList<>();

    public Rubric addRubric(String rubricName, List<Criteria> criteriaList) throws ExistException, InvalidException, RequiredException {

        if (checkIfRubricExist(rubricName)) {
            throw new ExistException(RUBRIC_EXIST);
        }

        if (criteriaList.isEmpty()) {
            throw new InvalidException(MIN_RUBRIC_CRITERIA);
        }

        if (criteriaList.size() > 10) {
            throw new InvalidException(MAX_RUBRIC_CRITERIA);
        }

        Rubric r = new Rubric(rubricName, criteriaList);
        rubricList.add(r);
        return r;
    }

    public Rubric getRubric(String rubricName) throws NotExistException, RequiredException {
        if (!checkIfRubricExist(rubricName)) {
            throw new NotExistException(INVALID_RUBRIC);
        }

        return rubricList.stream().filter(r -> r.getName().equals(rubricName)).findFirst().orElse(null);
    }

    public void addCriteriaToARubric(String rubricName, String criteriaName) throws NotExistException, InvalidException, ExistException, RequiredException {
        Rubric rubric = getRubric(rubricName);

        if (rubric.getCriteriaList().size() == 10) {
            throw new InvalidException(MAX_RUBRIC_CRITERIA);
        }

        rubric.addCriteria(criteriaName);
    }

    private boolean checkIfRubricExist(String rubricName) throws RequiredException {
        if (rubricName == null) {
            throw new RequiredException(Constants.nameParamRequired("rubric"));
        }

        return rubricList.stream().anyMatch(r -> r.getName().equals(rubricName));
    }


}
