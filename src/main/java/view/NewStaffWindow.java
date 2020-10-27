package view;

import controller.NewStaffTabController;
import controller.StaffTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class NewStaffWindow {

    public NewStaffWindow(StaffTabController staffTabController, int id) throws IOException, SQLException {
        FXMLLoader createStaffLoader = new FXMLLoader();
        createStaffLoader.setLocation(getClass().getResource("/FXMLview/NewStaffTab.fxml"));
        Scene scene = new Scene(createStaffLoader.load(), 1440,580);
        Stage stage = new Stage();
        stage.setTitle("Add a Student");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.show();
        Object temp = createStaffLoader.getController();
        NewStaffTabController controller = (NewStaffTabController) temp;
        controller.populateCB();
        controller.checkForSave(staffTabController);
        if(id != 0)
            controller.fetchStaffForEdit(id);
    }
}
