package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class CalendarController {
    @FXML
    Pane calendarPane;

    public void setSize() {
        calendarPane.resize(1200,1200);
    }
}
