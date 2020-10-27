package controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import view.PATWindow;

import java.io.IOException;
import java.sql.*;

public class AppointmentBoxController {
    @FXML
    private Text studentT, idT, dateT;
    @FXML
    private HBox hBox;
    @FXML
    private Button uploadBtn, completeBtn;
    private int appointmentID;
    private String tutorID;
    private String[] tutorName;

    public void setValues(String[] student, String id, String date, String idOfTutor, String[] nameOfTutor, int idOfAppointment) throws SQLException {
        hBox.setMargin(hBox, new Insets(10,0,10,0));
        studentT.setText(student[0] + " " + student[1]);
        idT.setText(id);
        dateT.setText(date);
        tutorID = idOfTutor;
        tutorName = nameOfTutor;
        appointmentID = idOfAppointment;
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        ResultSet result;
        System.out.println(appointmentID);
        result = fetchStaff.executeQuery("SELECT * FROM appointments WHERE appointment_id='" + appointmentID + "'");
        while (result.next()) {
            if (result.getInt("uploaded_pat") == 1)
                setUploadBtnToUploaded();
        }
    }

    public void openPATWindow() throws IOException, SQLException {
        new PATWindow(this, appointmentID);
    }

    public void setUploadBtnToUploaded() {
        uploadBtn.setText("PAT Uploaded");
        uploadBtn.setDisable(true);
    }

    public String getStudentName() {
        return studentT.getText();
    }

    public String getStudentID() {
        return idT.getText();
    }

    public String getTutorID() {
        return tutorID;
    }

    public String[] getTutorName() {
        return tutorName;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void addCompleteBtnListener(TutorAppointmentTabController tutorAppointmentTabController) {
        completeBtn.setOnAction(event -> {
            String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
            String username = "root";
            String password = "root";
            String updatequery = ("UPDATE appointments SET is_complete = ? WHERE appointment_id='" + appointmentID + "'");
            Connection myCon = null;
            try {
                myCon = DriverManager.getConnection(dbURL, username, password);
                PreparedStatement preparedStatement = myCon.prepareStatement(updatequery);
                PreparedStatement updateStatement = myCon.prepareStatement(updatequery);
                preparedStatement.setInt(1, 1);
                preparedStatement.executeUpdate();
                tutorAppointmentTabController.populateUpcoming();
        } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
            });
    }
}
