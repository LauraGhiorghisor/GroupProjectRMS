package view;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jfxtras.icalendarfx.VCalendar;
import jfxtras.scene.control.agenda.icalendar.ICalendarAgenda;
import model.Grade;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.Period;



public class CalendarWindow {

    public CalendarWindow(Label label) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        ResultSet result = fetchStaff.executeQuery("SELECT * FROM users WHERE user_id ='" + Integer.parseInt(label.getText()) + "'");
        while (result.next()) {
                VCalendar vCalendar = VCalendar.parse(result.getString("calendar_info"));
                if(vCalendar != null) {
                ICalendarAgenda agenda = new ICalendarAgenda(vCalendar);
                BorderPane root = new BorderPane();
                Scene scene = new Scene(root, 1366, 768);
                Stage stage = new Stage();
                Label ghostL = new Label(label.getText());
                setupComponents(root, agenda, ghostL);
                root.setCenter(agenda);
                stage.setMaximized(true);
                stage.setScene(scene);
                stage.show();
                closeCalendar(stage, vCalendar, ghostL);
            } else {
                VCalendar newCal = new VCalendar();
                ICalendarAgenda agenda = new ICalendarAgenda(newCal);
                BorderPane root = new BorderPane();
                Scene scene = new Scene(root, 1366, 768);
                Stage stage = new Stage();
                Label ghostL = new Label(label.getText());
                setupComponents(root, agenda, ghostL);
                root.setCenter(agenda);
                stage.setMaximized(true);
                stage.setScene(scene);
                stage.show();
                closeCalendar(stage, newCal, ghostL);
            }

        }
    }

    private void setupComponents(BorderPane root, ICalendarAgenda agenda, Label label) {
        Button incrementWeek = new Button(">");
        Button decrementWeek = new Button("<");
        label.setVisible(true);
        HBox buttonBox = new HBox(decrementWeek, incrementWeek, label);
        root.setTop(buttonBox);

        incrementWeek.setOnAction((e)-> {
            LocalDateTime newLocalDateTime = agenda.getDisplayedLocalDateTime().plus(Period.ofWeeks(1));
            agenda.setDisplayedLocalDateTime(newLocalDateTime);
        });
        decrementWeek.setOnAction((e)-> {
            LocalDateTime newLocalDateTime = agenda.getDisplayedLocalDateTime().minus(Period.ofWeeks(1));
            agenda.setDisplayedLocalDateTime(newLocalDateTime);
        });
    }

    private void closeCalendar(Stage stage, VCalendar vCalendar, Label label) throws SQLException {
        stage.setOnCloseRequest(e -> {
            try {
                saveCalendar(label, vCalendar);
                System.out.println(vCalendar.toString());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void saveCalendar(Label label, VCalendar vCalendar) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        String query = ("UPDATE users SET calendar_info='" + vCalendar.toString() + "' WHERE user_id='" + Integer.parseInt(label.getText()) + "'");
        Connection myConnection = DriverManager.getConnection(dbURL, username, password);
        PreparedStatement preparedStatement = myConnection.prepareStatement(query);
        preparedStatement.execute();
    }
}
