package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.Period;

public class NewGradeWindowController {
    @FXML
    private TextField studentTF, weightingTF;
    @FXML
    private ComboBox moduleCB, yearCB, firstCB, secondCB, finalCB, termCB, assessmentCB;
    @FXML
    private Button saveBtn;
    private Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);

    public void createGrade() throws SQLException {
        if (!verifyFilledInputFields()) {
            String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
            String username = "root";
            String password = "root";
            String query = "INSERT INTO grades(student_id, assessment_id, file, first_marking, second_marking, final_grade, year, module_code, weighting, term) VALUES (?,?,?,?,?,?,?,?,?,?)";
            Connection myConnection = DriverManager.getConnection(dbURL, username, password);
            PreparedStatement preparedStatement = myConnection.prepareStatement(query);
            preparedStatement.setString(1, studentTF.getText());
            preparedStatement.setString(2, assessmentCB.getValue().toString());
            preparedStatement.setString(3, "file");
            preparedStatement.setString(4, firstCB.getValue().toString());
            preparedStatement.setString(5, secondCB.getValue().toString());
            preparedStatement.setString(6, finalCB.getValue().toString());
            preparedStatement.setString(7, yearCB.getValue().toString());
            preparedStatement.setString(8, moduleCB.getValue().toString());
            preparedStatement.setString(9, String.valueOf(Float.parseFloat(weightingTF.getText())));
            preparedStatement.setString(10, termCB.getValue().toString());
            preparedStatement.executeUpdate();
            System.out.println("Grade created!");
            Stage stage = (Stage) saveBtn.getScene().getWindow();
            stage.close();
        }
    }

    public void populateCB() throws SQLException {
        populateYearCB();
        populateModuleCB();
        populateAssessmentCB();
        firstCB.getItems().addAll("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "E", "F", "G");
        secondCB.getItems().addAll("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "E", "F", "G");
        finalCB.getItems().addAll("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "E", "F", "G");
        termCB.getItems().addAll("1", "2", "3", "4");
    }

    private void populateModuleCB() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        ResultSet result = fetchStaff.executeQuery("SELECT module_code FROM modules");
        while (result.next()) {
            moduleCB.getItems().addAll(result.getString("module_code"));
        }
    }

    private void populateAssessmentCB() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        ResultSet result = fetchStaff.executeQuery("SELECT assessment_id FROM assessments");
        while (result.next()) {
            assessmentCB.getItems().addAll(result.getString("assessment_id"));
        }
    }
    private void populateYearCB() {
        for (int i = 2018; i<3000; i++) {
            yearCB.getItems().addAll(String.valueOf(i));
        }
    }

    private boolean verifyFilledInputFields() {
        TextField[] inputForms = new TextField[] {
                studentTF, weightingTF
        };
        ComboBox[] comboForms = new ComboBox[] {
                moduleCB, yearCB, firstCB, secondCB, finalCB, termCB, assessmentCB
        };
        for (TextField inputForm : inputForms) {
            if (inputForm.getText() == null || inputForm.getText().trim().isEmpty()) {
                errorAlert.setTitle("Record entry failed.");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Some fields were left empty!");
                errorAlert.showAndWait();
                return true;
            }
        }
        for (ComboBox comboBox : comboForms) {
            if (comboBox.getValue() == null) {
                errorAlert.setTitle("Record entry failed.");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Some fields were left empty!");
                errorAlert.showAndWait();
                return true;
            }
        }
        return false;
    }

    public void newRecordCheck(GradesTabController gradesTabController) {
        saveBtn.setOnAction((e)-> {
            try {
                createGrade();
                gradesTabController.populate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void newRecordCheck() {
        saveBtn.setOnAction((e)-> {
            try {
                createGrade();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}

