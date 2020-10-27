package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HomeTabController {

    public void showNewStudentWindow() throws IOException {
        System.out.println("lol");
        FXMLLoader createStudentLoader = new FXMLLoader();
        createStudentLoader.setLocation(getClass().getResource("/FXMLview/NewStudentTab.fxml"));
        Scene scene = new Scene(createStudentLoader.load(), 1440,580);
        Stage stage = new Stage();
        stage.setTitle("Add a Student");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.show();
        Object temp = createStudentLoader.getController();
        NewStudentTabController controller = (NewStudentTabController) temp;
        controller.populateComboBoxes();
        controller.checkForSave();
    }

    public void showNewStaffWindow() throws SQLException, IOException {
        FXMLLoader createStaffLoader = new FXMLLoader();
        createStaffLoader.setLocation(getClass().getResource("/FXMLview/NewStaffTab.fxml"));
        Scene scene = new Scene(createStaffLoader.load(), 1440,580);
        Stage stage = new Stage();
        stage.setTitle("Add a staff member");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.show();
        Object temp = createStaffLoader.getController();
        NewStaffTabController controller = (NewStaffTabController) temp;
        controller.populateCB();
        controller.checkForSave();
    }

    public void showNewModuleWindow() throws SQLException, IOException {
        FXMLLoader createModuleLoader = new FXMLLoader();
        createModuleLoader.setLocation(getClass().getResource("/FXMLview/NewModuleTab.fxml"));
        Scene scene = new Scene(createModuleLoader.load(), 1440,580);
        Stage stage = new Stage();
        stage.setTitle("Add a Module");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.show();
        Object temp = createModuleLoader.getController();
        NewModuleWindowController controller = (NewModuleWindowController) temp;
        controller.populateCB();
        controller.checkForSave();
    }

    public void showNewCourseWindow() throws SQLException, IOException {
        FXMLLoader createCourseLoader = new FXMLLoader();
        createCourseLoader.setLocation(getClass().getResource("/FXMLview/NewCourseTab.fxml"));
        Scene scene = new Scene(createCourseLoader.load(), 1440,580);
        Stage stage = new Stage();
        stage.setTitle("Add a Course");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMaximized(true);
        stage.show();
        Object temp = createCourseLoader.getController();
        NewCourseWindowController controller = (NewCourseWindowController) temp;
        controller.populateCB();
        controller.checkForSave();
    }

    public void showNewGradeWindow() throws SQLException, IOException {
        FXMLLoader createGradeLoader = new FXMLLoader();
        createGradeLoader.setLocation(getClass().getResource("/FXMLview/NewGradeTab.fxml"));
        Scene scene = new Scene(createGradeLoader.load(), 1440,580);
        Stage stage = new Stage();
        stage.setTitle("Add a Grade");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMaximized(true);
        stage.show();
        Object temp = createGradeLoader.getController();
        NewGradeWindowController controller = (NewGradeWindowController) temp;
        controller.populateCB();
        controller.newRecordCheck();
    }

    public void showNewAssessmentWindow() throws SQLException, IOException {
        FXMLLoader createCourseLoader = new FXMLLoader();
        createCourseLoader.setLocation(getClass().getResource("/FXMLview/NewAssessmentTab.fxml"));
        Scene scene = new Scene(createCourseLoader.load(), 1440,580);
        Stage stage = new Stage();
        stage.setTitle("Add an Assessment");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.show();
        Object temp = createCourseLoader.getController();
        NewAssessmentWindowController controller = (NewAssessmentWindowController) temp;
        controller.populateCB();
        controller.checkForSave();
    }

}
