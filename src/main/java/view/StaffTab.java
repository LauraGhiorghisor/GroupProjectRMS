package view;

import controller.StaffTabController;
import controller.StudentTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class StaffTab extends Tab {

    public StaffTab(TabPane tabPane, FXMLLoader loader, String id) throws IOException, SQLException {
        StaffTab staffTab = new StaffTab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/StaffTab.fxml"));
        AnchorPane staffTabContent = loader.load();
        tabPane.getTabs().add(staffTab);
        staffTab.setText("Staff");
        staffTab.setContent(staffTabContent);
        Object temp = loader.getController();
        StaffTabController controller = (StaffTabController) temp;
        controller.populate(id);
        tabPane.getSelectionModel().select(staffTab);
    }

    public StaffTab() {

    }
}
