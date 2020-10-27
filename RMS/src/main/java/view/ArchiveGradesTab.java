package view;

import controller.AssessmentTabController;
import controller.GradesTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class ArchiveGradesTab extends Tab {

    public ArchiveGradesTab(TabPane tabPane, FXMLLoader loader) throws IOException, SQLException {
        ArchiveGradesTab gradesTab = new ArchiveGradesTab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/GradesTabRecordsStaff.fxml"));
        AnchorPane gradesTabContent = loader.load();
        tabPane.getTabs().add(gradesTab);
        gradesTab.setText("Grades");
        gradesTab.setContent(gradesTabContent);
        Object temp = loader.getController();
        GradesTabController controller = (GradesTabController) temp;
        controller.populateArchive();
        tabPane.getSelectionModel().select(gradesTab);
    }

    public ArchiveGradesTab() {

    }

}
