package org.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private Button close;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void handleClose() {
        close.getScene().getWindow().hide();
    }

    @FXML
    void handleLogin() {
        String correctUsername = "Admin";
        String correctPassword = "Admin2024";

        String enteredUsername = username.getText();
        String enteredPassword = password.getText();

        if (enteredUsername.equals(correctUsername) && enteredPassword.equals(correctPassword)) {
            showAlert("Logged in successfully!");
            try {
                HelloApplication.showEmployeeWindow();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Incorrect username or password. Please try again.");
        }
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login Status");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


