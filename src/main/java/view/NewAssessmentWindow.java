package view;

import controller.AssessmentTabController;
import controller.NewAssessmentWindowController;
import controller.NewCourseWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class NewAssessmentWindow {

    public NewAssessmentWindow(AssessmentTabController assessmentTabController) throws IOException, SQLException {
        FXMLLoader createCourseLoader = new FXMLLoader();
        createCourseLoader.setLocation(getClass().getResource("/FXMLview/NewAssessmentTab.fxml"));
        Scene scene = new Scene(createCourseLoader.load(), 1440,580);
        Stage stage = new Stage();
        stage.setTitle("Add an Assessment");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMaximized(false);
        stage.show();
        Object temp = createCourseLoader.getController();
        NewAssessmentWindowController controller = (NewAssessmentWindowController) temp;
        controller.populateCB();
        controller.checkForSave(assessmentTabController);
    }
}
