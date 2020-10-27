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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import model.Course;
import model.Module;
import model.TablePrinter;
import view.NewCourseWindow;
import view.NewModuleWindow;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseTabController {
    @FXML
    private TableView courseTV;
    @FXML
    private TableColumn idCol, codeCol, nameCol, startCol, endCol, descCol, aimCol, leaderCol;
    @FXML
    private Button createBtn, archiveBtn;

    public List<Course> fetchTable(boolean isArchive) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        List<Course> courses = new ArrayList<>();
        if(isArchive) {
            ResultSet result = fetchStaff.executeQuery("SELECT * FROM courses WHERE archived = 1");
            while (result.next()) {
                int id = Integer.parseInt(result.getString("course_id"));
                String code = result.getString("course_code");
                String title = result.getString("course_title");
                String start = result.getString("start_year");
                String end = result.getString("end_year");
                String desc = result.getString("description");
                String aims = result.getString("aims_and_objectives");
                int leader = Integer.parseInt(result.getString("course_leader"));
                courses.add(new Course(id, leader, code, title, start, end, desc, aims));
            }
        } else {
            ResultSet result = fetchStaff.executeQuery("SELECT * FROM courses WHERE archived = 0");
            while (result.next()) {
                int id = Integer.parseInt(result.getString("course_id"));
                String code = result.getString("course_code");
                String title = result.getString("course_title");
                String start = result.getString("start_year");
                String end = result.getString("end_year");
                String desc = result.getString("description");
                String aims = result.getString("aims_and_objectives");
                int leader = Integer.parseInt(result.getString("course_leader"));
                courses.add(new Course(id, leader, code, title, start, end, desc, aims));
            }
        }
        return courses;
    }

    public void populateTable(List<Course> newCourse) {
        idCol.setCellValueFactory(new PropertyValueFactory<Course, Integer>("id"));
        codeCol.setCellValueFactory(new PropertyValueFactory<Course, String>("code"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
        leaderCol.setCellValueFactory(new PropertyValueFactory<Course, Integer>("leader"));
        startCol.setCellValueFactory(new PropertyValueFactory<Course, String>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<Course, String>("end"));
        descCol.setCellValueFactory(new PropertyValueFactory<Course, String>("description"));
        aimCol.setCellValueFactory(new PropertyValueFactory<Course, String>("aims"));

        idCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        codeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        leaderCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        startCol.setCellFactory(TextFieldTableCell.forTableColumn());
        endCol.setCellFactory(TextFieldTableCell.forTableColumn());
        descCol.setCellFactory(TextFieldTableCell.forTableColumn());
        aimCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ObservableList<Course> rows = FXCollections.observableArrayList();
        for (Course course : newCourse) {
            rows.add(course);
        }
        courseTV.setItems(rows);
        courseTV.setEditable(true);
        courseTV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        editColumns();
    }

    public void populate() throws SQLException {
        List<Course> newCourse = fetchTable(false);
        populateTable(newCourse);
    }
    public void populateArchive() throws SQLException {
        List<Course> newCourse = fetchTable(true);
        populateTable(newCourse);
        createBtn.setVisible(false);
        archiveBtn.setVisible(false);
    }

    public void editColumns() {
        idCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Course, Integer>>) courseIntegerCellEditEvent -> ((Course) courseIntegerCellEditEvent.getTableView().getItems().get(courseIntegerCellEditEvent.getTablePosition().getRow())).setId(courseIntegerCellEditEvent.getNewValue()));
        codeCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Course, String>>) courseStringCellEditEvent -> ((Course) courseStringCellEditEvent.getTableView().getItems().get(courseStringCellEditEvent.getTablePosition().getRow())).setCode(courseStringCellEditEvent.getNewValue()));
        nameCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Course, String>>) courseStringCellEditEvent -> ((Course) courseStringCellEditEvent.getTableView().getItems().get(courseStringCellEditEvent.getTablePosition().getRow())).setName(courseStringCellEditEvent.getNewValue()));
        leaderCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Course, Integer>>) courseIntegerCellEditEvent -> ((Course) courseIntegerCellEditEvent.getTableView().getItems().get(courseIntegerCellEditEvent.getTablePosition().getRow())).setLeader(courseIntegerCellEditEvent.getNewValue()));
        startCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Course, String>>) courseStringCellEditEvent -> ((Course) courseStringCellEditEvent.getTableView().getItems().get(courseStringCellEditEvent.getTablePosition().getRow())).setStart(courseStringCellEditEvent.getNewValue()));
        endCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Course, String>>) courseStringCellEditEvent -> ((Course) courseStringCellEditEvent.getTableView().getItems().get(courseStringCellEditEvent.getTablePosition().getRow())).setEnd(courseStringCellEditEvent.getNewValue()));
        descCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Course, String>>) courseStringCellEditEvent -> ((Course) courseStringCellEditEvent.getTableView().getItems().get(courseStringCellEditEvent.getTablePosition().getRow())).setDescription(courseStringCellEditEvent.getNewValue()));
        aimCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Course, String>>) courseStringCellEditEvent -> ((Course) courseStringCellEditEvent.getTableView().getItems().get(courseStringCellEditEvent.getTablePosition().getRow())).setAims(courseStringCellEditEvent.getNewValue()));
    }

    public void updateTable() throws SQLException {
        Course course;
        List <List<String>> courseList = new ArrayList<>();
        for (int i = 0; i < courseTV.getItems().size(); i++) {
            course = (Course) courseTV.getItems().get(i);
            courseList.add(new ArrayList<>());
            courseList.get(i).add(course.getCode());
            courseList.get(i).add(course.getName());
            courseList.get(i).add(Integer.toString(course.getLeader()));
            courseList.get(i).add(course.getStart());
            courseList.get(i).add(course.getEnd());
            courseList.get(i).add(course.getDescription());
            courseList.get(i).add(course.getAims());

            String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
            String username = "root";
            String password = "root";
            String query = ("UPDATE courses SET course_code='" + course.getCode() + "', course_title='" + course.getName() + "', start_year='" + course.getStart() +
                    "', end_year='" + course.getEnd() + "', description='" + course.getDescription() + "', aims_and_objectives='" + course.getAims() +
                    "', course_leader='" + course.getLeader() + "'WHERE course_id='" + course.getId() + "'");
            Connection myCon = DriverManager.getConnection(dbURL, username, password);
            PreparedStatement preparedStatement = myCon.prepareStatement(query);
            preparedStatement.execute();
        }
        System.out.println("Saved!");
    }

    public void removeCourse() throws SQLException {
        Object selectedItems = courseTV.getSelectionModel().getSelectedItems().get(0);
        String dbUrl = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = ("DELETE FROM courses WHERE course_id ='" + idCol.getCellData(selectedItems) + "'");
        Connection myConnection = DriverManager.getConnection(dbUrl, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        preparedStatement.execute();
        ObservableList<Course> allRows, singleRow;
        allRows = courseTV.getItems();
        singleRow = courseTV.getSelectionModel().getSelectedItems();
        singleRow.forEach(allRows::remove);
        System.out.println("Removed selected items!");
    }

    public void archiveCourse() throws SQLException {
        Object selectedItems = courseTV.getSelectionModel().getSelectedItems().get(0);
        String dbUrl = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = ("UPDATE courses SET archived= 1 WHERE course_id ='" + idCol.getCellData(selectedItems) + "'");
        Connection myConnection = DriverManager.getConnection(dbUrl, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        preparedStatement.execute();
        populate();
    }

    public void printTable() {
        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.print(Paper.A4, courseTV);
    }

    public void openNewCourseWindow() throws IOException, SQLException {
        new NewCourseWindow(this);
    }

}
