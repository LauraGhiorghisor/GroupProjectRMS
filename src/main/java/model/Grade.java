package model;

public class Grade {
    private int id, studentID, assessmentID;
    private String first, second, finalG, year, module, term, file;
    private float weight;

    public Grade(int id, int studentID, int assessmentID, String first, String second, String finalG, String year, String module, String term, float weight, String file) {
        this.id = id;
        this.studentID = studentID;
        this.assessmentID = assessmentID;
        this.first = first;
        this.second = second;
        this.finalG = finalG;
        this.year = year;
        this.module = module;
        this.term = term;
        this.weight = weight;
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getFinalG() {
        return finalG;
    }

    public void setFinalG(String finalG) {
        this.finalG = finalG;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
