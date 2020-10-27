package view;

import controller.AssessmentTabController;
import controller.NewAssessmentWindowController;
import controller.ProfileController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ProfileWindow {

    public ProfileWindow(Label label) throws IOException, SQLException {
        FXMLLoader profileLoader = new FXMLLoader();
        profileLoader.setLocation(getClass().getResource("/FXMLview/Profile.fxml"));
        Scene scene = new Scene(profileLoader.load(), 800,600);
        Stage stage = new Stage();
        stage.setTitle("Profile");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.show();
        Object temp = profileLoader.getController();
        ProfileController controller = (ProfileController) temp;
        controller.loadProfile(Integer.parseInt(label.getText()));
    }
}
