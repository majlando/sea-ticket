package dk.easv.ticket.gui.controller;

import dk.easv.ticket.be.Role;
import dk.easv.ticket.be.User;
import dk.easv.ticket.gui.model.UserModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblMessage;

    private final UserModel userModel = new UserModel();

    @FXML
    private void handleLogin() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        User loggedInUser = userModel.authenticate(username, password);

        if (loggedInUser == null) {
            lblMessage.setText("Invalid username or password.");
            return;
        }

        try {
            Stage stage = (Stage) txtUsername.getScene().getWindow();

            if (loggedInUser.getRole() == Role.ADMIN) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/ticket/AdminDashboard.fxml"));
                Parent root = loader.load();
                AdminDashboardController controller = loader.getController();
                controller.init(userModel, loggedInUser);
                stage.setTitle("Admin Dashboard — " + loggedInUser.getUsername());
                stage.setScene(new Scene(root));
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/ticket/EventCoordinatorDashboard.fxml"));
                Parent root = loader.load();
                EventCoordinatorDashboardController controller = loader.getController();
                controller.init(loggedInUser);
                stage.setTitle("Event Coordinator Dashboard — " + loggedInUser.getUsername());
                stage.setScene(new Scene(root));
            }
        } catch (Exception e) {
            e.printStackTrace();
            lblMessage.setText("Error loading dashboard.");
        }
    }
}
