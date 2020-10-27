package controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.AccessLevel;
import model.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import view.PasswordHelpWindow;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LoginController {
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    private FXMLLoader loader;
    @FXML
    private Label invalidIDLabel, invalidPWLabel;
    private boolean accountFound = false;


    public void loginAttempt() throws SQLException, IOException {
        initial();
        String dbUrl = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = "SELECT * FROM users WHERE username ='" + userField.getText() + "'";
        System.out.println(query);
        Connection myConnection = DriverManager.getConnection(dbUrl, username, password);
        PreparedStatement myStatement = myConnection.prepareStatement(query);
        ResultSet myResultSet = myStatement.executeQuery();

        while (myResultSet.next()) {
            initial();
            accountFound = true;
            String userName = myResultSet.getString("username");
            Timestamp lastlogged = myResultSet.getTimestamp("last_logged");
            int accesslevel = myResultSet.getInt("user_level");
            String pass = myResultSet.getString("password");
            int userId = myResultSet.getInt("user_id");
            //If password matches the password field in the UI
            if (pass.equals(passwordField.getText())) {
                Session session = new Session(userName, AccessLevel.fromInt(accesslevel), lastlogged, userId);
                //If the user is a records staff
                if (session.getAccessLevel().hasAccessToSysAdminView() || session.getAccessLevel().hasAccessToRecView()) {
                    //Transition to records staff view
                    loader = new FXMLLoader(getClass().getResource("/FXMLview/HomePageRecordsStaff.fxml"));
                    Parent homePage = loader.load();
                    loginButton.getScene().setRoot(homePage);
                    Object temp = loader.getController();
                    HomePageController controller = (HomePageController) temp;
                    controller.setLoginUsername(session.getUsername());
                    controller.setLastLogLabel("Last logged in: " + session.getLastLogged());
                    controller.setGhostSessionL(Integer.toString(session.getId()));
                    controller.setDateLabel();
                    controller.createHomeTab(session);
                    controller.showNotifications();
                    controller.populateSearchCB();
                    //If the user is a course leader
                    //Transition to course leader view
                } else if (session.getAccessLevel().hasAccessToCourseLeadView()) {
                    loader = new FXMLLoader(getClass().getResource("/FXMLview/HomePageCourseLeader.fxml"));
                    Parent homePage = loader.load();
                    loginButton.getScene().setRoot(homePage);
                    Object temp = loader.getController();
                    HomePageController controller = (HomePageController) temp;
                    controller.setLoginUsername(session.getUsername());
                    controller.setLastLogLabel("Last logged in: " + session.getLastLogged());
                    controller.setGhostSessionL(Integer.toString(session.getId()));
                    controller.setDateLabel();
                    controller.createHomeTab(session);

                } else {
                    //else is a tutor
                    //Transition to tutor staff view
                    loader = new FXMLLoader(getClass().getResource("/FXMLview/HomePageTutor.fxml"));
                    Parent homePage = loader.load();
                    loginButton.getScene().setRoot(homePage);
                    Object temp = loader.getController();
                    HomePageController controller = (HomePageController) temp;
                    controller.setLoginUsername(session.getUsername());
                    controller.setLastLogLabel("Last logged in: " + session.getLastLogged());
                    controller.setGhostSessionL(Integer.toString(session.getId()));
                    controller.setDateLabel();
                    controller.createHomeTab(session);
                    controller.showNotificationsTutor();
                    controller.populateSearchCBT();
                }
                Calendar currentDate = Calendar.getInstance();
                SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
                String dateNow = formatter.format(currentDate.getTime());
                System.out.println(dateNow);
                String updateLog = "UPDATE users SET last_logged='" + dateNow + "' WHERE user_id='" + session.getId() + "'";
                PreparedStatement updateLoggedin = myConnection.prepareStatement(updateLog);
                updateLoggedin.execute();
                //If password does not match, show invalid password prompt
            } else {
                invalidPWLabel.setVisible(true);
            }
        }
        //If account cant be found, show invalid login prompt.
        if (!accountFound)
            invalidIDLabel.setVisible(true);
    }

    public void initial() {
        invalidPWLabel.setVisible(false);
        invalidIDLabel.setVisible(false);
        accountFound = false;
    }

    public void keyPressed(KeyEvent key) throws ParseException, SQLException, IOException {
        if (key.getCode() == KeyCode.ENTER) {
            loginAttempt();
        }
    }

    public void showPasswordHelp() {
        PasswordHelpWindow.showPasswordWindow();
    }
}
