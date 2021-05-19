package utils;

import Exceptions.ExistException;
import Exceptions.InvalidException;
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

    public void addRubric(String rubricName, List<Criteria> criteriaList) throws ExistException, InvalidException, RequiredException {
        if (rubricName == null) {
            throw new RequiredException(Constants.nameParamRequired("rubric"));
        }

        if (checkIfRubricExist(rubricName)) {
            throw new ExistException(RUBRIC_EXIST);
        }

        if (criteriaList.isEmpty()) {
            throw new InvalidException(MIN_RUBRIC_CRITERIA);
        }

        if (criteriaList.size() > 10) {
            throw new InvalidException(MAX_RUBRIC_CRITERIA);
        }

        rubricList.add(new Rubric(rubricName, criteriaList));
    }

    private boolean checkIfRubricExist(String rubricName) {
        return rubricList.stream().anyMatch(r -> r.getName().equals(rubricName));
    }
}
