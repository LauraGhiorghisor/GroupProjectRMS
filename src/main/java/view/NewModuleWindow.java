package view;

import controller.ModuleTabController;
import controller.NewModuleWindowController;
import controller.NewStaffTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class NewModuleWindow {

    public NewModuleWindow(ModuleTabController moduleTabController) throws IOException, SQLException {
        FXMLLoader createModuleLoader = new FXMLLoader();
        createModuleLoader.setLocation(getClass().getResource("/FXMLview/NewModuleTab.fxml"));
        Scene scene = new Scene(createModuleLoader.load(), 1440,580);
        Stage stage = new Stage();
        stage.setTitle("Add a Module");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMaximized(false);
        stage.show();
        Object temp = createModuleLoader.getController();
        NewModuleWindowController controller = (NewModuleWindowController) temp;
        controller.populateCB();
        controller.checkForSave(moduleTabController);
    }
}
