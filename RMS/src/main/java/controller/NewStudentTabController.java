package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.sql.*;

public class NewStudentTabController {
    @FXML
    private TextField idTF, firstNameTF, midNameTF, surnameTF, passwordTF, termNTF, termSTF, termCountryTF, termHNTF,
            termTTF, termZTF, homeNTF, homeSTF, homeCountryTF, homeHNTF, homeTTF, homeZTF, currCourseTF, ptIDTF,
            emergPhoneTF, emergEmailTF, employerTF, allergyTF, homeCountyTF, termCountyTF, pictureTF;
    @FXML
    private ComboBox genderCB, currYearCB, enrolYearCB, statusCB, entryQCB1, entryQCB2, entryQCB3, dormCB;
    @FXML
    private Button pictureBtn, saveBtn, mediSaveBtn;
    @FXML
    private TextArea addNoteTF, religionTF, medicalTF;
    @FXML
    private ImageView imageIV, pictureIV;
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

    public void createStudent() throws SQLException, IOException {
        if(!verifyFilledInputFields()) {
            if (idTF.getText().equalsIgnoreCase("automatically generated")) {
                FileInputStream imageFile = new FileInputStream(pictureTF.getText());
                String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
                String username = "root";
                String password = "root";
                String query = "INSERT INTO students(password, status, dormancy_reason, first_name, middle_name, surname, image, gender, telephone, current_course_code, current_year, enrollment_year, entry_qualifications, emergency_contact_phone, emergency_contact_email, employer, additional_notes, personal_tutor, medical_history, medical_allergies, medical_religious, address_term_number, address_term_house_name, address_term_street, address_term_town, address_term_county, address_term_country, " +
                        "address_term_zip_code, noterm_address_number, noterm_address_house_name, noterm_address_street, noterm_address_town, noterm_address_county, noterm_address_country, noterm_zip_code, email) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                Connection myConnection = DriverManager.getConnection(dbURL, username, password);
                PreparedStatement preparedStatement = myConnection.prepareStatement(query);
                preparedStatement.setString(1, passwordTF.getText());
                preparedStatement.setString(2, statusCB.getValue().toString());
                if (!statusCB.getValue().toString().equals("Dormant")) {
                    preparedStatement.setString(3, "Inactive");
                } else {
                    preparedStatement.setString(3, dormCB.getValue().toString());
                }
                preparedStatement.setString(4, firstNameTF.getText());
                preparedStatement.setString(5, midNameTF.getText());
                preparedStatement.setString(6, surnameTF.getText());
                preparedStatement.setBinaryStream(7, imageFile, imageFile.available());
                preparedStatement.setString(8, genderCB.getValue().toString());
                preparedStatement.setString(9, termHNTF.getText());
                preparedStatement.setString(10, currCourseTF.getText());
                preparedStatement.setInt(11, Integer.parseInt(currYearCB.getValue().toString()));
                preparedStatement.setInt(12, Integer.parseInt(enrolYearCB.getValue().toString()));
                preparedStatement.setString(13, entryQCB1.getValue().toString() + " " + entryQCB2.getValue().toString() + " " + entryQCB3.getValue().toString());
                preparedStatement.setString(14, emergPhoneTF.getText());
                preparedStatement.setString(15, emergEmailTF.getText());
                preparedStatement.setString(16, employerTF.getText());
                preparedStatement.setString(17, addNoteTF.getText());
                preparedStatement.setInt(18, Integer.parseInt(ptIDTF.getText()));
                preparedStatement.setString(19, medicalTF.getText());
                preparedStatement.setString(20, allergyTF.getText());
                preparedStatement.setString(21, religionTF.getText());
                preparedStatement.setInt(22, Integer.parseInt(termNTF.getText()));
                preparedStatement.setString(23, termHNTF.getText());
                preparedStatement.setString(24, termSTF.getText());
                preparedStatement.setString(25, termTTF.getText());
                preparedStatement.setString(26, termCountyTF.getText());
                preparedStatement.setString(27, termCountryTF.getText());
                preparedStatement.setString(28, termZTF.getText());
                preparedStatement.setInt(29, Integer.parseInt(homeNTF.getText()));
                preparedStatement.setString(30, homeHNTF.getText());
                preparedStatement.setString(31, homeSTF.getText());
                preparedStatement.setString(32, homeTTF.getText());
                preparedStatement.setString(33, homeCountyTF.getText());
                preparedStatement.setString(34, homeCountryTF.getText());
                preparedStatement.setString(35, homeZTF.getText());
                preparedStatement.setString(36, emergEmailTF.getText());
                preparedStatement.executeUpdate();
                System.out.println("Student Created!");
                Stage stage = (Stage) saveBtn.getScene().getWindow();
                stage.close();
            } else {
                updateStudent();
            }
        }
    }

