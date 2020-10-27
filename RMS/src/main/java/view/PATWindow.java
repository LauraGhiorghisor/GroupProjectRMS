package view;

import controller.AppointmentBoxController;
import controller.BookingController;
import controller.PATFormController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class PATWindow {

    public PATWindow(AppointmentBoxController appointmentBoxController, int appointmentID) throws IOException, SQLException {
        FXMLLoader createCourseLoader = new FXMLLoader();
        createCourseLoader.setLocation(getClass().getResource("/FXMLview/PATForm.fxml"));
        Scene scene = new Scene(createCourseLoader.load(), 1448, 580);
        Stage stage = new Stage();
        stage.setTitle("PAT Form");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.show();
        Object temp = createCourseLoader.getController();
        PATFormController controller = (PATFormController) temp;
        controller.setSaveBtnListener(appointmentBoxController, appointmentID);
        if (appointmentBoxController != null)
        controller.populateFields(appointmentBoxController.getStudentID(), appointmentBoxController.getStudentName(), appointmentBoxController.getTutorID(), appointmentBoxController.getTutorName());
        else
            controller.loadFields(appointmentID);
    }
}
