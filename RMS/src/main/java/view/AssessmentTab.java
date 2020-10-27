package view;

import controller.AssessmentTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.*;

public class AssessmentTab extends Tab {

    public AssessmentTab(TabPane tabPane, FXMLLoader loader, Label label) throws IOException, SQLException {
        AssessmentTab assessmentTab = new AssessmentTab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/AssessmentsTabRecordsStaff.fxml"));
        AnchorPane assessmentTabContent = loader.load();
        tabPane.getTabs().add(assessmentTab);
        assessmentTab.setText("Assessments");
        assessmentTab.setContent(assessmentTabContent);
        Object temp = loader.getController();
        AssessmentTabController controller = (AssessmentTabController) temp;
        ResultSet result = connect().executeQuery("SELECT * FROM users WHERE user_id ='" + Integer.parseInt(label.getText()) + "'");
        while (result.next()) {
            if (result.getInt("user_level") == 4) {
                controller.populateByCourse(result.getString("course"));
            } else {
                controller.populate();
            }
            controller.populateCourseCB(result.getInt("user_level"), result.getString("course"));
            controller.populateModuleCB(result.getInt("user_level"), result.getString("course"));
            controller.populateYearCB();
            controller.initializeEventListeners();
        }
        tabPane.getSelectionModel().select(assessmentTab);
    }

    public AssessmentTab() {

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