    private void populateYearCB() {
        for (int i = 2018; i<3000; i++) {
            enrolYearCB.getItems().addAll(String.valueOf(i));
        }
    }

    public void populateComboBoxes() {
        genderCB.getItems().addAll("M", "F");
        currYearCB.getItems().addAll("1", "2", "3");
        statusCB.getItems().addAll("Provisional", "Live", "Dormant");
        dormCB.getItems().addAll("Graduated", "Withdrawn", "Terminated");
        entryQCB1.getItems().addAll("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "E", "F", "G");
        entryQCB2.getItems().addAll("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "E", "F", "G");
        entryQCB3.getItems().addAll("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "E", "F", "G");
        populateYearCB();
    }

    private boolean verifyFilledInputFields() {
        TextField[] inputForms = new TextField[] {
                firstNameTF, midNameTF, surnameTF, passwordTF, termNTF, termSTF, termCountryTF, termHNTF,
                termTTF, termZTF, homeNTF, homeSTF, homeCountryTF, homeHNTF, homeTTF, homeZTF, currCourseTF, ptIDTF,
                emergPhoneTF, emergEmailTF, employerTF, allergyTF, homeCountyTF, termCountyTF
        };
        ComboBox[] comboForms = new ComboBox[] {
                genderCB, currYearCB, enrolYearCB, statusCB, entryQCB1, entryQCB2, entryQCB3, dormCB
        };
        TextArea[] inputFormsTA = new TextArea[] {
                addNoteTF, religionTF, medicalTF
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
        if(statusCB.getValue().toString().equals("Dormant") && dormCB.getValue().toString().equals("Inactive")) {
            errorAlert.setTitle("Record entry failed.");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Entry cannot be Dormant and Inactive at the same time!");
            errorAlert.showAndWait();
            return true;
        }
        return false;
    }

    public void checkForSave(StudentTabController studentTabController) {
        saveBtn.setOnAction((e)-> {
            try {
                createStudent();
                studentTabController.populate(null);
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void checkForSave() {
        saveBtn.setOnAction((e)-> {
            try {
                createStudent();
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void fetchStudentForEdit(int id) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection myConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStudent = myConnection.createStatement();
        ResultSet getStudent = fetchStudent.executeQuery("SELECT * FROM students WHERE student_id='" + id + "'");
        while (getStudent.next()) {
            idTF.setText(getStudent.getString("student_id"));
            firstNameTF.setText(getStudent.getString("first_name"));
            midNameTF.setText(getStudent.getString("middle_name"));
            surnameTF.setText(getStudent.getString("surname"));
            termNTF.setText(getStudent.getString("address_term_number"));
            termSTF.setText(getStudent.getString("address_term_street"));
            termCountryTF.setText(getStudent.getString("address_term_country"));
            termHNTF.setText(getStudent.getString("address_term_house_name"));
            termTTF.setText(getStudent.getString("address_term_town"));
            termZTF.setText(getStudent.getString("address_term_zip_code").toUpperCase());
            homeNTF.setText(getStudent.getString("noterm_address_number"));
            homeSTF.setText(getStudent.getString("noterm_address_street"));
            homeCountryTF.setText(getStudent.getString("noterm_address_country"));
            homeHNTF.setText(getStudent.getString("noterm_address_house_name"));
            homeTTF.setText(getStudent.getString("noterm_address_town"));
            homeZTF.setText(getStudent.getString("noterm_zip_code"));
            currCourseTF.setText(getStudent.getString("current_course_code"));
            ptIDTF.setText(getStudent.getString("personal_tutor"));
            emergPhoneTF.setText(getStudent.getString("emergency_contact_phone"));
            emergEmailTF.setText(getStudent.getString("emergency_contact_email"));
            employerTF.setText(getStudent.getString("employer"));
            allergyTF.setText(getStudent.getString("medical_allergies"));
            homeCountyTF.setText(getStudent.getString("noterm_address_county"));
            termCountyTF.setText(getStudent.getString("address_term_county"));
            genderCB.setValue(getStudent.getString("gender").toUpperCase());
            currYearCB.setValue(getStudent.getString("current_year"));
            enrolYearCB.setValue(getStudent.getString("enrollment_year"));
            statusCB.setValue(getStudent.getString("status"));
            String[] line;
            line = getStudent.getString("entry_qualifications").split(" ");
            entryQCB1.setValue(line[0].toUpperCase());
            entryQCB2.setValue(line[1].toUpperCase());
            entryQCB3.setValue(line[2].toUpperCase());
            dormCB.setValue(getStudent.getString("dormancy_reason"));
            addNoteTF.setText(getStudent.getString("additional_notes"));
            religionTF.setText(getStudent.getString("medical_religious"));
            medicalTF.setText(getStudent.getString("medical_history"));
            InputStream imageStream = getStudent.getBinaryStream("image");
            Image picture = new Image(imageStream);
            imageIV.setImage(picture);
        }
    }

    public void updateStudent() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = ("UPDATE students SET personal_tutor='" + ptIDTF.getText() + "', password='" + passwordTF.getText() + "', status='" + statusCB.getValue().toString() + "', dormancy_reason='" + dormCB.getValue().toString() +
                "', first_name='" + firstNameTF.getText() + "', middle_name='" + midNameTF.getText() + "', surname='" + surnameTF.getText() + "', gender='" + genderCB.getValue().toString() +
                "', telephone='" + emergPhoneTF.getText() + "', current_course_code='" + currCourseTF.getText() + "', current_year='" + currYearCB.getValue().toString() + "', enrollment_year='" + enrolYearCB.getValue().toString() +
                "', entry_qualifications='" + entryQCB1.getValue().toString() + " " + entryQCB2.getValue().toString() + " " + entryQCB3.getValue().toString() + "', emergency_contact_phone='" + emergPhoneTF.getText() + "', emergency_contact_email='" + emergEmailTF.getText() +
                "', employer='" + employerTF.getText() + "', additional_notes='" + addNoteTF.getText() + "', personal_tutor='" + ptIDTF.getText() + "', medical_history='" + medicalTF.getText() +
                "', medical_allergies='" + allergyTF.getText() + "', medical_religious='" + religionTF.getText() + "', address_term_number='" + termNTF.getText() + "', address_term_house_name='" + termHNTF.getText() +
                "', address_term_street='" + termSTF.getText() + "', address_term_town='" + termTTF.getText() + "', address_term_county='" + termCountyTF.getText() + "', address_term_country='" + termCountryTF.getText() +
                "', address_term_zip_code='" + termZTF.getText() + "', noterm_address_number='" + homeNTF.getText() + "', noterm_address_house_name='" + homeHNTF.getText() + "', noterm_address_street='" + homeSTF.getText() +
                "', noterm_address_town='" + homeTTF.getText() + "', noterm_address_county='" + homeCountyTF.getText() + "', noterm_address_country='" + homeCountryTF.getText() + "', noterm_zip_code='" + homeZTF.getText() +
                "', email='" + emergEmailTF.getText() + "' WHERE student_id='" + idTF.getText() + "'");
        Connection myCon = DriverManager.getConnection(dbURL, username, password);
        PreparedStatement preparedStatement = myCon.prepareStatement(query);
        preparedStatement.execute();
        System.out.println("Student Updated!");
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }
}
