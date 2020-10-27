package controller;

import chat_client.ChatClient;
import chat_client.UserListPane;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import jfxtras.icalendarfx.VCalendar;
import jfxtras.scene.control.agenda.icalendar.ICalendarAgenda;
import model.Course;
import model.Session;
import model.Staff;
import view.*;

import java.awt.*;
import java.awt.ScrollPane;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.*;

public class HomePageController {
    @FXML
    private Text loginLabel;
    @FXML
    private Text lastLogLabel;
    @FXML
    private Text dateLabel;
    @FXML
    public TabPane homePageTabPane;
    @FXML
    private Button logoutBtn, notifBtn, tutorNotifBtn;
    @FXML
    private Label ghostSessionL;
    @FXML
    private AnchorPane homePaneRS, homePaneT, notifPane, notifPaneTutor;
    @FXML
    private ComboBox<String> searchCB, searchCBT;
    @FXML
    private TextField searchTF, searchTFT;
    private FXMLLoader loader;
    public ArrayList<Tab> tabArrayList = new ArrayList<>();
    private int currentTabView;
    private Alert tabLimitError = new Alert(Alert.AlertType.ERROR);

    public void setLoginUsername(String name) {
        loginLabel.setText(name);
    }

    public void setLastLogLabel(String timestamp) {
        lastLogLabel.setText(timestamp);
    }

    public void setGhostSessionL(String id) {
        ghostSessionL.setText(id);
    }

