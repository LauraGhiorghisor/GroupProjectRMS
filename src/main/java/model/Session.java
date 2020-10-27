package model;

import java.sql.*;

public class Session {
    private final String username;
    private AccessLevel accessLevel;
    private final Timestamp lastLogged;
    private final int id;

    public Session(String username, AccessLevel accessLevel, Timestamp lastLogged, int id) {
        this.username = username;
        this.accessLevel = accessLevel;
        this.lastLogged = lastLogged;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public AccessLevel getAccessLevel() {
        return accessLevel;
    }
    public Timestamp getLastLogged() {
        return lastLogged;
    }
    public int getId() {
        return id;
    }
    public String getUserName() {
        return username;
    }
    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }


}
