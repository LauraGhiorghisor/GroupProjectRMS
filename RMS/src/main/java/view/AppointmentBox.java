package view;

import controller.AppointmentBoxController;
import controller.GradesTabController;
import controller.TutorAppointmentTabController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;


public class AppointmentBox extends HBox {
    @FXML
    private Text studentT, idT, dateT;


    public AppointmentBox(VBox vbox, String[] student, String id, String date, String tutorID, String[] tutorName, int appointmentID, TutorAppointmentTabController tutorAppointmentTabController) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLview/AppointmentBox.fxml"));
        HBox appointmentBoxContent = loader.load();
        vbox.getChildren().add(appointmentBoxContent);
        Object temp = loader.getController();
        AppointmentBoxController controller = (AppointmentBoxController) temp;
        controller.setValues(student, id, date, tutorID, tutorName, appointmentID);
        controller.addCompleteBtnListener(tutorAppointmentTabController);
    }

    public AppointmentBox() {

    }
}
