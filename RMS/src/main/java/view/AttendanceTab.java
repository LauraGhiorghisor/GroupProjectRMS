package view;

import controller.AttendanceTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.*;

public class AttendanceTab extends Tab {

    public AttendanceTab(TabPane tabPane, FXMLLoader loader, Label label) throws IOException, SQLException {
        AttendanceTab attendanceTab = new AttendanceTab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/AttendanceTabRecordsStaff.fxml"));
        AnchorPane attendanceTabContent = loader.load();
        tabPane.getTabs().add(attendanceTab);
        attendanceTab.setText("Attendance");
        attendanceTab.setContent(attendanceTabContent);
        Object temp = loader.getController();
        AttendanceTabController controller = (AttendanceTabController) temp;
        ResultSet result = connect().executeQuery("SELECT * FROM users WHERE user_id ='" + Integer.parseInt(label.getText()) + "'");
        while (result.next()) {
            controller.populateCourseCB(result.getString("course"), result.getInt("user_level"));
            controller.populateModuleCB(result.getString("course"), result.getInt("user_level"));
            controller.startSearcherForRecordsStaff();
        }
        tabPane.getSelectionModel().select(attendanceTab);
    }

    public AttendanceTab() {

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
