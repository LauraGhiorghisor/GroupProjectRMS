package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.sql.*;

public class NewStaffTabController {
    @FXML
    private TextField idTF, firstNameTF, middleNameTF, surnameTF, pwTF, addNumberTF, houseNTF, houseSTF, houseTTF, countyTF, countryTF, zipTF, phoneTF, emailTF,
            emergPTF, emergETF, pictureTF;
    @FXML
    private ComboBox genderCB, statusCB, dormCB, specialismCB, courseCB;
    @FXML
    private TextArea allergyTA, religiousTA, addNoteTA, medicalHTA;
    @FXML
    private Button saveBtn;
    @FXML
    private ImageView imageIV;
    @FXML
    private AnchorPane mainPane;
    private Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);

    public void handleUpload() throws FileNotFoundException {
        FileChooser imgChooser = new FileChooser();
        File photo = imgChooser.showOpenDialog(mainPane.getScene().getWindow());
        pictureTF.setText(photo.getAbsolutePath());
        FileInputStream imageFileStream = new FileInputStream(photo.getAbsoluteFile());
        Image image = new Image(imageFileStream);
        imageIV.setImage(image);
    }

    public void createStaff() throws SQLException, IOException {
        if (!verifyFilledInputFields()) {
            if (idTF.getText().equalsIgnoreCase("automatically generated")) {
                FileInputStream imageFile = new FileInputStream(pictureTF.getText());
                String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
                String username = "root";
                String password = "root";
                String query = "INSERT INTO staff(status, dormancy_reason, first_name, middle_name, surname, gender, address_number, address_house_name, address_street, address_town, address_county, " +
                        "address_country, zip_code, telephone, email_address, emergency_contact_phone, emergency_contact_email, specialist_subject, resume, additional_notes, medical_history, medical_allergies, medical_religious, image) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                Connection myConnection = DriverManager.getConnection(dbURL, username, password);
                PreparedStatement preparedStatement = myConnection.prepareStatement(query);
                preparedStatement.setString(1, statusCB.getValue().toString());
                if (!statusCB.getValue().toString().equals("Dormant")) {
                    preparedStatement.setString(2, "Inactive");
                } else {
                    preparedStatement.setString(2, dormCB.getValue().toString());
                }
                preparedStatement.setString(3, firstNameTF.getText());
                preparedStatement.setString(4, middleNameTF.getText());
                preparedStatement.setString(5, surnameTF.getText());
                preparedStatement.setString(6, genderCB.getValue().toString());
                preparedStatement.setString(7, addNumberTF.getText());
                preparedStatement.setString(8, houseNTF.getText());
                preparedStatement.setString(9, houseSTF.getText());
                preparedStatement.setString(10, houseTTF.getText());
                preparedStatement.setString(11, countyTF.getText());
                preparedStatement.setString(12, countryTF.getText());
                preparedStatement.setString(13, zipTF.getText());
                preparedStatement.setString(14, phoneTF.getText());
                preparedStatement.setString(15, emailTF.getText());
                preparedStatement.setString(16, emergPTF.getText());
                preparedStatement.setString(17, emergETF.getText());
                preparedStatement.setString(18, specialismCB.getValue().toString());
                preparedStatement.setString(19, "resume");
                preparedStatement.setString(20, addNoteTA.getText());
                preparedStatement.setString(21, medicalHTA.getText());
                preparedStatement.setString(22, allergyTA.getText());
                preparedStatement.setString(23, religiousTA.getText());
                preparedStatement.setBinaryStream(24, imageFile, imageFile.available());
                preparedStatement.executeUpdate();
                Statement fetchStaff = myConnection.createStatement();
                ResultSet getStaff = fetchStaff.executeQuery("SELECT * FROM staff WHERE first_name='" + firstNameTF.getText() + "'AND surname='" + surnameTF.getText() + "'");
                String newUser = "INSERT INTO users(username, password, user_level, course, staff_id) VALUES (?,?,?,?,?)";
                PreparedStatement newUserStatement = myConnection.prepareStatement(newUser);
                newUserStatement.setString(1, firstNameTF.getText() + "" + surnameTF.getText());
                newUserStatement.setString(2, firstNameTF.getText() + "" + surnameTF.getText());
                newUserStatement.setInt(3, 4);
                newUserStatement.setString(4, courseCB.getValue().toString());
                while (getStaff.next()) {
                    newUserStatement.setString(5, String.valueOf(getStaff.getInt("staff_id")));
                }
                newUserStatement.execute();
                System.out.println("Staff Created!");
                Stage stage = (Stage) saveBtn.getScene().getWindow();
                stage.close();
            } else {
                updateStaff();
            }
        }
    }

    public void populateCB() throws SQLException {
        genderCB.getItems().addAll("M", "F");
        statusCB.getItems().addAll("Provisional", "Live", "Dormant");
        dormCB.getItems().addAll("Graduated", "Withdrawn", "Terminated", "Inactive");
        specialismCB.getItems().addAll("Databases", "AI", "Software Development", "Web Development", "Systems");
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
        TextField[] inputForms = new TextField[]{
                firstNameTF, middleNameTF, surnameTF, pwTF, addNumberTF, houseNTF, houseSTF, houseTTF, countyTF, countryTF, zipTF, phoneTF, emailTF,
                emergPTF, emergETF};
        ComboBox[] comboForms = new ComboBox[]{
                genderCB, statusCB, dormCB, specialismCB
        };
        TextArea[] inputFormsTA = new TextArea[]{
                allergyTA, religiousTA, addNoteTA, medicalHTA
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
        if (statusCB.getValue().toString().equals("Dormant") && dormCB.getValue().toString().equals("Inactive")) {
            errorAlert.setTitle("Record entry failed.");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Entry cannot be Dormant and Inactive at the same time!");
            errorAlert.showAndWait();
            return true;
        }
        return false;
    }

    public void checkForSave(StaffTabController staffTabController) {
        saveBtn.setOnAction((e) -> {
            try {
                createStaff();
                staffTabController.populate(null);
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void checkForSave() {
        saveBtn.setOnAction((e) -> {
            try {
                createStaff();
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void fetchStaffForEdit(int id) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection myConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = myConnection.createStatement();
        ResultSet getStaff = fetchStaff.executeQuery("SELECT * FROM staff WHERE staff_id='" + id + "'");
        while (getStaff.next()) {
            idTF.setText(getStaff.getString("staff_id"));
            firstNameTF.setText(getStaff.getString("first_name"));
            middleNameTF.setText(getStaff.getString("middle_name"));
            surnameTF.setText(getStaff.getString("surname"));
            addNumberTF.setText(getStaff.getString("address_number"));
            houseNTF.setText(getStaff.getString("address_house_name"));
            houseSTF.setText(getStaff.getString("address_street"));
            houseTTF.setText(getStaff.getString("address_town"));
            countyTF.setText(getStaff.getString("address_county"));
            countryTF.setText(getStaff.getString("address_country"));
            zipTF.setText(getStaff.getString("zip_code").toUpperCase());
            phoneTF.setText(getStaff.getString("telephone"));
            emailTF.setText(getStaff.getString("email_address"));
            emergPTF.setText(getStaff.getString("emergency_contact_phone"));
            emergETF.setText(getStaff.getString("emergency_contact_email"));
            genderCB.setValue(getStaff.getString("gender").toUpperCase());
            statusCB.setValue(getStaff.getString("status"));
            dormCB.setValue(getStaff.getString("dormancy_reason"));
            specialismCB.setValue(getStaff.getString("specialist_subject"));
            allergyTA.setText(getStaff.getString("medical_allergies"));
            religiousTA.setText(getStaff.getString("medical_religious"));
            addNoteTA.setText(getStaff.getString("additional_notes"));
            medicalHTA.setText(getStaff.getString("medical_history"));
            courseCB.setValue("Computing");
            InputStream imageStream = getStaff.getBinaryStream("image");
            Image picture = new Image(imageStream);
            imageIV.setImage(picture);
        }
    }

    public void updateStaff() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = ("UPDATE staff SET status='" + statusCB.getValue().toString() + "', dormancy_reason='" + dormCB.getValue().toString() + "', first_name='" + firstNameTF.getText() + "', middle_name='" +
                middleNameTF.getText() + "', surname='" + surnameTF.getText() + "', gender='" + genderCB.getValue().toString() + "', address_number='" + addNumberTF.getText() + "', address_house_name='" +
                houseNTF.getText() + "', address_street='" + houseSTF.getText() + "', address_town='" + houseTTF.getText() + "', address_county='" + countyTF.getText() +
                "', address_country='" + countryTF.getText() + "', zip_code='" + zipTF.getText() + "', telephone='" + phoneTF.getText() + "', email_address='" + emailTF.getText() +
                "', emergency_contact_phone='" + emergPTF.getText() + "', emergency_contact_email='" + emergETF.getText() + "', specialist_subject='" + specialismCB.getValue().toString() +
                "', resume='" + "resume" + "', additional_notes='" + addNoteTA.getText() + "', medical_history='" + medicalHTA.getText() + "', medical_allergies='" +
                allergyTA.getText() + "', medical_religious='" + religiousTA.getText() + "' WHERE staff_id='" + idTF.getText() + "'");
        Connection myCon = DriverManager.getConnection(dbURL, username, password);
        PreparedStatement preparedStatement = myCon.prepareStatement(query);
        preparedStatement.execute();
        System.out.println("Staff Updated!");
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }
}
