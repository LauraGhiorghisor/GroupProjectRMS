package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Assessment;
import model.Booking;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class BookingController {
    @FXML
    private TableView bookingTV;
    @FXML
    private TableColumn roomCol, dateCol, timeCol, descCol;
    @FXML
    private ComboBox<String> roomCB, startCB, endCB;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField descTF;
    private Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);

    public void populateCB() {
        for (int i = 100; i < 300; i++) {
            roomCB.getItems().add("Room " + i);
        }
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

    private void repopulateCB() {
        startCB.getItems().clear();
        endCB.getItems().clear();
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

    public List<Booking> fetchBookings() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        List<Booking> bookings = new ArrayList<>();
        ResultSet result = fetchStaff.executeQuery("SELECT * FROM bookings");
        while (result.next()) {
            int id = Integer.parseInt(result.getString("id"));
            String room = result.getString("room");
            String start = result.getString("start");
            String end = result.getString("end");
            String date = result.getString("date");
            String desc = result.getString("description");
            bookings.add(new Booking(id, room, date, start, end, desc, "0"));
        }
        return bookings;
    }

    public void populateTable(List<Booking> bookingList) {
        roomCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("room"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("time"));
        descCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("description"));

        roomCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        timeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        descCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ObservableList<Booking> rows = FXCollections.observableArrayList();
        rows.addAll(bookingList);
        bookingTV.setItems(rows);
    }

    public void displayTable() throws SQLException {
        List<Booking> bookingList = fetchBookings();
        populateTable(bookingList);
    }

    public void refine() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        String formattedString = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        ResultSet result = fetchStaff.executeQuery("SELECT * FROM bookings WHERE room ='" + roomCB.getValue() + "' AND date='" + formattedString + "'");
        repopulateCB();
        while (result.next()) {
            String[] start = result.getString("start").split("");
            String[] end = result.getString("end").split("");
            ObservableList<String> startTimes = startCB.getItems();
            ObservableList<String> endTimes = endCB.getItems();
            String startCheck = start[0] + start[1];
            String endCheck = end[0] + end[1];
            startTimes.removeIf(value -> value.contains(startCheck));
            endTimes.removeIf(value -> value.contains(endCheck));
        }
    }

    public void addBooking() throws SQLException {
        if (!verifyFields()) {
            String[] startTime = startCB.getValue().split(":");
            String[] endTime = endCB.getValue().split(":");
            String startVal = startTime[0] + startTime[1];
            String endVal = endTime[0] + endTime[1];
            String formattedDate = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
            String username = "root";
            String password = "root";
            String query = "INSERT INTO bookings(room, start, end, date, description) VALUES (?,?,?,?,?)";
            Connection myConnection = DriverManager.getConnection(dbURL, username, password);
            PreparedStatement preparedStatement = myConnection.prepareStatement(query);
            preparedStatement.setString(1, roomCB.getValue());
            preparedStatement.setString(2, startVal);
            preparedStatement.setString(3, endVal);
            preparedStatement.setString(4, formattedDate);
            preparedStatement.setString(5, descTF.getText());
            preparedStatement.executeUpdate();
            System.out.println("Booking created!");
            displayTable();
        }
    }

    private boolean verifyFields() {
        if (roomCB.getValue() == null) {
            errorAlert.setTitle("Booking entry failed.");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Some fields were left empty!");
            errorAlert.showAndWait();
            return true;
        }
        if (datePicker.getValue() == null) {
            errorAlert.setTitle("Booking entry failed.");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Some fields were left empty!");
            errorAlert.showAndWait();
            return true;
        }
        if (startCB.getValue() == null) {
            errorAlert.setTitle("Booking entry failed.");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Some fields were left empty!");
            errorAlert.showAndWait();
            return true;
        }
        if (endCB.getValue() == null) {
            errorAlert.setTitle("Booking entry failed.");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Some fields were left empty!");
            errorAlert.showAndWait();
            return true;
        }
        return false;
    }
}
