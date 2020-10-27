package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import model.TablePrinter;
import model.student.Student;
import view.NewStaffWindow;
import view.NewStudentWindow;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentTabController {
    @FXML
    private TableView studentTV;
    @FXML
    private TableColumn idCol, pwCol, statusCol, currCourseCol, currYearCol, firstNCol, lastNCol, midNCol,
            genderCol, phoneCol, emailCol;
    @FXML
    private Button createBtn, archiveBtn;

    public List<Student> fetchTable(Boolean isArchive, String searchID) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String fetchQuery = ("SELECT * FROM students");
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        List<Student> students = new ArrayList<>();
        ResultSet result;
        if(isArchive) {
            if (searchID == null)
            result = fetchStaff.executeQuery("SELECT * FROM students WHERE archived = 1");
            else
                result = fetchStaff.executeQuery("SELECT * FROM students WHERE archived = 1 AND student_id='" + searchID + "'");
            while (result.next()) {
                int id = Integer.parseInt(result.getString("student_id"));
                String pw = result.getString("password");
                String status = result.getString("status");
                String dorm = result.getString("dormancy_reason");
                String firstName = result.getString("first_name");
                String midName = result.getString("middle_name");
                String surname = result.getString("surname");
                String image = result.getString("image");
                String gender = result.getString("gender");
                String telephone = result.getString("telephone");
                String currCourse = result.getString("current_course_code");
                int currYear = Integer.parseInt(result.getString("current_year"));
                int enrollYear = Integer.parseInt(result.getString("enrollment_year"));
                String entQual = result.getString("entry_qualifications");
                String emPhone = result.getString("emergency_contact_phone");
                String emEmail = result.getString("emergency_contact_email");
                String employer = result.getString("employer");
                String addNotes = result.getString("additional_notes");
                int tutor = Integer.parseInt(result.getString("personal_tutor"));
                String mediHistory = result.getString("medical_history");
                String allergies = result.getString("medical_allergies");
                String religious = result.getString("medical_religious");
                String email = result.getString("email");
                String termNumber = (result.getString("address_term_number"));
                String termHouseName = result.getString("address_term_house_name");
                String termHouseStreet = result.getString("address_term_street");
                String termHouseTown = result.getString("address_term_town");
                String termHouseCounty = result.getString("address_term_county");
                String termHouseCountry = result.getString("address_term_country");
                String termHouseZip = result.getString("address_term_zip_code");
                String homeNumber = (result.getString("noterm_address_number"));
                String homeHouseName = result.getString("noterm_address_house_name");
                String homeHouseStreet = result.getString("noterm_address_street");
                String homeHouseTown = result.getString("noterm_address_town");
                String homeHouseCounty = result.getString("noterm_address_county");
                String homeHouseCountry = result.getString("noterm_address_country");
                String homeHouseZip = result.getString("noterm_zip_code");
                students.add(new Student(id, tutor, pw, status, dorm, firstName, midName, surname, image, gender, telephone, currCourse, currYear, enrollYear, entQual, emPhone,
                        emEmail, employer, addNotes, mediHistory, allergies, religious, termNumber, termHouseName, termHouseStreet, termHouseTown,
                        termHouseCounty, termHouseCountry, termHouseZip, homeNumber, homeHouseName, homeHouseStreet, homeHouseTown, homeHouseCounty
                        , homeHouseCountry, homeHouseZip, email));
            }
        } else {
            if (searchID == null)
                result = fetchStaff.executeQuery("SELECT * FROM students WHERE archived = 0");
            else
                result = fetchStaff.executeQuery("SELECT * FROM students WHERE archived = 0 AND student_id='" + searchID + "'");
            while (result.next()) {
                int id = Integer.parseInt(result.getString("student_id"));
                String pw = result.getString("password");
                String status = result.getString("status");
                String dorm = result.getString("dormancy_reason");
                String firstName = result.getString("first_name");
                String midName = result.getString("middle_name");
                String surname = result.getString("surname");
                String image = result.getString("image");
                String gender = result.getString("gender");
                String telephone = result.getString("telephone");
                String currCourse = result.getString("current_course_code");
                int currYear = Integer.parseInt(result.getString("current_year"));
                int enrollYear = Integer.parseInt(result.getString("enrollment_year"));
                String entQual = result.getString("entry_qualifications");
                String emPhone = result.getString("emergency_contact_phone");
                String emEmail = result.getString("emergency_contact_email");
                String employer = result.getString("employer");
                String addNotes = result.getString("additional_notes");
                int tutor = Integer.parseInt(result.getString("personal_tutor"));
                String mediHistory = result.getString("medical_history");
                String allergies = result.getString("medical_allergies");
                String religious = result.getString("medical_religious");
                String email = result.getString("email");
                String termNumber = (result.getString("address_term_number"));
                String termHouseName = result.getString("address_term_house_name");
                String termHouseStreet = result.getString("address_term_street");
                String termHouseTown = result.getString("address_term_town");
                String termHouseCounty = result.getString("address_term_county");
                String termHouseCountry = result.getString("address_term_country");
                String termHouseZip = result.getString("address_term_zip_code");
                String homeNumber = (result.getString("noterm_address_number"));
                String homeHouseName = result.getString("noterm_address_house_name");
                String homeHouseStreet = result.getString("noterm_address_street");
                String homeHouseTown = result.getString("noterm_address_town");
                String homeHouseCounty = result.getString("noterm_address_county");
                String homeHouseCountry = result.getString("noterm_address_country");
                String homeHouseZip = result.getString("noterm_zip_code");
                students.add(new Student(id, tutor, pw, status, dorm, firstName, midName, surname, image, gender, telephone, currCourse, currYear, enrollYear, entQual, emPhone,
                        emEmail, employer, addNotes, mediHistory, allergies, religious, termNumber, termHouseName, termHouseStreet, termHouseTown,
                        termHouseCounty, termHouseCountry, termHouseZip, homeNumber, homeHouseName, homeHouseStreet, homeHouseTown, homeHouseCounty
                        , homeHouseCountry, homeHouseZip, email));
            }
        }
        return students;
    }

    public void populateTable(List<Student> newStudents) {
        idCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        pwCol.setCellValueFactory(new PropertyValueFactory<Student, String>("password"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Student, String>("status"));
        currCourseCol.setCellValueFactory(new PropertyValueFactory<Student, String>("currCourseCode"));
        currYearCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("currYear"));
        firstNCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        lastNCol.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        midNCol.setCellValueFactory(new PropertyValueFactory<Student, String>("middleName"));
        genderCol.setCellValueFactory(new PropertyValueFactory<Student, String>("gender"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Student, String>("phoneNumber"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));

        idCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        pwCol.setCellFactory(TextFieldTableCell.forTableColumn());
        statusCol.setCellFactory(TextFieldTableCell.forTableColumn());
        currCourseCol.setCellFactory(TextFieldTableCell.forTableColumn());
        currYearCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        firstNCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNCol.setCellFactory(TextFieldTableCell.forTableColumn());
        midNCol.setCellFactory(TextFieldTableCell.forTableColumn());
        genderCol.setCellFactory(ComboBoxTableCell.forTableColumn("M", "F"));
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ObservableList<Student> rows = FXCollections.observableArrayList();
        for (Student student : newStudents) {
            rows.add(student);
        }
        studentTV.setItems(rows);
        studentTV.setEditable(true);
        studentTV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        editColumns();
    }

    public void populate(String id) throws SQLException {
        List<Student> newStudent = fetchTable(false, id);
        populateTable(newStudent);
    }
    public void populateArchive(String id) throws SQLException {
        List<Student> newStudent = fetchTable(true, id);
        populateTable(newStudent);
        createBtn.setVisible(false);
        archiveBtn.setVisible(false);
    }

    public void editColumns() {
        idCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, Integer>>) studentIntegerCellEditEvent -> ((Student) studentIntegerCellEditEvent.getTableView().getItems().get(studentIntegerCellEditEvent.getTablePosition().getRow())).setId(studentIntegerCellEditEvent.getNewValue()));
        pwCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setPassword(studentStringCellEditEvent.getNewValue()));
        statusCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setStatus(studentStringCellEditEvent.getNewValue()));
        currCourseCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setCurrCourseCode(studentStringCellEditEvent.getNewValue()));
        currYearCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, Integer>>) studentIntegerCellEditEvent -> ((Student) studentIntegerCellEditEvent.getTableView().getItems().get(studentIntegerCellEditEvent.getTablePosition().getRow())).setCurrYear(studentIntegerCellEditEvent.getNewValue()));
        firstNCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setFirstName(studentStringCellEditEvent.getNewValue()));
        lastNCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setSurname(studentStringCellEditEvent.getNewValue()));
        midNCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setMiddleName(studentStringCellEditEvent.getNewValue()));
        genderCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setGender(studentStringCellEditEvent.getNewValue()));
        phoneCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setPhoneNumber(studentStringCellEditEvent.getNewValue()));
        emailCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Student, String>>) studentStringCellEditEvent -> ((Student) studentStringCellEditEvent.getTableView().getItems().get(studentStringCellEditEvent.getTablePosition().getRow())).setEmail(studentStringCellEditEvent.getNewValue()));
    }

    public void updateTableEntries() throws SQLException {
        Student student = new Student();
        List <List<String>> sList = new ArrayList<>();
        for (int i = 0; i < studentTV.getItems().size(); i++) {
            student = (Student) studentTV.getItems().get(i);
            sList.add(new ArrayList<>());
            sList.get(i).add(Integer.toString(student.getId()));//
            sList.get(i).add(student.getPassword());//
            sList.get(i).add(student.getStatus());//
            sList.get(i).add(student.getFirstName());//
            sList.get(i).add(student.getMiddleName());//
            sList.get(i).add(student.getSurname());//
            sList.get(i).add(student.getGender());//
            sList.get(i).add(student.getPhoneNumber());//
            sList.get(i).add(student.getCurrCourseCode());//
            sList.get(i).add(Integer.toString(student.getCurrYear()));//
            sList.get(i).add(student.getEmail());//

            int getStudentID = student.getId();
            String getPass = student.getPassword();
            String getStatus = student.getStatus();
            String getFirstName = student.getFirstName();
            String getMidName = student.getMiddleName();
            String getSurname = student.getSurname();
            String getGender = student.getGender();
            String getTelephone = student.getPhoneNumber();
            String getCurrCourseCode = student.getCurrCourseCode();
            int getCurrYear = student.getCurrYear();
            String getEmail = student.getEmail();
            if (student.isEmpty()) {
                Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                errorAlert.setTitle("Record entry failed.");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Some fields were left empty!");
                errorAlert.showAndWait();
                populate(null);
                break;
            } else {
                String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
                String username = "root";
                String password = "root";
                Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
                Statement fetchStaff = rmsConnection.createStatement();
                String query = ("UPDATE students SET password='" + getPass + "', status='" + getStatus +
                        "', first_name='" + getFirstName + "', middle_name='" + getMidName + "', surname='" + getSurname + "', gender='" + getGender +
                        "', telephone='" + getTelephone + "', current_course_code='" + getCurrCourseCode + "', current_year='" + getCurrYear +
                        "', email='" + getEmail + "' WHERE student_id='" + getStudentID + "'");
                Connection myConnection = DriverManager.getConnection(dbURL, username, password);
                PreparedStatement preparedStatement = myConnection.prepareStatement(query);
                preparedStatement.execute();
            }
            System.out.println("Saved!");
        }
    }

    public void createStudent() throws IOException, SQLException {
        new NewStudentWindow(this, 0);
    }

    public void removeStudent() throws SQLException {
        Object selectedItems = studentTV.getSelectionModel().getSelectedItems().get(0);
        String dbUrl = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = ("DELETE FROM students WHERE student_id ='" + idCol.getCellData(selectedItems) + "'");
        Connection myConnection = DriverManager.getConnection(dbUrl, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        preparedStatement.execute();
        ObservableList<Student> allRows, singleRow;
        allRows = studentTV.getItems();
        singleRow = studentTV.getSelectionModel().getSelectedItems();
        singleRow.forEach(allRows::remove);
    }

    public void archiveStudent() throws SQLException {
        Object selectedItems = studentTV.getSelectionModel().getSelectedItems().get(0);
        String dbUrl = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = ("UPDATE students SET archived= 1 WHERE student_id ='" + idCol.getCellData(selectedItems) + "'");
        Connection myConnection = DriverManager.getConnection(dbUrl, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        preparedStatement.execute();
        populate(null);
    }

    public void printTable() {
        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.print(Paper.A3, studentTV);
    }

    public void editStudent() throws IOException, SQLException {
        Object selectedItems = studentTV.getSelectionModel().getSelectedItems().get(0);
        int id = (int) idCol.getCellData(selectedItems);
        new NewStudentWindow(this, id);
    }
}

