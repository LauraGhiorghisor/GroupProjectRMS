package model;

public class Course {
    private int id, leader;
    private String code, name, start, end, description, aims;

    public Course(int id, int leader, String code, String name, String start, String end, String description, String aims) {
        this.id = id;
        this.leader = leader;
        this.code = code;
        this.name = name;
        this.start = start;
        this.end = end;
        this.description = description;
        this.aims = aims;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLeader() {
        return leader;
    }

    public void setLeader(int leader) {
        this.leader = leader;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
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
}
