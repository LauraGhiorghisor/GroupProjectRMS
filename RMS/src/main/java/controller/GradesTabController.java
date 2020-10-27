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
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.Assessment;
import model.Grade;
import model.TablePrinter;
import view.NewAssessmentWindow;
import view.NewGradeWindow;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradesTabController {
    @FXML
    private TableView gradesTV;
    @FXML
    private TableColumn idCol, yearCol, studentCol, moduleCol, firstMarkCol, secondMarkCol, finalGradeCol, termCol, weightCol, fileCol, assessmentCol;
    @FXML
    private Button createBtn, archiveBtn;

    public List<Grade> fetchTable(boolean isArchive) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        List<Grade> grades = new ArrayList<>();
        if(isArchive) {
            ResultSet result = fetchStaff.executeQuery("SELECT * FROM grades WHERE archived = 1");
            while (result.next()) {
                int id = Integer.parseInt(result.getString("grade_id"));
                int studentID = Integer.parseInt(result.getString("student_id"));
                int assessmentID = Integer.parseInt(result.getString("assessment_id"));
                String file = result.getString("file");
                String first = result.getString("first_marking");
                String second = result.getString("second_marking");
                String finalG = result.getString("final_grade");
                String year = result.getString("year");
                String module = result.getString("module_code");
                String term = result.getString("term");
                float weight = Float.parseFloat(result.getString("weighting"));

                grades.add(new Grade(id, studentID, assessmentID, first, second, finalG, year, module, term, weight, file));
            }
        } else {
            ResultSet result = fetchStaff.executeQuery("SELECT * FROM grades WHERE archived = 0");
            while (result.next()) {
                int id = Integer.parseInt(result.getString("grade_id"));
                int studentID = Integer.parseInt(result.getString("student_id"));
                int assessmentID = Integer.parseInt(result.getString("assessment_id"));
                String file = result.getString("file");
                String first = result.getString("first_marking");
                String second = result.getString("second_marking");
                String finalG = result.getString("final_grade");
                String year = result.getString("year");
                String module = result.getString("module_code");
                String term = result.getString("term");
                float weight = Float.parseFloat(result.getString("weighting"));

                grades.add(new Grade(id, studentID, assessmentID, first, second, finalG, year, module, term, weight, file));
            }
        }
        return grades;
    }


    public void populateTable(List<Grade> newGrade) {
        idCol.setCellValueFactory(new PropertyValueFactory<Grade, Integer>("id"));
        yearCol.setCellValueFactory(new PropertyValueFactory<Grade, String>("year"));
        studentCol.setCellValueFactory(new PropertyValueFactory<Grade, Integer>("studentID"));
        moduleCol.setCellValueFactory(new PropertyValueFactory<Grade, String>("module"));
        firstMarkCol.setCellValueFactory(new PropertyValueFactory<Grade, String>("first"));
        secondMarkCol.setCellValueFactory(new PropertyValueFactory<Grade, String>("second"));
        finalGradeCol.setCellValueFactory(new PropertyValueFactory<Grade, Float>("finalG"));
        termCol.setCellValueFactory(new PropertyValueFactory<Grade, String>("term"));
        weightCol.setCellValueFactory(new PropertyValueFactory<Grade, Float>("weight"));
        fileCol.setCellValueFactory(new PropertyValueFactory<Grade, String>("file"));
        assessmentCol.setCellValueFactory(new PropertyValueFactory<Grade, Integer>("assessmentID"));

        idCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        yearCol.setCellFactory(TextFieldTableCell.forTableColumn());
        studentCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        firstMarkCol.setCellFactory(TextFieldTableCell.forTableColumn());
        secondMarkCol.setCellFactory(TextFieldTableCell.forTableColumn());
        finalGradeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        termCol.setCellFactory(TextFieldTableCell.forTableColumn());
        weightCol.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        fileCol.setCellFactory(TextFieldTableCell.forTableColumn());
        assessmentCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ObservableList<Grade> rows = FXCollections.observableArrayList();
        for (Grade grade : newGrade) {
            rows.add(grade);
        }
        gradesTV.setItems(rows);
        gradesTV.setEditable(true);
        gradesTV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        editColumns();
    }

    public void populate() throws SQLException {
        List<Grade> newGrade = fetchTable(false);
        populateTable(newGrade);
    }
    public void populateTutor() throws SQLException {
        List<Grade> newGrade = fetchTable(false);
        populateTable(newGrade);
        createBtn.setVisible(false);
        archiveBtn.setVisible(false);
    }
    public void populateArchive() throws SQLException {
        List<Grade> newGrade = fetchTable(true);
        populateTable(newGrade);
        createBtn.setVisible(false);
        archiveBtn.setVisible(false);
    }

    public void editColumns() {
        idCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Grade, Integer>>) gradeIntegerCellEditEvent -> ((Grade) gradeIntegerCellEditEvent.getTableView().getItems().get(gradeIntegerCellEditEvent.getTablePosition().getRow())).setId(gradeIntegerCellEditEvent.getNewValue()));
        yearCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Grade, String>>) gradeStringCellEditEvent -> ((Grade) gradeStringCellEditEvent.getTableView().getItems().get(gradeStringCellEditEvent.getTablePosition().getRow())).setYear(gradeStringCellEditEvent.getNewValue()));
        studentCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Grade, Integer>>) gradeIntegerCellEditEvent -> ((Grade) gradeIntegerCellEditEvent.getTableView().getItems().get(gradeIntegerCellEditEvent.getTablePosition().getRow())).setStudentID(gradeIntegerCellEditEvent.getNewValue()));
        firstMarkCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Grade, String>>) gradeStringCellEditEvent -> ((Grade) gradeStringCellEditEvent.getTableView().getItems().get(gradeStringCellEditEvent.getTablePosition().getRow())).setFirst(gradeStringCellEditEvent.getNewValue()));
        secondMarkCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Grade, String>>) gradeStringCellEditEvent -> ((Grade) gradeStringCellEditEvent.getTableView().getItems().get(gradeStringCellEditEvent.getTablePosition().getRow())).setSecond(gradeStringCellEditEvent.getNewValue()));
        finalGradeCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Grade, String>>) gradeStringCellEditEvent -> ((Grade) gradeStringCellEditEvent.getTableView().getItems().get(gradeStringCellEditEvent.getTablePosition().getRow())).setFinalG(gradeStringCellEditEvent.getNewValue()));
        termCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Grade, String>>) gradeStringCellEditEvent -> ((Grade) gradeStringCellEditEvent.getTableView().getItems().get(gradeStringCellEditEvent.getTablePosition().getRow())).setTerm(gradeStringCellEditEvent.getNewValue()));
        weightCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Grade, Float>>) gradeFloatCellEditEvent -> ((Grade) gradeFloatCellEditEvent.getTableView().getItems().get(gradeFloatCellEditEvent.getTablePosition().getRow())).setWeight(gradeFloatCellEditEvent.getNewValue()));
        fileCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Grade, String>>) gradeStringCellEditEvent -> ((Grade) gradeStringCellEditEvent.getTableView().getItems().get(gradeStringCellEditEvent.getTablePosition().getRow())).setFile(gradeStringCellEditEvent.getNewValue()));
        assessmentCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Grade, Integer>>) gradeIntegerCellEditEvent -> ((Grade) gradeIntegerCellEditEvent.getTableView().getItems().get(gradeIntegerCellEditEvent.getTablePosition().getRow())).setAssessmentID(gradeIntegerCellEditEvent.getNewValue()));
    }

    public void updateTable() throws SQLException {
        Grade grade;
        List <List<String>> gradeList = new ArrayList<>();
        for (int i = 0; i < gradesTV.getItems().size(); i++) {
            grade = (Grade) gradesTV.getItems().get(i);
            gradeList.add(new ArrayList<>());
            gradeList.get(i).add(grade.getYear());
            gradeList.get(i).add(Integer.toString(grade.getStudentID()));
            gradeList.get(i).add(grade.getModule());
            gradeList.get(i).add(grade.getFirst());
            gradeList.get(i).add(grade.getSecond());
            gradeList.get(i).add(grade.getFinalG());
            gradeList.get(i).add(grade.getTerm());
            gradeList.get(i).add(Float.toString(grade.getWeight()));
            gradeList.get(i).add(grade.getFile());
            gradeList.get(i).add(Integer.toString(grade.getAssessmentID()));

            String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
            String username = "root";
            String password = "root";
            String query = ("UPDATE grades SET student_id='" + grade.getStudentID() + "', assessment_id='" + grade.getAssessmentID() + "', file='" + grade.getFile() +
                    "', first_marking='" + grade.getFirst() + "', second_marking='" + grade.getSecond() + "', final_grade='" + grade.getFinalG() +
                    "', year='" + grade.getYear() + "', module_code='" + grade.getModule() + "', weighting='" + grade.getWeight() + "', term='" + grade.getTerm() + "'WHERE grade_id='" + grade.getId() + "'");
            Connection myCon = DriverManager.getConnection(dbURL, username, password);
            PreparedStatement preparedStatement = myCon.prepareStatement(query);
            preparedStatement.execute();
        }
        System.out.println("Saved!");
    }

    public void removeGrade() throws SQLException {
        Object selectedItems = gradesTV.getSelectionModel().getSelectedItems().get(0);
        String dbUrl = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = ("DELETE FROM grades WHERE grade_id ='" + idCol.getCellData(selectedItems) + "'");
        Connection myConnection = DriverManager.getConnection(dbUrl, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        preparedStatement.execute();
        ObservableList<Grade> allRows, singleRow;
        allRows = gradesTV.getItems();
        singleRow = gradesTV.getSelectionModel().getSelectedItems();
        singleRow.forEach(allRows::remove);
        System.out.println("Removed selected items!");
    }

    public void archiveGrade() throws SQLException {
        Object selectedItems = gradesTV.getSelectionModel().getSelectedItems().get(0);
        String dbUrl = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = ("UPDATE grades SET archived= 1 WHERE grade_id ='" + idCol.getCellData(selectedItems) + "'");
        Connection myConnection = DriverManager.getConnection(dbUrl, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        preparedStatement.execute();
        populate();
    }

    public void printTable() {
        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.print(Paper.A4, gradesTV);
    }

    public void openNewGradeWindow() throws IOException, SQLException {
        new NewGradeWindow(this);
    }
}
