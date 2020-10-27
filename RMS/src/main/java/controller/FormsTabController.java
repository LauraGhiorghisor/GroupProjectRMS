package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FormsTabController {
    @FXML
    private AnchorPane formPane;

    public void savePat() {
        File patFile = Paths.get("PDFforms/patform.pdf").toFile();
        FileChooser fileSaver = new FileChooser();
        fileSaver.setTitle("Save PAT form");
        File destination = fileSaver.showSaveDialog(formPane.getScene().getWindow());
        if (destination != null) {
            try {
                Files.copy(patFile.toPath(), destination.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveJulyConfirmLetter() {
        File julyConfirmLetter = Paths.get("PDFforms/julyletter.pdf").toFile();
        FileChooser fileSaver = new FileChooser();
        fileSaver.setTitle("Save july letter");
        File destination = fileSaver.showSaveDialog(formPane.getScene().getWindow());
        if (destination != null) {
            try {
                Files.copy(julyConfirmLetter.toPath(), destination.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveCauseForConcern() {
        File causeForConcernLetter = Paths.get("PDFforms/causeforconcernletter.pdf").toFile();
        FileChooser fileSaver = new FileChooser();
        fileSaver.setTitle("Save cause for concern form");
        File destination = fileSaver.showSaveDialog(formPane.getScene().getWindow());
        if (destination != null) {
            try {
                Files.copy(causeForConcernLetter.toPath(), destination.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveAttendanceForm() {
        File attendanceForm = Paths.get("PDFforms/attendanceform.pdf").toFile();
        FileChooser fileSaver = new FileChooser();
        fileSaver.setTitle("Save attendance form");
        File destination = fileSaver.showSaveDialog(formPane.getScene().getWindow());
        if (destination != null) {
            try {
                Files.copy(attendanceForm.toPath(), destination.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveAssignmentSubmission() {
        File assignmentSubmissionForm = Paths.get("PDFforms/assignmentform.pdf").toFile();
        FileChooser fileSaver = new FileChooser();
        fileSaver.setTitle("Save assignment form");
        File destination = fileSaver.showSaveDialog(formPane.getScene().getWindow());
        if (destination != null) {
            try {
                Files.copy(assignmentSubmissionForm.toPath(), destination.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveRecordUpdate() {
        File recordUpdateForm = Paths.get("PDFforms/recordupdate.pdf").toFile();
        FileChooser fileSaver = new FileChooser();
        fileSaver.setTitle("Save record form");
        File destination = fileSaver.showSaveDialog(formPane.getScene().getWindow());
        if (destination != null) {
            try {
                Files.copy(recordUpdateForm.toPath(), destination.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
