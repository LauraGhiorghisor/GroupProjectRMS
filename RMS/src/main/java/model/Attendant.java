package model;

import java.io.Serializable;
import java.util.Date;

public class Attendant implements Serializable {
    private int id;
    private String firstName, surname, cause, course, module;
    private String attended;

    public Attendant(int id, String firstName, String surname, String attended, String cause) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.attended = attended;
        this.cause = cause;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAttended() {
        return attended;
    }

    public void setAttended(String attended) {
        this.attended = attended;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
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
}
