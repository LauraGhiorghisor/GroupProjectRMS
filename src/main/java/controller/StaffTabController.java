package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.print.Paper;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import model.Staff;
import model.TablePrinter;
import view.NewStaffWindow;
import view.NewStudentWindow;

import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffTabController {
    @FXML
    private TableView staffTV;
    @FXML
    private TableColumn staffIDCol, firstNameCol, lastNameCol, middleNameCol, genderCol, phoneCol, emailCol, specialistCol;
    @FXML
    private Button createBtn, archiveBtn;

    public List<Staff> fetchTable(Boolean isArchive, String searchID) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        List<Staff> staff = new ArrayList<>();
        ResultSet result;
        if (isArchive) {
            if (searchID == null)
                result = fetchStaff.executeQuery("SELECT * FROM staff WHERE archived = 1");
            else
                result = fetchStaff.executeQuery("SELECT * FROM staff WHERE archived = 1 AND staff_id='" + searchID + "'");
            while (result.next()) {
                int id = Integer.parseInt(result.getString("staff_id"));
                String status = result.getString("status");
                String dormReason = result.getString("dormancy_reason");
                String firstName = result.getString("first_name");
                String middleName = result.getString("middle_name");
                String surname = result.getString("surname");
                String gender = result.getString("gender");
                String addNumber = result.getString("address_number");
                String houseName = result.getString("address_house_name");
                String street = result.getString("address_street");
                String town = result.getString("address_town");
                String county = result.getString("address_county");
                String country = result.getString("address_country");
                String zip = result.getString("zip_code");
                String telephone = result.getString("telephone");
                String email = result.getString("email_address");
                String emergP = result.getString("emergency_contact_phone");
                String emergE = result.getString("emergency_contact_email");
                String specialism = result.getString("specialist_subject");
                String resume = result.getString("resume");
                String notes = result.getString("additional_notes");
                String medicalH = result.getString("medical_history");
                String medicalA = result.getString("medical_allergies");
                String medicalR = result.getString("medical_religious");
                String image = result.getString("image");
                staff.add(new Staff(id, status, dormReason, firstName, middleName, surname,
                        gender, addNumber, houseName, street, town, county, country,
                        zip, telephone, email, emergP, emergE, specialism, resume, notes, medicalH,
                        medicalA, medicalR, image));
            }
        } else {
            if (searchID == null)
                result = fetchStaff.executeQuery("SELECT * FROM staff WHERE archived = 0");
            else
                result = fetchStaff.executeQuery("SELECT * FROM staff WHERE archived = 0 AND staff_id='" + searchID + "'");
            while (result.next()) {
                int id = Integer.parseInt(result.getString("staff_id"));
                String status = result.getString("status");
                String dormReason = result.getString("dormancy_reason");
                String firstName = result.getString("first_name");
                String middleName = result.getString("middle_name");
                String surname = result.getString("surname");
                String gender = result.getString("gender");
                String addNumber = result.getString("address_number");
                String houseName = result.getString("address_house_name");
                String street = result.getString("address_street");
                String town = result.getString("address_town");
                String county = result.getString("address_county");
                String country = result.getString("address_country");
                String zip = result.getString("zip_code");
                String telephone = result.getString("telephone");
                String email = result.getString("email_address");
                String emergP = result.getString("emergency_contact_phone");
                String emergE = result.getString("emergency_contact_email");
                String specialism = result.getString("specialist_subject");
                String resume = result.getString("resume");
                String notes = result.getString("additional_notes");
                String medicalH = result.getString("medical_history");
                String medicalA = result.getString("medical_allergies");
                String medicalR = result.getString("medical_religious");
                String image = result.getString("image");
                staff.add(new Staff(id, status, dormReason, firstName, middleName, surname,
                        gender, addNumber, houseName, street, town, county, country,
                        zip, telephone, email, emergP, emergE, specialism, resume, notes, medicalH,
                        medicalA, medicalR, image));
            }
        }
        return staff;
    }

    public void populateTable(List<Staff> newStaff) {
        staffIDCol.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("staffID"));//
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("firstName"));//
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("surname"));//
        middleNameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("middleName"));//
        genderCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("gender"));//
        phoneCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("telephone"));//
        emailCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("email"));//
        specialistCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("specialism"));//

        staffIDCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        middleNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        genderCol.setCellFactory(ComboBoxTableCell.forTableColumn("M", "F"));
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        specialistCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ObservableList<Staff> rows = FXCollections.observableArrayList();
        for (Staff staff : newStaff) {
            rows.add(staff);
        }
        staffTV.setItems(rows);
        staffTV.setEditable(true);
        staffTV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        editColumns();
    }

    public void populate(String id) throws SQLException {
        List<Staff> newStaff = fetchTable(false, id);
        populateTable(newStaff);
    }

    public void populateArchive() throws SQLException {
        List<Staff> newStaff = fetchTable(true, null);
        populateTable(newStaff);
        createBtn.setVisible(false);
        archiveBtn.setVisible(false);
    }

    public void editColumns() {
        staffIDCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, Integer>>) staffIntegerCellEditEvent -> ((Staff) staffIntegerCellEditEvent.getTableView().getItems().get(staffIntegerCellEditEvent.getTablePosition().getRow())).setStaffID(staffIntegerCellEditEvent.getNewValue()));
        firstNameCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setFirstName(staffStringCellEditEvent.getNewValue()));
        lastNameCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setSurname(staffStringCellEditEvent.getNewValue()));
        middleNameCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setMiddleName(staffStringCellEditEvent.getNewValue()));
        genderCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setGender(staffStringCellEditEvent.getNewValue()));
        phoneCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setTelephone(staffStringCellEditEvent.getNewValue()));
        emailCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setEmail(staffStringCellEditEvent.getNewValue()));
        specialistCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Staff, String>>) staffStringCellEditEvent -> ((Staff) staffStringCellEditEvent.getTableView().getItems().get(staffStringCellEditEvent.getTablePosition().getRow())).setSpecialism(staffStringCellEditEvent.getNewValue()));
    }

    public void updateTable() throws SQLException {
        Staff staff;
        List<List<String>> staffList = new ArrayList<>();
        for (int i = 0; i < staffTV.getItems().size(); i++) {
            staff = (Staff) staffTV.getItems().get(i);
            staffList.add(new ArrayList<>());
            staffList.get(i).add(Integer.toString(staff.getStaffID()));
            staffList.get(i).add(staff.getFirstName());
            staffList.get(i).add(staff.getMiddleName());
            staffList.get(i).add(staff.getSurname());
            staffList.get(i).add(staff.getGender());
            staffList.get(i).add(staff.getTelephone());
            staffList.get(i).add(staff.getEmail());
            staffList.get(i).add(staff.getSpecialism());

            String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
            String username = "root";
            String password = "root";
            String query = ("UPDATE staff SET first_name='" + staff.getFirstName() + "', middle_name='" +
                    staff.getMiddleName() + "', surname='" + staff.getSurname() + "', gender='" + staff.getGender() + "', telephone='" + staff.getTelephone() + "', email_address='" + staff.getEmail() + "', specialist_subject='" + staff.getSpecialism() + "' WHERE staff_id='" + staff.getStaffID() + "'");
            Connection myCon = DriverManager.getConnection(dbURL, username, password);
            PreparedStatement preparedStatement = myCon.prepareStatement(query);
            preparedStatement.execute();
        }
        System.out.println("Saved!");
    }

    public void removeStaff() throws SQLException {
        Object selectedItems = staffTV.getSelectionModel().getSelectedItems().get(0);
        String dbUrl = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = ("DELETE FROM staff WHERE staff_id ='" + staffIDCol.getCellData(selectedItems) + "'");
        Connection myConnection = DriverManager.getConnection(dbUrl, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        preparedStatement.execute();
        ObservableList<Staff> allRows, singleRow;
        allRows = staffTV.getItems();
        singleRow = staffTV.getSelectionModel().getSelectedItems();
        singleRow.forEach(allRows::remove);
        System.out.println("Removed selected items!");
    }

    public void openNewStaffTab() throws IOException, SQLException {
        new NewStaffWindow(this, 0);
    }

    public void archiveStaff() throws SQLException {
        Object selectedItems = staffTV.getSelectionModel().getSelectedItems().get(0);
        String dbUrl = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = ("UPDATE staff SET archived= 1 WHERE staff_id ='" + staffIDCol.getCellData(selectedItems) + "'");
        Connection myConnection = DriverManager.getConnection(dbUrl, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        preparedStatement.execute();
        populate(null);
    }

    public void printTable() {
        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.print(Paper.A4, staffTV);
    }

    public void editStaff() throws IOException, SQLException {
        Object selectedItems = staffTV.getSelectionModel().getSelectedItems().get(0);
        int id = (int) staffIDCol.getCellData(selectedItems);
        new NewStaffWindow(this, id);
    }
}
