package view;

import controller.CourseTabController;
import controller.PersonalTutorTabController;
import controller.TutorAppointmentTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class TutorAppointmentTab extends Tab {

    public TutorAppointmentTab(TabPane tabPane, FXMLLoader loader, String sessionId) throws IOException, SQLException {
        TutorAppointmentTab personalTutorTab = new TutorAppointmentTab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/PersonalTutorshipTutorTab.fxml"));
        AnchorPane ptTabContent = loader.load();
        tabPane.getTabs().add(personalTutorTab);
        personalTutorTab.setText("Personal Tutorship");
        personalTutorTab.setContent(ptTabContent);
        Object temp = loader.getController();
        TutorAppointmentTabController controller = (TutorAppointmentTabController) temp;
        controller.setGhostSessionL(sessionId);
        controller.populateCB();
        controller.populateUpcoming();
        tabPane.getSelectionModel().select(personalTutorTab);
    }

    public TutorAppointmentTab() {

    }
}
