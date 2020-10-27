package view;

import controller.CourseTabController;
import controller.ModuleTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class CourseTab extends Tab {

    public CourseTab(TabPane tabPane, FXMLLoader loader) throws IOException, SQLException {
        CourseTab courseTab = new CourseTab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/CoursesTab.fxml"));
        AnchorPane courseTabContent = loader.load();
        tabPane.getTabs().add(courseTab);
        courseTab.setText("Courses");
        courseTab.setContent(courseTabContent);
        Object temp = loader.getController();
        CourseTabController controller = (CourseTabController) temp;
        controller.populate();
        tabPane.getSelectionModel().select(courseTab);
    }

    public CourseTab() {

    }
}

