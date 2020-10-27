package model;

import java.io.Serializable;

public class PATForm implements Serializable {
    private String studentID, studentName, tutorID,
            tutorName, nextMeetingDate, startTime, endTime, summary, actionPoints,
            tutorSig, studentSig;

    public PATForm(String studentID, String studentName, String tutorID, String tutorName, String nextMeetingDate, String startTime, String endTime, String summary, String actionPoints, String tutorSig, String studentSig) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.tutorID = tutorID;
        this.tutorName = tutorName;
        this.nextMeetingDate = nextMeetingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.summary = summary;
        this.actionPoints = actionPoints;
        this.tutorSig = tutorSig;
        this.studentSig = studentSig;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTutorID() {
        return tutorID;
    }

    public void setTutorID(String tutorID) {
        this.tutorID = tutorID;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getNextMeetingDate() {
        return nextMeetingDate;
    }

    public void setNextMeetingDate(String nextMeetingDate) {
        this.nextMeetingDate = nextMeetingDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getActionPoints() {
        return actionPoints;
    }

    public void setActionPoints(String actionPoints) {
        this.actionPoints = actionPoints;
    }

    public String getTutorSig() {
        return tutorSig;
    }

    public void setTutorSig(String tutorSig) {
        this.tutorSig = tutorSig;
    }

    public String getStudentSig() {
        return studentSig;
    }

    public void setStudentSig(String studentSig) {
        this.studentSig = studentSig;
    }
}
