package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.PATForm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.time.LocalDate;

public class PATFormController {
    @FXML
    private Button saveBtn;
    @FXML
    private TextField studentIDTF, studentNameTF, tutorIDTF, tutorNameTF, tutorSigTF, studentSigTF;
    @FXML
    private TextArea summaryTA, actionPointsTA;
    @FXML
    private ComboBox<String> startCB, endCB;
    @FXML
    private DatePicker nextMeetingDP;


    public void setSaveBtnListener(AppointmentBoxController appointmentBoxController, int appointmentID) {
        saveBtn.setOnAction(e -> {
            PATForm patForm = new PATForm(studentIDTF.getText(), studentNameTF.getText(), tutorIDTF.getText(), tutorNameTF.getText(),
                    nextMeetingDP.getValue().toString(), startCB.getValue(), endCB.getValue(), summaryTA.getText(), actionPointsTA.getText(),
                    tutorSigTF.getText(), studentSigTF.getText());
            String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
            String username = "root";
            String password = "root";
            String query = ("INSERT INTO pats (student_id, student_name, tutor_id, tutor_name, next_meeting_date, start_time, end_time, summary, action_points, tutor_sig, student_sig, appointment_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            String updatequery = ("UPDATE appointments SET uploaded_pat = ? WHERE appointment_id='" + appointmentID + "'");

            Connection myCon = null;
            try {
                myCon = DriverManager.getConnection(dbURL, username, password);
                PreparedStatement preparedStatement = myCon.prepareStatement(query);
                PreparedStatement updateStatement = myCon.prepareStatement(updatequery);
                preparedStatement.setString(1, patForm.getStudentID());
                preparedStatement.setString(2, patForm.getStudentName());
                preparedStatement.setString(3, patForm.getTutorID());
                preparedStatement.setString(4, patForm.getTutorName());
                preparedStatement.setString(5, patForm.getNextMeetingDate());
                preparedStatement.setString(6, patForm.getStartTime());
                preparedStatement.setString(7, patForm.getEndTime());
                preparedStatement.setString(8, patForm.getSummary());
                preparedStatement.setString(9, patForm.getActionPoints());
                preparedStatement.setString(10, patForm.getStudentSig());
                preparedStatement.setString(11, patForm.getTutorSig());
                preparedStatement.setInt(12, appointmentID);
                updateStatement.setInt(1, 1);
                updateStatement.executeUpdate();
                preparedStatement.executeUpdate();
                System.out.println(patForm.getActionPoints());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            appointmentBoxController.setUploadBtnToUploaded();
            Stage stage = (Stage) saveBtn.getScene().getWindow();
            stage.close();
            System.out.println(appointmentID);
        });
    }

    public void populateFields(String studentID, String studentName, String tutorID, String[] tutorName) {
        studentIDTF.setText(studentID);
        studentNameTF.setText(studentName);
        tutorIDTF.setText(tutorID);
        tutorNameTF.setText(tutorName[0] + " " + tutorName[1]);
        populateCB();
    }

    public void loadFields(int appointmentID) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        ResultSet result;
        System.out.println(appointmentID);
        result = fetchStaff.executeQuery("SELECT * FROM pats WHERE appointment_id='" + appointmentID + "'");
        while (result.next()) {
            studentIDTF.setText(result.getString("student_id"));
            studentNameTF.setText(result.getString("student_name"));
            tutorIDTF.setText(result.getString("tutor_id"));
            tutorNameTF.setText(result.getString("tutor_name"));
            tutorSigTF.setText(result.getString("tutor_sig"));
            studentSigTF.setText(result.getString("student_sig"));
            summaryTA.setText(result.getString("summary"));
            actionPointsTA.setText(result.getString("action_points"));
            startCB.setValue(result.getString("start_time"));
            endCB.setValue(result.getString("end_time"));
            String date = result.getString("next_meeting_date");
            LocalDate meetingDate = LocalDate.parse(date);
            nextMeetingDP.setValue(meetingDate);
        }
        tutorSigTF.setEditable(false);
        studentSigTF.setEditable(false);
        summaryTA.setEditable(false);
        actionPointsTA.setEditable(false);
        startCB.setEditable(false);
        endCB.setEditable(false);
        nextMeetingDP.setEditable(false);
        saveBtn.setText("Close");
    }

    private void populateCB() {
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                startCB.getItems().add("0" + i + ":00");
                endCB.getItems().add("0" + i + ":00");
            } else {
                startCB.getItems().add(i + ":00");
                endCB.getItems().add(i + ":00");
            }
        }
    }
}