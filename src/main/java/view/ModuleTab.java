package view;

import controller.ModuleTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.sql.*;

public class ModuleTab extends Tab {

    public ModuleTab(TabPane tabPane, FXMLLoader loader, Label label) throws IOException, SQLException {
        ModuleTab moduleTab = new ModuleTab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/ModulesTab.fxml"));
        AnchorPane moduleTabContent = loader.load();
        tabPane.getTabs().add(moduleTab);
        moduleTab.setText("Modules");
        moduleTab.setContent(moduleTabContent);
        Object temp = loader.getController();
        ModuleTabController controller = (ModuleTabController) temp;
        ResultSet result = connect().executeQuery("SELECT * FROM users WHERE user_id ='" + Integer.parseInt(label.getText()) + "'");
        while (result.next()) {
            if(result.getInt("user_level") == 4) {
                controller.populateByCourse(result.getString("course"));
            } else {
                controller.populate();
            }
        }
        tabPane.getSelectionModel().select(moduleTab);
    }

    public ModuleTab() {

    }

    private Statement connect() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        return fetchStaff;
    }
}
