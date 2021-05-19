package utils;

public class Constants {
    public static final String INVALID_RUBRIC = "Invalid Rubric provided!";
    public static final String INVALID_CRITERIA = "Invalid Criteria provided!";
    public static final String INVALID_SCORE = "Invalid Score. Please add a value between 1 and 5";
    public static final String CRITERIA_EXIST = "Criteria already exists in this rubric!";
    public static final String STUDENT_EXIST = "Grade already exist for this student!";
    public static final String STUDENT_NOT_EXIST = "Student not found!";
    public static final String MAX_RUBRIC_CRITERIA = "Each rubric can contain maximum 10 criteria!";
    public static final String MIN_RUBRIC_CRITERIA = "Each rubric has to contain at least one criteria!";
    public static final String RUBRIC_EXIST = "Rubric already exists!";
    public static final String STUDENT_GRADES_EMPTY = "The student grades list for this rubric is empty!";
    public static final String RUBRIC_STUDENT_GRADES_EMPTY = "The scores for this student are empty";

    public static String nameParamRequired(String name){
        return "A "+name+" name is required to continue with this action.";
    }
}
