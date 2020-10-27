package view;

import controller.AttendanceTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.sql.SQLException;

public class ArchiveAttendanceTab extends Tab {

    public ArchiveAttendanceTab(TabPane tabPane, FXMLLoader loader) throws IOException, SQLException {
        ArchiveAttendanceTab attendanceTab = new ArchiveAttendanceTab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/AttendanceTabRecordsStaff.fxml"));
        AnchorPane attendanceTabContent = loader.load();
        tabPane.getTabs().add(attendanceTab);
        attendanceTab.setText("Attendance");
        attendanceTab.setContent(attendanceTabContent);
        Object temp = loader.getController();
        AttendanceTabController controller = (AttendanceTabController) temp;
        tabPane.getSelectionModel().select(attendanceTab);
    }

    public ArchiveAttendanceTab() {

    }
}
