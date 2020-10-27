package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.*;

public class NewModuleWindowController {
    @FXML
    private TextField titleTF, creditTF, leaderTF, moduleTF;
    @FXML
    private ComboBox levelCB, startCB, endCB, courseCB;
    @FXML
    private TextArea descTA, aimTA;
    @FXML
    private Button saveBtn;
    private Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);

    public void createModule() throws SQLException {
        if(!verifyFilledInputFields()) {
            String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
            String username = "root";
            String password = "root";
            String query = "INSERT INTO modules(module_code, start_year, end_year, level, credits, title, leader, description, aims_and_objectives, course) VALUES (?,?,?,?,?,?,?,?,?,?)";
            Connection myConnection = DriverManager.getConnection(dbURL, username, password);
            PreparedStatement preparedStatement = myConnection.prepareStatement(query);
            preparedStatement.setString(1, moduleTF.getText());
            preparedStatement.setString(2, startCB.getValue().toString());
            preparedStatement.setString(3, endCB.getValue().toString());
            preparedStatement.setString(4, levelCB.getValue().toString());
            preparedStatement.setString(5, creditTF.getText());
            preparedStatement.setString(6, titleTF.getText());
            preparedStatement.setString(7, leaderTF.getText());
            preparedStatement.setString(8, descTA.getText());
            preparedStatement.setString(9, aimTA.getText());
            preparedStatement.setString(10, courseCB.getValue().toString());
            preparedStatement.executeUpdate();
            System.out.println("Module created!");
            Stage stage = (Stage) saveBtn.getScene().getWindow();
            stage.close();
        }
    }

    public void populateCB() throws SQLException {
        levelCB.getItems().addAll("4", "5", "6");
        populateCourse();
        populateYearCB();
    }

    private void populateYearCB() {
        for (int i = 2018; i<3000; i++) {
            startCB.getItems().addAll(String.valueOf(i));
            endCB.getItems().addAll(String.valueOf(i));
        }
    }

    public void generateCode() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        ResultSet result = fetchStaff.executeQuery("SELECT * FROM modules");

        String text = courseCB.getValue().toString();
        StringBuilder code = new StringBuilder();
        int amount = 1;
        while (result.next()) {
            ++amount;
        }
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '-') {
                break;
            } else {
                code.append(text.charAt(i));
            }
        }
        String genCode = code + "0" + amount + "-" + startCB.getValue().toString().charAt(2) + startCB.getValue().toString().charAt(3) + "-" + endCB.getValue().toString().charAt(2) +
                endCB.getValue().toString().charAt(3);
        moduleTF.setText(genCode);
    }

    private void populateCourse() throws SQLException {
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

    private boolean verifyFilledInputFields() {
        TextField[] inputForms = new TextField[] {
                titleTF, creditTF, leaderTF, moduleTF
        };
        ComboBox[] comboForms = new ComboBox[] {
                levelCB, startCB, endCB, courseCB
        };
        TextArea[] inputFormsTA = new TextArea[] {
                descTA, aimTA
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
        for (TextArea textArea : inputFormsTA) {
            if (textArea.getText().isEmpty()) {
                errorAlert.setTitle("Record entry failed.");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Some fields were left empty!");
                errorAlert.showAndWait();
                return true;
            }
        }
        return false;
    }

    public void checkForSave(ModuleTabController moduleTabController) {
        saveBtn.setOnAction((e)-> {
            try {
                createModule();
                moduleTabController.populate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void checkForSave() {
        saveBtn.setOnAction((e)-> {
            try {
                createModule();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}
