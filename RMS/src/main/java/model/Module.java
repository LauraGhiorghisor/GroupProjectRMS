package model;

public class Module {
    private String code, startYear, endYear, level, credits, title, description, aims, course;
    private int leader, id;

    public Module(int id, String code, String course, String startYear, String endYear, String level, String credits, String title, String description, String aims, int leader) {
        this.id = id;
        this.code = code;
        this.course = course;
        this.startYear = startYear;
        this.endYear = endYear;
        this.level = level;
        this.credits = credits;
        this.title = title;
        this.description = description;
        this.aims = aims;
        this.leader = leader;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAims() {
        return aims;
    }

    public void setAims(String aims) {
        this.aims = aims;
    }

    public int getLeader() {
        return leader;
    }

    public void setLeader(int leader) {
        this.leader = leader;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
