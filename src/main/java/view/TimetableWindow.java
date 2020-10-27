package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jfxtras.icalendarfx.VCalendar;
import jfxtras.scene.control.agenda.icalendar.ICalendarAgenda;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.Period;

public class TimetableWindow {

    public TimetableWindow() {
        VCalendar newCal = new VCalendar();
        ICalendarAgenda agenda = new ICalendarAgenda(newCal);
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1366, 768);
        Stage stage = new Stage();
        setupComponents(root);
        root.setCenter(agenda);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    private void setupComponents(BorderPane root) {
        Button saveButton = new Button("Save Timetable");

        HBox buttonBox = new HBox(saveButton);
        root.setTop(buttonBox);

        saveButton.setOnAction((e)-> {
            FileChooser fileSaver = new FileChooser();
            fileSaver.setTitle("Save timetable");
            File destination = fileSaver.showSaveDialog(root.getScene().getWindow());
            try {
                if (destination != null) {
                    Rectangle screenSize = new Rectangle(70, 90, 1830, 1100);
                    BufferedImage timetableImg = new Robot().createScreenCapture(screenSize);
                    ImageIO.write(timetableImg, "png", new File(destination.toString()));
                }
            } catch (AWTException | IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
