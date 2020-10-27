package model;

public class Booking {
    private int id;
    private String room, date, start, end, description, time;

    public Booking(int id, String room, String date, String start, String end, String description, String time) {
        this.id = id;
        this.room = room;
        this.date = date;
        this.start = start;
        this.end = end;
        this.description = description;
        this.time = start + "-" + end;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
