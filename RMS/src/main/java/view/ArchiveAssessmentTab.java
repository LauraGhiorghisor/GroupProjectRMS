package view;

import controller.AssessmentTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class ArchiveAssessmentTab extends Tab {

    public ArchiveAssessmentTab(TabPane tabPane, FXMLLoader loader) throws IOException, SQLException {
        ArchiveAssessmentTab assessmentTab = new ArchiveAssessmentTab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/AssessmentsTabRecordsStaff.fxml"));
        AnchorPane assessmentTabContent = loader.load();
        tabPane.getTabs().add(assessmentTab);
        assessmentTab.setText("Assessments");
        assessmentTab.setContent(assessmentTabContent);
        Object temp = loader.getController();
        AssessmentTabController controller = (AssessmentTabController) temp;
        controller.populateArchive();
        tabPane.getSelectionModel().select(assessmentTab);
    }

    public ArchiveAssessmentTab() {

    }
}

