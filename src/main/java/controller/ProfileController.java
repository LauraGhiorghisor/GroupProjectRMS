package controller;



import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;;
import java.io.IOException;
import java.sql.*;


public class ProfileController {
    @FXML
    private AnchorPane profilePane;
    @FXML
    private TextField usernameTF, passwordTF, pictureTF;
    @FXML
    private Label passwordChangeL;
    @FXML
    private ImageView profilePictureIMG;
    private int userID = 0;

    public void loadProfile(int id) throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        Connection rmsConnection = DriverManager.getConnection(dbURL, username, password);
        Statement fetchStaff = rmsConnection.createStatement();
        ResultSet result = fetchStaff.executeQuery("SELECT * FROM users WHERE user_id ='" + id + "'");
        while (result.next()) {
            usernameTF.setText(result.getString("username"));
            passwordTF.setText(result.getString("password"));
            String path = result.getString("profile_picture");
            if (!path.equalsIgnoreCase("image")) {
                Image image = new Image(new File(path).toURI().toString());
                profilePictureIMG.setImage(image);
            }
        }
        userID = id;
    }

    public void changePassword() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
        String username = "root";
        String password = "root";
        String query = ("UPDATE users SET password='" + passwordTF.getText() + "'WHERE user_id='" + userID + "'");
        Connection myCon = DriverManager.getConnection(dbURL, username, password);
        PreparedStatement preparedStatement = myCon.prepareStatement(query);
        preparedStatement.execute();
        passwordChangeL.setVisible(true);
    }

    public void newPhoto() throws SQLException, IOException {
        FileChooser photoGrabber = new FileChooser();
        photoGrabber.setTitle("Upload a photo from system");
        File photo = photoGrabber.showOpenDialog(profilePane.getScene().getWindow());
        String path = photo.getAbsolutePath();
        if (photo != null) {
            pictureTF.setText(photo.getAbsolutePath());
            String dbURL = "jdbc:mysql://localhost:3306/rmsdb";
            String username = "root";
            String password = "root";
            String query = ("UPDATE users SET profile_picture='" + path + "'WHERE user_id='" + userID + "'");
            Connection myCon = DriverManager.getConnection(dbURL, username, password);
            PreparedStatement preparedStatement = myCon.prepareStatement(query);
            preparedStatement.execute();
            loadProfile(userID);
        }
    }
}
