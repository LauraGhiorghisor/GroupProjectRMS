package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;

public class NewAssessmentWindowController {
    @FXML
    private TextField termTF, weightTF, briefTF;
    @FXML
    private ComboBox moduleCB, typeCB, statusCB, courseCB, yearCB;
    @FXML
    private Button saveBtn;
    private Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);

    public void createAssessment() throws SQLException {
        if(!verifyFilledInputFields()) {
            String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
            String username = "root";
            String password = "root";
            String query = "INSERT INTO assessments(module_code, type, term, weighting, brief, status, year, course_code) VALUES (?,?,?,?,?,?,?,?)";
            Connection myConnection = DriverManager.getConnection(dbURL, username, password);
            PreparedStatement preparedStatement = myConnection.prepareStatement(query);
            preparedStatement.setString(1, moduleCB.getValue().toString());
            preparedStatement.setString(2, typeCB.getValue().toString());
            preparedStatement.setString(3, termTF.getText());
            preparedStatement.setString(4, weightTF.getText());
            preparedStatement.setString(5, briefTF.getText());
            preparedStatement.setString(6, statusCB.getValue().toString());
            preparedStatement.setString(7, yearCB.getValue().toString());
            preparedStatement.setString(8, courseCB.getValue().toString());
            preparedStatement.executeUpdate();
            System.out.println("Course created!");
            Stage stage = (Stage) saveBtn.getScene().getWindow();
            stage.close();
        }
    }

    public void populateCB() throws SQLException {
        populateYearCB();
        populateModuleCB();
        populateCourseCB();
        populateTypeCB();
        populateStatusCB();
    }

    private void populateYearCB() {
        for (int i = 2018; i<3000; i++) {
            yearCB.getItems().addAll(String.valueOf(i));
        }
    }

    private void populateModuleCB() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        ResultSet result = fetchStaff.executeQuery("SELECT module_code FROM modules WHERE archived = 0");
        while (result.next()) {
            moduleCB.getItems().addAll(result.getString("module_code"));
        }
    }

    private void populateTypeCB() {
        typeCB.getItems().addAll("TCA", "Essay", "Assignment");
    }

    private void populateCourseCB() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        ResultSet result = fetchStaff.executeQuery("SELECT course_code FROM courses");
        while (result.next()) {
            courseCB.getItems().addAll(result.getString("course_code"));
        }
    }

    private void populateStatusCB() {
        statusCB.getItems().addAll("Pending", "Released", "Terminated");
    }

    private boolean verifyFilledInputFields() {
        TextField[] inputForms = new TextField[] {
                termTF, weightTF, briefTF
        };
        ComboBox[] comboForms = new ComboBox[] {
                moduleCB, typeCB, statusCB, courseCB, yearCB
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

    public void checkForSave(AssessmentTabController assessmentTabController) {
        saveBtn.setOnAction((e)-> {
            try {
                createAssessment();
                assessmentTabController.populate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void checkForSave() {
        saveBtn.setOnAction((e)-> {
            try {
                createAssessment();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}
