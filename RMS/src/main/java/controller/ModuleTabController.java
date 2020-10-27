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
import model.Module;
import model.TablePrinter;
import view.NewModuleWindow;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleTabController {
    @FXML
    private TableView moduleTV;
    @FXML
    private TableColumn idCol, codeCol, levelCol, creditCol, titleCol, leaderCol, startCol,
            endCol, descCol, aimCol, courseCol;
    @FXML
    private Button createBtn, archiveBtn;

    public List<Module> fetchTable(boolean isArchive) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        List<Module> modules = new ArrayList<>();
        if(isArchive) {
            ResultSet result = fetchStaff.executeQuery("SELECT * FROM modules WHERE archived = 1");
            while (result.next()) {
                int id = Integer.parseInt(result.getString("module_id"));
                String code = result.getString("module_code");
                String start = result.getString("start_year");
                String end = result.getString("end_year");
                String level = result.getString("level");
                String credits = result.getString("credits");
                String title = result.getString("title");
                int leader = Integer.parseInt(result.getString("leader"));
                String desc = result.getString("description");
                String aims = result.getString("aims_and_objectives");
                String course = result.getString("course");
                modules.add(new Module(id, code, course, start, end, level, credits, title, desc, aims, leader));
            }
        } else {
            ResultSet result = fetchStaff.executeQuery("SELECT * FROM modules WHERE archived = 0");
            while (result.next()) {
                int id = Integer.parseInt(result.getString("module_id"));
                String code = result.getString("module_code");
                String start = result.getString("start_year");
                String end = result.getString("end_year");
                String level = result.getString("level");
                String credits = result.getString("credits");
                String title = result.getString("title");
                int leader = Integer.parseInt(result.getString("leader"));
                String desc = result.getString("description");
                String aims = result.getString("aims_and_objectives");
                String course = result.getString("course");
                modules.add(new Module(id, code, course, start, end, level, credits, title, desc, aims, leader));
            }
        }
        return modules;
    }

    public List<Module> fetchTableByCourse(boolean isArchive, String course) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        List<Module> modules = new ArrayList<>();
        if (!isArchive) {
            ResultSet result = fetchStaff.executeQuery("SELECT * FROM modules WHERE archived = 0 AND course='" + course + "'");
            while (result.next()) {
                int id = Integer.parseInt(result.getString("module_id"));
                String code = result.getString("module_code");
                String start = result.getString("start_year");
                String end = result.getString("end_year");
                String level = result.getString("level");
                String credits = result.getString("credits");
                String title = result.getString("title");
                int leader = Integer.parseInt(result.getString("leader"));
                String desc = result.getString("description");
                String aims = result.getString("aims_and_objectives");
                String courseResult = result.getString("course");
                modules.add(new Module(id, code, courseResult, start, end, level, credits, title, desc, aims, leader));
            }
        }
        return modules;
    }

    public void populateTable(List<Module> newModule) {
        idCol.setCellValueFactory(new PropertyValueFactory<Module, Integer>("id"));
        codeCol.setCellValueFactory(new PropertyValueFactory<Module, String>("code"));
        levelCol.setCellValueFactory(new PropertyValueFactory<Module, String>("level"));
        creditCol.setCellValueFactory(new PropertyValueFactory<Module, String>("credits"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Module, String>("title"));
        leaderCol.setCellValueFactory(new PropertyValueFactory<Module, Integer>("leader"));
        startCol.setCellValueFactory(new PropertyValueFactory<Module, String>("startYear"));
        endCol.setCellValueFactory(new PropertyValueFactory<Module, String>("endYear"));
        descCol.setCellValueFactory(new PropertyValueFactory<Module, String>("description"));
        aimCol.setCellValueFactory(new PropertyValueFactory<Module, String>("aims"));
        courseCol.setCellValueFactory(new PropertyValueFactory<Module, String>("course"));

        idCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        codeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        levelCol.setCellFactory(TextFieldTableCell.forTableColumn());
        creditCol.setCellFactory(TextFieldTableCell.forTableColumn());
        titleCol.setCellFactory(TextFieldTableCell.forTableColumn());
        leaderCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        startCol.setCellFactory(TextFieldTableCell.forTableColumn());
        endCol.setCellFactory(TextFieldTableCell.forTableColumn());
        descCol.setCellFactory(TextFieldTableCell.forTableColumn());
        aimCol.setCellFactory(TextFieldTableCell.forTableColumn());
        courseCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ObservableList<Module> rows = FXCollections.observableArrayList();
        for (Module module : newModule) {
            rows.add(module);
        }
        moduleTV.setItems(rows);
        moduleTV.setEditable(true);
        moduleTV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        editColumns();
    }

    public void populate() throws SQLException {
        List<Module> newModule = fetchTable(false);
        populateTable(newModule);
    }
    public void populateByCourse(String course) throws SQLException {
        List<Module> newModule = fetchTableByCourse(false, course);
        populateTable(newModule);
    }
    public void populateArchive() throws SQLException {
        List<Module> newModule = fetchTable(true);
        populateTable(newModule);
        createBtn.setVisible(false);
        archiveBtn.setVisible(false);
    }

    public void editColumns() {
        idCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Module, Integer>>) moduleIntegerCellEditEvent -> ((Module) moduleIntegerCellEditEvent.getTableView().getItems().get(moduleIntegerCellEditEvent.getTablePosition().getRow())).setId(moduleIntegerCellEditEvent.getNewValue()));
        codeCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Module, String>>) moduleStringCellEditEvent -> ((Module) moduleStringCellEditEvent.getTableView().getItems().get(moduleStringCellEditEvent.getTablePosition().getRow())).setCode(moduleStringCellEditEvent.getNewValue()));
        levelCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Module, String>>) moduleStringCellEditEvent -> ((Module) moduleStringCellEditEvent.getTableView().getItems().get(moduleStringCellEditEvent.getTablePosition().getRow())).setLevel(moduleStringCellEditEvent.getNewValue()));
        creditCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Module, String>>) moduleStringCellEditEvent -> ((Module) moduleStringCellEditEvent.getTableView().getItems().get(moduleStringCellEditEvent.getTablePosition().getRow())).setCredits(moduleStringCellEditEvent.getNewValue()));
        titleCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Module, String>>) moduleStringCellEditEvent -> ((Module) moduleStringCellEditEvent.getTableView().getItems().get(moduleStringCellEditEvent.getTablePosition().getRow())).setTitle(moduleStringCellEditEvent.getNewValue()));
        leaderCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Module, Integer>>) moduleIntegerCellEditEvent -> ((Module) moduleIntegerCellEditEvent.getTableView().getItems().get(moduleIntegerCellEditEvent.getTablePosition().getRow())).setLeader(moduleIntegerCellEditEvent.getNewValue()));
        startCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Module, String>>) moduleStringCellEditEvent -> ((Module) moduleStringCellEditEvent.getTableView().getItems().get(moduleStringCellEditEvent.getTablePosition().getRow())).setStartYear(moduleStringCellEditEvent.getNewValue()));
        endCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Module, String>>) moduleStringCellEditEvent -> ((Module) moduleStringCellEditEvent.getTableView().getItems().get(moduleStringCellEditEvent.getTablePosition().getRow())).setEndYear(moduleStringCellEditEvent.getNewValue()));
        descCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Module, String>>) moduleStringCellEditEvent -> ((Module) moduleStringCellEditEvent.getTableView().getItems().get(moduleStringCellEditEvent.getTablePosition().getRow())).setDescription(moduleStringCellEditEvent.getNewValue()));
        aimCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Module, String>>) moduleStringCellEditEvent -> ((Module) moduleStringCellEditEvent.getTableView().getItems().get(moduleStringCellEditEvent.getTablePosition().getRow())).setAims(moduleStringCellEditEvent.getNewValue()));
        courseCol.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Module, String>>) moduleStringCellEditEvent -> ((Module) moduleStringCellEditEvent.getTableView().getItems().get(moduleStringCellEditEvent.getTablePosition().getRow())).setCourse(moduleStringCellEditEvent.getNewValue()));
    }

    public void updateTable() throws SQLException {
        Module module;
        List <List<String>> moduleList = new ArrayList<>();
        for (int i = 0; i < moduleTV.getItems().size(); i++) {
            module = (Module) moduleTV.getItems().get(i);
            moduleList.add(new ArrayList<>());
            moduleList.get(i).add(module.getCode());
            moduleList.get(i).add(module.getLevel());
            moduleList.get(i).add(module.getCredits());
            moduleList.get(i).add(module.getTitle());
            moduleList.get(i).add(Integer.toString(module.getLeader()));
            moduleList.get(i).add(module.getStartYear());
            moduleList.get(i).add(module.getEndYear());
            moduleList.get(i).add(module.getDescription());
            moduleList.get(i).add(module.getAims());
            moduleList.get(i).add(module.getCourse());

            String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
            String username = "root";
            String password = "root";
            String query = ("UPDATE modules SET module_code='" + module.getCode() + "', start_year='" + module.getStartYear() + "', end_year='" + module.getEndYear() + "', level='" + module.getLevel() + "', credits='" + module.getCredits() +
                    "', title='" + module.getTitle() + "', leader='" + module.getLeader() + "', description='" + module.getDescription() + "', aims_and_objectives='" + module.getAims() + "', course='" + module.getCourse() + "' WHERE module_id='" + module.getId() + "'");
            Connection myCon = DriverManager.getConnection(dbURL, username, password);
            PreparedStatement preparedStatement = myCon.prepareStatement(query);
            preparedStatement.execute();
        }
        System.out.println("Saved!");
    }

    public void removeModule() throws SQLException {
        Object selectedItems = moduleTV.getSelectionModel().getSelectedItems().get(0);
        String dbUrl = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = ("DELETE FROM modules WHERE module_id ='" + idCol.getCellData(selectedItems) + "'");
        Connection myConnection = DriverManager.getConnection(dbUrl, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        preparedStatement.execute();
        ObservableList<Module> allRows, singleRow;
        allRows = moduleTV.getItems();
        singleRow = moduleTV.getSelectionModel().getSelectedItems();
        singleRow.forEach(allRows::remove);
        System.out.println("Removed selected items!");
    }

    public void printTable() {
        TablePrinter tablePrinter = new TablePrinter();
        tablePrinter.print(Paper.A4, moduleTV);
    }

    public void archiveModule() throws SQLException {
        Object selectedItems = moduleTV.getSelectionModel().getSelectedItems().get(0);
        String dbUrl = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = ("UPDATE modules SET archived= 1 WHERE module_id ='" + idCol.getCellData(selectedItems) + "'");
        Connection myConnection = DriverManager.getConnection(dbUrl, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        preparedStatement.execute();
        populate();
    }

    public void openNewModuleWindow() throws IOException, SQLException {
        new NewModuleWindow(this);
    }
}
