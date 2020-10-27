package view;

import controller.StudentTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class ArchiveStudentTab extends Tab {

    public ArchiveStudentTab(TabPane tabPane, FXMLLoader loader, String id) throws IOException, SQLException {
        ArchiveStudentTab studentTab = new ArchiveStudentTab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/StudentTab.fxml"));
        AnchorPane studentTabContent = loader.load();
        tabPane.getTabs().add(studentTab);
        studentTab.setText("Students");
        studentTab.setContent(studentTabContent);
        Object temp = loader.getController();
        StudentTabController controller = (StudentTabController) temp;
        controller.populateArchive(id);
        tabPane.getSelectionModel().select(studentTab);
    }

    public ArchiveStudentTab() {

    }
}
