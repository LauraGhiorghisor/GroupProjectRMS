package view;

import controller.CourseTabController;
import controller.PersonalTutorTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class PersonalTutorTab extends Tab {

    public PersonalTutorTab(TabPane tabPane, FXMLLoader loader) throws IOException, SQLException {
        PersonalTutorTab personalTutorTab = new PersonalTutorTab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/PersonalTutorshipRecordsStaffTab.fxml"));
        AnchorPane ptTabContent = loader.load();
        tabPane.getTabs().add(personalTutorTab);
        personalTutorTab.setText("Personal Tutorship");
        personalTutorTab.setContent(ptTabContent);
        Object temp = loader.getController();
        PersonalTutorTabController controller = (PersonalTutorTabController) temp;
        controller.populate();
        tabPane.getSelectionModel().select(personalTutorTab);
    }

    public PersonalTutorTab() {

    }
}
