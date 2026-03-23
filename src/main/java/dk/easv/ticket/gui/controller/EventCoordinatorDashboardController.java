package dk.easv.ticket.gui.controller;

import dk.easv.ticket.be.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EventCoordinatorDashboardController {
    @FXML private Label lblWelcome;

    public void init(User currentUser) {
        lblWelcome.setText("Welcome, " + currentUser.getUsername() + " (" + currentUser.getRole() + ")");
    }

    @FXML
    private void handleLogout() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/dk/easv/ticket/Login.fxml"));
            Stage stage = (Stage) lblWelcome.getScene().getWindow();
            stage.setTitle("EASV Bar Ticket System");
            stage.setScene(new Scene(root, 300, 400));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
