package view;

import controller.CourseTabController;
import controller.ModuleTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class ArchiveCourseTab extends Tab {

    public ArchiveCourseTab(TabPane tabPane, FXMLLoader loader) throws IOException, SQLException {
        ArchiveCourseTab courseTab = new ArchiveCourseTab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/CoursesTab.fxml"));
        AnchorPane courseTabContent = loader.load();
        tabPane.getTabs().add(courseTab);
        courseTab.setText("Courses");
        courseTab.setContent(courseTabContent);
        Object temp = loader.getController();
        CourseTabController controller = (CourseTabController) temp;
        controller.populateArchive();
        tabPane.getSelectionModel().select(courseTab);
    }

    public ArchiveCourseTab() {

    }
}

