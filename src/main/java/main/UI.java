package main;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class UI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLview/Login.fxml"));
        Parent loginPage = loader.load();
        Scene scene = new Scene(loginPage);
//        scene.getStylesheets().add("main.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Woodlands RMS");
        Object temp = loader.getController();
        LoginController controller = (LoginController) temp;
        controller.initial();
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
