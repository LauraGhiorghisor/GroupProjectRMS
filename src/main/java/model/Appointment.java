package model;

public class Appointment {
    private int studentId, tutorId, id;
    private String studentFN, studentS, course, module, year, appDate, patForm;

    public Appointment(int id, int studentId, int tutorId, String studentFN, String studentS, String course, String module, String year, String appDate, String patForm) {
        this.id = id;
        this.studentId = studentId;
        this.tutorId = tutorId;
        this.studentFN = studentFN;
        this.studentS = studentS;
        this.course = course;
        this.module = module;
        this.year = year;
        this.appDate = appDate;
        this.patForm = patForm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public String getStudentFN() {
        return studentFN;
    }

    public void setStudentFN(String studentFN) {
        this.studentFN = studentFN;
    }

    public String getStudentS() {
        return studentS;
    }

    public void setStudentS(String studentS) {
        this.studentS = studentS;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getPatForm() {
        return patForm;
    }

    public void setPatForm(String patForm) {
        this.patForm = patForm;
    }
}
