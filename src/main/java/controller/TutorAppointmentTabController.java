package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.AppointmentBox;
import java.io.IOException;
import java.sql.*;

public class TutorAppointmentTabController {
    @FXML
    private Label ghostSessionL;
    @FXML
    private ComboBox moduleCB, studentCB;
    @FXML
    private DatePicker datePicker;
    @FXML
    private VBox appVBox;
    @FXML
    private HBox upcomingHBox;

    public void setGhostSessionL(String sessionId) {
        ghostSessionL.setText(sessionId);
    }

    public void populateUpcoming() throws SQLException, IOException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        Statement fetchUser = rmsConnection.createStatement();
        ResultSet userResult = fetchUser.executeQuery("SELECT staff_id FROM users WHERE user_id='" + Integer.parseInt(ghostSessionL.getText()) + "'");
        while(userResult.next()) {
            ResultSet result = fetchStaff.executeQuery("SELECT * FROM appointments WHERE tutor_id='" + userResult.getString("staff_id") + "' AND is_complete = 0");
            while (result.next()) {
                String[] studentName = new String[2];
                studentName[0] = result.getString("student_first_name");
                studentName[1] = result.getString("student_surname");
                String[] tutorName = new String[2];
                tutorName[0] = result.getString("tutor_first_name");
                tutorName[1] = result.getString("tutor_surname");
                appVBox.getChildren().add(new AppointmentBox(appVBox, studentName, String.valueOf(result.getInt("student_id")), result.getString("date"), result.getString("tutor_id"), tutorName, result.getInt("appointment_id"), this));
            }
        }
    }

    public void populateCB() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        Statement fetchUser = rmsConnection.createStatement();
        ResultSet userResult = fetchUser.executeQuery("SELECT course FROM users WHERE user_id='" + Integer.parseInt(ghostSessionL.getText()) + "'");
        while(userResult.next()) {
            ResultSet result = fetchStaff.executeQuery("SELECT module_code FROM modules WHERE course='" + userResult.getString("course") + "'");
            System.out.println(result);
            while (result.next()) {
                moduleCB.getItems().addAll(result.getString("module_code"));
            }
        }
        ResultSet studentResult = fetchStaff.executeQuery("SELECT student_id FROM students");
        while(studentResult.next()) {
            studentCB.getItems().addAll(String.valueOf(studentResult.getInt("student_id")));
        }
    }

    public void createAppointment() throws SQLException, IOException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = "INSERT INTO appointments(student_first_name, student_surname, tutor_id, course, module, date, student_id, tutor_first_name, tutor_surname) VALUES (?,?,?,?,?,?,?,?,?)";
        String getStudents = "SELECT * FROM students WHERE student_id='" + Integer.parseInt(studentCB.getValue().toString()) + "'";
        String getStaffID = "SELECT * FROM users WHERE user_id='" + Integer.parseInt(ghostSessionL.getText()) + "'";
        String getCourse = "SELECT course FROM modules WHERE module_code='" + moduleCB.getValue().toString() + "'";
        Connection myConnection = DriverManager.getConnection(dbURL, username, password);
        Statement returnStudents = myConnection.createStatement();
        Statement returnStaff = myConnection.createStatement();
        ResultSet studentResult = returnStudents.executeQuery(getStudents);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        while (studentResult.next()) {
            preparedStatement.setString(1, studentResult.getString("first_name"));
            preparedStatement.setString(2, studentResult.getString("surname"));
        }
        ResultSet staffIDResult = returnStudents.executeQuery(getStaffID);
        while (staffIDResult.next()) {
            preparedStatement.setInt(3, staffIDResult.getInt("staff_id"));
            String getStaffNames = "SELECT first_name, surname FROM staff WHERE staff_id='" + staffIDResult.getInt("staff_id") + "'";
            ResultSet getStaffNameResult = returnStaff.executeQuery(getStaffNames);
            while (getStaffNameResult.next()) {
                preparedStatement.setString(8, getStaffNameResult.getString("first_name"));
                preparedStatement.setString(9, getStaffNameResult.getString("surname"));
            }
        }
        ResultSet courseResult = returnStudents.executeQuery(getCourse);
        while (courseResult.next()) {
            preparedStatement.setString(4, courseResult.getString("course"));
        }
        preparedStatement.setString(5, moduleCB.getValue().toString());
        preparedStatement.setString(6, datePicker.getValue().toString());
        preparedStatement.setString(7, String.valueOf(Integer.parseInt(studentCB.getValue().toString())));
        preparedStatement.execute();
        for(int i = 0; i < appVBox.getChildren().size(); i++) {
            if(appVBox.getChildren().get(i) instanceof HBox && !(appVBox.getChildren().get(i) == upcomingHBox)) {
                appVBox.getChildren().remove(i);
            }
        }
        populateUpcoming();
    }
}