    public void setDateLabel() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatDate = new SimpleDateFormat("MMM-dd");
        String dateForLabel = formatDate.format(cal.getTime());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String string = new SimpleDateFormat("HH:mm:ss").format(new Date());
                dateLabel.setText(dateForLabel + " " + string);
            }
        }, 0, 1000);
    }

    public void createHomeTab(Session session) throws IOException {
        Tab homeTab = new Tab();
        if (session.getAccessLevel().hasAccessToSysAdminView() || session.getAccessLevel().hasAccessToRecView()) {
            loader = new FXMLLoader(getClass().getResource("/FXMLview/HomePageRecordsStaffTab.fxml"));
        } else {
            loader = new FXMLLoader(getClass().getResource("/FXMLview/HomePageTutorTab.fxml"));
        }
        AnchorPane homeTabContent = loader.load();
        homePageTabPane.getTabs().add(homeTab);
        homeTab.setText("Home");
        homeTab.setContent(homeTabContent);
    }

    public void createStudentTab() throws IOException, SQLException {
        checkTabLimit(new StudentTab(homePageTabPane, loader, null));
    }

    public void archiveCreateStudentTab() throws IOException, SQLException {
        checkTabLimit(new ArchiveStudentTab(homePageTabPane, loader, null));
    }

    public void createStaffTab() throws IOException, SQLException {
        checkTabLimit(new StaffTab(homePageTabPane, loader, null));
    }

    public void archiveCreateStaffTab() throws IOException, SQLException {
        checkTabLimit(new ArchiveStaffTab(homePageTabPane, loader));
    }

    public void createModuleTab() throws IOException, SQLException {
        checkTabLimit(new ModuleTab(homePageTabPane, loader, ghostSessionL));
    }

    public void createArchiveModuleTab() throws IOException, SQLException {
        checkTabLimit(new ArchiveModuleTab(homePageTabPane, loader));
    }

    public void createCourseTab() throws IOException, SQLException {
        checkTabLimit(new CourseTab(homePageTabPane, loader));
    }

    public void createFormsTab() throws IOException {
        checkTabLimit(new FormsTab(homePageTabPane, loader));
    }

    public void createArchiveCourseTab() throws IOException, SQLException {
        checkTabLimit(new ArchiveCourseTab(homePageTabPane, loader));
    }

    public void createAssessmentTabRS() throws IOException, SQLException {
        checkTabLimit(new AssessmentTab(homePageTabPane, loader, ghostSessionL));
    }

    public void createArchiveAssessmentTab() throws IOException, SQLException {
        checkTabLimit(new ArchiveAssessmentTab(homePageTabPane, loader));
    }

    public void createAssessmentTabT() throws IOException {
        Tab assessmentTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/AssessmentsTabTutor.fxml"));
        AnchorPane assessmentTabContent = loader.load();
        homePageTabPane.getTabs().add(assessmentTab);
        assessmentTab.setText("Assessments");
        assessmentTab.setContent(assessmentTabContent);
    }

    public void createGradesTabRS() throws IOException, SQLException {
        checkTabLimit(new GradesTab(homePageTabPane, loader, ghostSessionL));
    }

    public void createGradesTabT() throws IOException {
        Tab gradesTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/GradesTabTutor.fxml"));
        AnchorPane gradesTabContent = loader.load();
        homePageTabPane.getTabs().add(gradesTab);
        gradesTab.setText("Grades");
        gradesTab.setContent(gradesTabContent);
    }

    public void createArchiveGradesTab() throws IOException, SQLException {
        checkTabLimit(new ArchiveGradesTab(homePageTabPane, loader));
    }

    public void createAttendanceTabRS() throws IOException, SQLException {
        checkTabLimit(new AttendanceTab(homePageTabPane, loader, ghostSessionL));
    }

    public void createArchiveAttendanceTab() throws IOException, SQLException {
        checkTabLimit(new ArchiveAttendanceTab(homePageTabPane, loader));
    }

    public void createAttendanceTabT() throws IOException {
        Tab attendanceTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/AttendanceTabTutor.fxml"));
        AnchorPane attendanceTabContent = loader.load();
        homePageTabPane.getTabs().add(attendanceTab);
        attendanceTab.setText("Attendance");
        attendanceTab.setContent(attendanceTabContent);
    }

    public void createTutorshipTabRS() throws IOException, SQLException {
        checkTabLimit(new PersonalTutorTab(homePageTabPane, loader));
    }

    public void archiveCreateTutorshipTabRS() throws IOException, SQLException {
        checkTabLimit(new ArchivePersonalTutorTab(homePageTabPane, loader));
    }

    public void showProfile() throws IOException, SQLException {
        new ProfileWindow(ghostSessionL);
    }

    public void showSettings() throws IOException {
        new SettingsWindow();
    }

    public void showBooking() throws IOException, SQLException {
        new BookingWindow();
    }

    public void createTutorshipTabT() throws IOException {
        Tab tutorshipTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/PersonalTutorshipTutorTab.fxml"));
        AnchorPane tutorshipTabContent = loader.load();
        homePageTabPane.getTabs().add(tutorshipTab);
        tutorshipTab.setText("Personal Tutorship");
        tutorshipTab.setContent(tutorshipTabContent);
    }

    public void createNotifTab() throws IOException {
        Tab notificationTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/NotificationsTab.fxml"));
        AnchorPane notificationTabContent = loader.load();
        homePageTabPane.getTabs().add(notificationTab);
        notificationTab.setText("Notifications");
        notificationTab.setContent(notificationTabContent);
    }

    public void showDiary() throws SQLException {
        new CalendarWindow(ghostSessionL);
    }

    public void createHomeTabRS() throws IOException {
        checkTabLimit(new HomeTabRS(homePageTabPane, loader));
    }

    public void createNotificationsTab() throws IOException {
        checkTabLimit(new NotificationTab(homePageTabPane, loader));
    }

    public void createHomeTabCL() throws IOException {
        Tab homeTab = new Tab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/HomePageTutor.fxml"));
        AnchorPane homeTabContent = loader.load();
        homePageTabPane.getTabs().add(homeTab);
        homeTab.setText("Home");
        homeTab.setContent(homeTabContent);
        homePageTabPane.getSelectionModel().select(homeTab);
    }

    public void createHomeTabT() throws IOException {
        checkTabLimit(new HomeTabTutor(homePageTabPane, loader));
    }

    public void createIMChat() throws IOException {
        new UserListPane(loginLabel.getText());
    }

    public void createTutorTab() throws IOException, SQLException {
        checkTabLimit(new TutorAppointmentTab(homePageTabPane, loader, ghostSessionL.getText()));
    }

    public void refresh() throws IOException, SQLException {
        System.out.println("Refresh method called.");
        for (int i = 0; i < homePageTabPane.getTabs().size(); i++) {
            tabArrayList.add(homePageTabPane.getTabs().get(i));
        }
        currentTabView = homePageTabPane.getSelectionModel().getSelectedIndex();
        homePageTabPane.getTabs().clear();
        for (Tab tab : tabArrayList) {
            if (tab instanceof StudentTab) {
                StudentTab st = new StudentTab(homePageTabPane, loader, null);
            } else if (tab instanceof StaffTab) {
                StaffTab staffTab = new StaffTab(homePageTabPane, loader, null);
            } else if (tab instanceof ModuleTab) {
                ModuleTab moduleTab = new ModuleTab(homePageTabPane, loader, ghostSessionL);
            } else if (tab instanceof CourseTab) {
                CourseTab courseTab = new CourseTab(homePageTabPane, loader);
            } else if (tab instanceof AssessmentTab) {
                AssessmentTab assessmentTab = new AssessmentTab(homePageTabPane, loader, ghostSessionL);
            } else if (tab instanceof GradesTab) {
                GradesTab gradesTab = new GradesTab(homePageTabPane, loader, ghostSessionL);
            } else if (tab instanceof PersonalTutorTab) {
                PersonalTutorTab tutorTab = new PersonalTutorTab(homePageTabPane, loader);
            } else if (tab instanceof ArchiveStudentTab) {
                ArchiveStudentTab archiveStudentTab = new ArchiveStudentTab(homePageTabPane, loader, null);
            } else if (tab instanceof ArchiveStaffTab) {
                ArchiveStaffTab archiveStaffTab = new ArchiveStaffTab(homePageTabPane, loader);
            } else if (tab instanceof ArchiveAssessmentTab) {
                ArchiveAssessmentTab archiveAssessmentTab = new ArchiveAssessmentTab(homePageTabPane, loader);
            } else if (tab instanceof ArchiveCourseTab) {
                ArchiveCourseTab archiveCourseTab = new ArchiveCourseTab(homePageTabPane, loader);
            } else if (tab instanceof ArchiveGradesTab) {
                ArchiveGradesTab archiveGradesTab = new ArchiveGradesTab(homePageTabPane, loader);
            } else if (tab instanceof ArchiveModuleTab) {
                ArchiveModuleTab archiveModuleTab = new ArchiveModuleTab(homePageTabPane, loader);
            } else if (tab instanceof ArchivePersonalTutorTab) {
                ArchivePersonalTutorTab archivePersonalTutorTab = new ArchivePersonalTutorTab(homePageTabPane, loader);
            } else if (tab instanceof ArchiveAttendanceTab) {
                ArchiveAttendanceTab archiveAttendanceTab = new ArchiveAttendanceTab(homePageTabPane, loader);
            } else if (tab instanceof FormsTab) {
                FormsTab formsTab = new FormsTab(homePageTabPane, loader);
            } else if (tab instanceof HomeTabRS) {
                HomeTabRS homeTabRS = new HomeTabRS(homePageTabPane, loader);
            } else if (tab instanceof HomeTabTutor) {
                HomeTabTutor homeTabTutor = new HomeTabTutor(homePageTabPane, loader);
            } else if (tab instanceof NotificationTab) {
                NotificationTab notificationTab = new NotificationTab(homePageTabPane, loader);
            } else {
                homePageTabPane.getTabs().add(tab);
            }
            homePageTabPane.getSelectionModel().select(currentTabView);
        }
        tabArrayList.removeAll(tabArrayList);
    }

    public void endSession() throws IOException {
        Stage stage = (Stage) logoutBtn.getScene().getWindow();
        stage.close();
        Parent loginPage = FXMLLoader.load(getClass().getResource("/FXMLview/Login.fxml"));
        Scene scene = new Scene(loginPage);
        scene.getStylesheets().add("main.css");
        stage.setScene(scene);
        stage.setTitle("Woodlands RMS");
        stage.show();
    }

    private void checkTabLimit(Tab tab) {
        if (homePageTabPane.getTabs().size() > 30) {
            tabLimitError.setTitle("Tab Limit Reached!");
            tabLimitError.setHeaderText(null);
            tabLimitError.setContentText("Please close some tabs before attempting to open more! (Max 30)");
            homePageTabPane.getTabs().remove(10);
            tabLimitError.showAndWait();
        }
    }


    public void openWebsite(ActionEvent event) {

        try {
            Desktop.getDesktop().browse(new URI("https://woodlandsuc.uk/"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void openTwitter(ActionEvent event) {

        try {
            Desktop.getDesktop().browse(new URI("https://www.twitter.com"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void openLinkedin(ActionEvent event) {

        try {
            Desktop.getDesktop().browse(new URI("https://www.linkedin.com"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void openGoogle(ActionEvent event) {

        try {
            Desktop.getDesktop().browse(new URI("https://www.google.com"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void openFacebook(ActionEvent event) {

        try {
            Desktop.getDesktop().browse(new URI("https://www.facebook.com"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void openWilde(ActionEvent event) {

        try {
            Desktop.getDesktop().browse(new URI("https://www.google.com"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void saveFaqRecordsStaff() {
        File attendanceForm = Paths.get("PDFforms/faq.pdf").toFile();
        FileChooser fileSaver = new FileChooser();
        fileSaver.setTitle("Save FAQ Form");
        File destination = fileSaver.showSaveDialog(homePaneRS.getScene().getWindow());
        if (destination != null) {
            try {
                Files.copy(attendanceForm.toPath(), destination.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveFaqTutor() {
        File attendanceForm = Paths.get("PDFforms/faq.pdf").toFile();
        FileChooser fileSaver = new FileChooser();
        fileSaver.setTitle("Save FAQ Form");
        File destination = fileSaver.showSaveDialog(homePaneT.getScene().getWindow());
        if (destination != null) {
            try {
                Files.copy(attendanceForm.toPath(), destination.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveTosRecordsStaff() {
        File attendanceForm = Paths.get("PDFforms/tos.pdf").toFile();
        FileChooser fileSaver = new FileChooser();
        fileSaver.setTitle("Save FAQ Form");
        File destination = fileSaver.showSaveDialog(homePaneRS.getScene().getWindow());
        if (destination != null) {
            try {
                Files.copy(attendanceForm.toPath(), destination.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveTosTutor() {
        File attendanceForm = Paths.get("PDFforms/tos.pdf").toFile();
        FileChooser fileSaver = new FileChooser();
        fileSaver.setTitle("Save FAQ Form");
        File destination = fileSaver.showSaveDialog(homePaneT.getScene().getWindow());
        if (destination != null) {
            try {
                Files.copy(attendanceForm.toPath(), destination.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showNotifications() {
        TranslateTransition openTransition = new TranslateTransition(new Duration(350), notifPane);
        openTransition.setToX(0);
        TranslateTransition closeTransition = new TranslateTransition(new Duration(350), notifPane);
        notifBtn.setOnAction((ActionEvent event) -> {
            if (notifPane.getTranslateX() != 0) {
                openTransition.play();
            } else {
                closeTransition.setToX(-(notifPane.getWidth()));
                closeTransition.play();
            }
        });
    }

    public void showNotificationsTutor() {
        TranslateTransition openTransition = new TranslateTransition(new Duration(350), notifPaneTutor);
        openTransition.setToX(0);
        TranslateTransition closeTransition = new TranslateTransition(new Duration(350), notifPaneTutor);
        tutorNotifBtn.setOnAction((ActionEvent event) -> {
            if (notifPaneTutor.getTranslateX() != 0) {
                openTransition.play();
            } else {
                closeTransition.setToX(-(notifPaneTutor.getWidth()));
                closeTransition.play();
            }
        });
    }

    public void populateSearchCB() {
        searchCB.getItems().add("STUDENTS");
        searchCB.getItems().add("STAFF");
        searchCB.getItems().add("MODULES");
        searchCB.getItems().add("COURSES");
        searchCB.getItems().add("ASSESSMENTS");
        searchCB.getItems().add("GRADES");
        searchCB.getItems().add("ATTENDANCE");
        searchCB.getItems().add("PERSONAL TUTOR");
    }

    public void populateSearchCBT() {
        searchCBT.getItems().add("ASSESSMENTS");
        searchCBT.getItems().add("ATTENDANCE");
        searchCBT.getItems().add("GRADES");
        searchCBT.getItems().add("MODULES");
    }

    public void performSearchByTabAndID() throws IOException, SQLException {
        String id = searchTF.getText();
        if (searchCB.getValue().equalsIgnoreCase("STUDENTS")) {
            checkTabLimit(new StudentTab(homePageTabPane, loader, id));
        } else if (searchCB.getValue().equalsIgnoreCase("STAFF")) {
            checkTabLimit(new StaffTab(homePageTabPane, loader, id));
        }
    }

    public void showTimetable() {
        new TimetableWindow();
    }
}

