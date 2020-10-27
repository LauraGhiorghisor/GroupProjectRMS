package view;

import javafx.scene.control.Alert;

public class PasswordHelpWindow {
    private static Alert forgottenPw;

    public PasswordHelpWindow() {

    }

    public static void showPasswordWindow() {
        forgottenPw = new Alert(Alert.AlertType.INFORMATION);
        forgottenPw.setTitle("How to recover your password.");
        forgottenPw.setHeaderText(null);
        forgottenPw.setContentText("Please contact a member of the records staff team to reset your password.");
        forgottenPw.show();
    }
}
