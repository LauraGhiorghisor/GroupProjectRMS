package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsWindow {

    public SettingsWindow() throws IOException {
        FXMLLoader createCourseLoader = new FXMLLoader();
        createCourseLoader.setLocation(getClass().getResource("/FXMLview/Settings.fxml"));
        Scene scene = new Scene(createCourseLoader.load(), 800,550);
        Stage stage = new Stage();
        stage.setTitle("Settings");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.show();
    }
}
