package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.print.Paper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.Assessment;
import model.TablePrinter;
import view.NewAssessmentWindow;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssessmentTabController {
    @FXML
    private TableView assessmentTV;
    @FXML
    private TableColumn idCol, courseCol, moduleCol, yearCol, termCol, typeCol, weightCol, statusCol, briefCol;
    @FXML
    private Button createBtn, archiveBtn;
    @FXML
    private ComboBox<String> courseCB, moduleCB, yearCB;

    public List<Assessment> fetchTable(Boolean isArchive) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        List<Assessment> assessments = new ArrayList<>();
        if (isArchive) {
            ResultSet result = fetchStaff.executeQuery("SELECT * FROM assessments WHERE archived = 1");
            while (result.next()) {
                int id = Integer.parseInt(result.getString("assessment_id"));
                String moduleCode = result.getString("module_code");
                String type = result.getString("type");
                String term = result.getString("term");
                float weight = Float.parseFloat(result.getString("weighting"));
                String brief = result.getString("brief");
                String status = result.getString("status");
                String year = result.getString("year");
                String courseCode = result.getString("course_code");

                assessments.add(new Assessment(id, courseCode, moduleCode, year, term, type, status, brief, weight));
            }
        } else {
            ResultSet result = fetchStaff.executeQuery("SELECT * FROM assessments WHERE archived = 0");
            while (result.next()) {
                int id = Integer.parseInt(result.getString("assessment_id"));
                String moduleCode = result.getString("module_code");
                String type = result.getString("type");
                String term = result.getString("term");
                float weight = Float.parseFloat(result.getString("weighting"));
                String brief = result.getString("brief");
                String status = result.getString("status");
                String year = result.getString("year");
                String courseCode = result.getString("course_code");

                assessments.add(new Assessment(id, courseCode, moduleCode, year, term, type, status, brief, weight));
            }
        }
        return assessments;
    }

    public List<Assessment> fetchTableByCourse(Boolean isArchive, String course) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        List<Assessment> assessments = new ArrayList<>();
        if (!isArchive) {
            ResultSet result = fetchStaff.executeQuery("SELECT * FROM assessments WHERE archived = 0 AND course_code ='" + course + "'");
            while (result.next()) {
                int id = Integer.parseInt(result.getString("assessment_id"));
                String moduleCode = result.getString("module_code");
                String type = result.getString("type");
                String term = result.getString("term");
                float weight = Float.parseFloat(result.getString("weighting"));
                String brief = result.getString("brief");
                String status = result.getString("status");
                String year = result.getString("year");
                String courseCode = result.getString("course_code");

                assessments.add(new Assessment(id, courseCode, moduleCode, year, term, type, status, brief, weight));
            }
        }
        return assessments;
    }

    public List<Assessment> fetchTableByModule(Boolean isArchive, String course, String module) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        List<Assessment> assessments = new ArrayList<>();
        ResultSet result;
        if (!isArchive) {
            if (course != null)
            result = fetchStaff.executeQuery("SELECT * FROM assessments WHERE archived = 0 AND course_code ='" + course + "' AND module_code ='" + module + "'");
            else
                result = fetchStaff.executeQuery("SELECT * FROM assessments WHERE archived = 0 AND module_code ='" + module + "'");
            while (result.next()) {
                int id = Integer.parseInt(result.getString("assessment_id"));
                String moduleCode = result.getString("module_code");
                String type = result.getString("type");
                String term = result.getString("term");
                float weight = Float.parseFloat(result.getString("weighting"));
                String brief = result.getString("brief");
                String status = result.getString("status");
                String year = result.getString("year");
                String courseCode = result.getString("course_code");

                assessments.add(new Assessment(id, courseCode, moduleCode, year, term, type, status, brief, weight));
            }
        }
        return assessments;
    }

    public void populateTable(List<Assessment> newAssessment) {
        idCol.setCellValueFactory(new PropertyValueFactory<Assessment, Integer>("id"));
        courseCol.setCellValueFactory(new PropertyValueFactory<Assessment, String>("course"));
        moduleCol.setCellValueFactory(new PropertyValueFactory<Assessment, String>("module"));
        yearCol.setCellValueFactory(new PropertyValueFactory<Assessment, String>("year"));
        termCol.setCellValueFactory(new PropertyValueFactory<Assessment, String>("term"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Assessment, String>("type"));
        weightCol.setCellValueFactory(new PropertyValueFactory<Assessment, Float>("weighting"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Assessment, String>("status"));
        briefCol.setCellValueFactory(new PropertyValueFactory<Assessment, String>("brief"));

        idCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        courseCol.setCellFactory(TextFieldTableCell.forTableColumn());
        moduleCol.setCellFactory(TextFieldTableCell.forTableColumn());
        yearCol.setCellFactory(TextFieldTableCell.forTableColumn());
        termCol.setCellFactory(TextFieldTableCell.forTableColumn());
        typeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        weightCol.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        statusCol.setCellFactory(TextFieldTableCell.forTableColumn());
        briefCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ObservableList<Assessment> rows = FXCollections.observableArrayList();
        for (Assessment assessment : newAssessment) {
            rows.add(assessment);
        }
        assessmentTV.setItems(rows);
        assessmentTV.setEditable(true);
        assessmentTV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        editColumns();
    }

    public void populate() throws SQLException {
        List<Assessment> newAssessment = fetchTable(false);
        populateTable(newAssessment);
    }

    public void populateByCourse(String course) throws SQLException {
        List<Assessment> newAssessment = fetchTableByCourse(false, course);
        populateTable(newAssessment);
    }

    public void populateArchive() throws SQLException {
        List<Assessment> newAssessment = fetchTable(true);
        populateTable(newAssessment);
        createBtn.setVisible(false);
        archiveBtn.setVisible(false);
    }

    public void populateCourseCB(int id, String courseVal) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        ResultSet result;
        if (id == 4) {
            courseCB.getItems().add(courseVal);
            courseCB.setValue(courseVal);
        }
        else {
            result = fetchStaff.executeQuery("SELECT DISTINCT course_code FROM courses");
            while (result.next()) {
                courseCB.getItems().add(result.getString("course_code"));
            }
        }
    }

    public void populateModuleCB(int id, String courseVal) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        ResultSet result;
        if (id == 4)
            result = fetchStaff.executeQuery("SELECT module_code FROM modules WHERE course ='" + courseVal + "' AND archived = 0");
        else
            result = fetchStaff.executeQuery("SELECT DISTINCT module_code FROM modules");
        while (result.next()) {
            moduleCB.getItems().add(result.getString("module_code"));
        }
    }

    public void populateYearCB() {
        for (int i = 2018; i<3000; i++) {
            yearCB.getItems().addAll(String.valueOf(i));
        }
    }

    public void initializeEventListeners() {
        courseCB.setOnAction(e-> {
            List<Assessment> newAssessment = null;
            try {
                newAssessment = fetchTableByCourse(false, courseCB.getValue());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            populateTable(newAssessment);
        });

        moduleCB.setOnAction(e-> {
            List<Assessment> newAssessment = null;
            try {
                newAssessment = fetchTableByModule(false, courseCB.getValue(), moduleCB.getValue());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            populateTable(newAssessment);
        });
    }

    public void editColumns() {
        idCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Assessment, Integer>>) assessmentIntegerCellEditEvent -> ((Assessment) assessmentIntegerCellEditEvent.getTableView().getItems().get(assessmentIntegerCellEditEvent.getTablePosition().getRow())).setId(assessmentIntegerCellEditEvent.getNewValue()));
        moduleCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Assessment, String>>) assessmentStringCellEditEvent -> ((Assessment) assessmentStringCellEditEvent.getTableView().getItems().get(assessmentStringCellEditEvent.getTablePosition().getRow())).setModule(assessmentStringCellEditEvent.getNewValue()));
        typeCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Assessment, String>>) assessmentStringCellEditEvent -> ((Assessment) assessmentStringCellEditEvent.getTableView().getItems().get(assessmentStringCellEditEvent.getTablePosition().getRow())).setType(assessmentStringCellEditEvent.getNewValue()));
        termCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Assessment, String>>) assessmentStringCellEditEvent -> ((Assessment) assessmentStringCellEditEvent.getTableView().getItems().get(assessmentStringCellEditEvent.getTablePosition().getRow())).setTerm(assessmentStringCellEditEvent.getNewValue()));
        weightCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Assessment, Float>>) assessmentFloatCellEditEvent -> ((Assessment) assessmentFloatCellEditEvent.getTableView().getItems().get(assessmentFloatCellEditEvent.getTablePosition().getRow())).setWeighting(assessmentFloatCellEditEvent.getNewValue()));
        briefCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Assessment, String>>) assessmentStringCellEditEvent -> ((Assessment) assessmentStringCellEditEvent.getTableView().getItems().get(assessmentStringCellEditEvent.getTablePosition().getRow())).setBrief(assessmentStringCellEditEvent.getNewValue()));
        statusCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Assessment, String>>) assessmentStringCellEditEvent -> ((Assessment) assessmentStringCellEditEvent.getTableView().getItems().get(assessmentStringCellEditEvent.getTablePosition().getRow())).setStatus(assessmentStringCellEditEvent.getNewValue()));
        yearCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Assessment, String>>) assessmentStringCellEditEvent -> ((Assessment) assessmentStringCellEditEvent.getTableView().getItems().get(assessmentStringCellEditEvent.getTablePosition().getRow())).setYear(assessmentStringCellEditEvent.getNewValue()));
        courseCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Assessment, String>>) assessmentStringCellEditEvent -> ((Assessment) assessmentStringCellEditEvent.getTableView().getItems().get(assessmentStringCellEditEvent.getTablePosition().getRow())).setCourse(assessmentStringCellEditEvent.getNewValue()));
    }

    public void updateTable() throws SQLException {
        Assessment assessment;
        List<List<String>> assessmentList = new ArrayList<>();
        for (int i = 0; i < assessmentTV.getItems().size(); i++) {
            assessment = (Assessment) assessmentTV.getItems().get(i);
            assessmentList.add(new ArrayList<>());
            assessmentList.get(i).add(assessment.getCourse());
            assessmentList.get(i).add(assessment.getModule());
            assessmentList.get(i).add(assessment.getYear());
            assessmentList.get(i).add(assessment.getTerm());
            assessmentList.get(i).add(assessment.getType());
            assessmentList.get(i).add(Float.toString(assessment.getWeighting()));
            assessmentList.get(i).add(assessment.getStatus());
            assessmentList.get(i).add(assessment.getBrief());

            String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
            String username = "root";
            String password = "root";
            String query = ("UPDATE assessments SET module_code='" + assessment.getModule() + "', type='" + assessment.getType() + "', term='" + assessment.getTerm() +
                    "', weighting='" + assessment.getWeighting() + "', brief='" + assessment.getBrief() + "', status='" + assessment.getStatus() +
                    "', year='" + assessment.getYear() + "', course_code='" + assessment.getCourse() + "'WHERE assessment_id='" + assessment.getId() + "'");
            Connection myCon = DriverManager.getConnection(dbURL, username, password);
            PreparedStatement preparedStatement = myCon.prepareStatement(query);
            preparedStatement.execute();
        }
        System.out.println("Saved!");
    }

    public void removeCourse() throws SQLException {
        Object selectedItems = assessmentTV.getSelectionModel().getSelectedItems().get(0);
        String dbUrl = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = ("DELETE FROM assessments WHERE assessment_id ='" + idCol.getCellData(selectedItems) + "'");
        Connection myConnection = DriverManager.getConnection(dbUrl, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        preparedStatement.execute();
        ObservableList<Assessment> allRows, singleRow;
        allRows = assessmentTV.getItems();
        singleRow = assessmentTV.getSelectionModel().getSelectedItems();
        singleRow.forEach(allRows::remove);
        System.out.println("Removed selected items!");
    }

    public void archiveAssessment() throws SQLException {
        Object selectedItems = assessmentTV.getSelectionModel().getSelectedItems().get(0);
        String dbUrl = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = ("UPDATE assessments SET archived= 1 WHERE assessment_id ='" + idCol.getCellData(selectedItems) + "'");
        Connection myConnection = DriverManager.getConnection(dbUrl, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        preparedStatement.execute();
        populate();
    }

    public void printTable() {
        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.print(Paper.A4, assessmentTV);
    }

    public void openNewAssessmentWindow() throws IOException, SQLException {
        new NewAssessmentWindow(this);
    }
}
