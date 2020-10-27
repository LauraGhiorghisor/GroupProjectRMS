package view;

import controller.CourseTabController;
import controller.NewCourseWindowController;
import controller.NewModuleWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class NewCourseWindow {

    public NewCourseWindow(CourseTabController courseTabController) throws IOException, SQLException {
        FXMLLoader createCourseLoader = new FXMLLoader();
        createCourseLoader.setLocation(getClass().getResource("/FXMLview/NewCourseTab.fxml"));
        Scene scene = new Scene(createCourseLoader.load(), 1440,580);
        Stage stage = new Stage();
        stage.setTitle("Add a Course");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMaximized(false);
        stage.show();
        Object temp = createCourseLoader.getController();
        NewCourseWindowController controller = (NewCourseWindowController) temp;
        controller.populateCB();
        controller.checkForSave(courseTabController);
    }
}
