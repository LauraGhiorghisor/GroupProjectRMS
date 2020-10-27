package view;

import controller.AssessmentTabController;
import controller.GradesTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.*;

public class GradesTab extends Tab {

    public GradesTab(TabPane tabPane, FXMLLoader loader, Label label) throws IOException, SQLException {
        GradesTab gradesTab = new GradesTab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/GradesTabRecordsStaff.fxml"));
        AnchorPane gradesTabContent = loader.load();
        tabPane.getTabs().add(gradesTab);
        gradesTab.setText("Grades");
        gradesTab.setContent(gradesTabContent);
        Object temp = loader.getController();
        GradesTabController controller = (GradesTabController) temp;
        ResultSet result = connect().executeQuery("SELECT * FROM users WHERE user_id ='" + Integer.parseInt(label.getText()) + "'");
        while (result.next()) {
            if(result.getInt("user_level") == 4) {
                controller.populateTutor();
            } else {
                controller.populate();
            }
        }
        tabPane.getSelectionModel().select(gradesTab);
    }

    public GradesTab() {

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
