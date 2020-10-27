package view;

import controller.CourseTabController;
import controller.PersonalTutorTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class ArchivePersonalTutorTab extends Tab {

    public ArchivePersonalTutorTab(TabPane tabPane, FXMLLoader loader) throws IOException, SQLException {
        ArchivePersonalTutorTab personalTutorTab = new ArchivePersonalTutorTab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/PersonalTutorshipRecordsStaffTab.fxml"));
        AnchorPane ptTabContent = loader.load();
        tabPane.getTabs().add(personalTutorTab);
        personalTutorTab.setText("Personal Tutorship");
        personalTutorTab.setContent(ptTabContent);
        Object temp = loader.getController();
        PersonalTutorTabController controller = (PersonalTutorTabController) temp;
        controller.populateArchive();
        tabPane.getSelectionModel().select(personalTutorTab);
    }

    public ArchivePersonalTutorTab() {

    }
}
