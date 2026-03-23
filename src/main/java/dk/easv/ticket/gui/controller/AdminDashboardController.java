package dk.easv.ticket.gui.controller;

import dk.easv.ticket.be.Role;
import dk.easv.ticket.be.User;
import dk.easv.ticket.gui.model.UserModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.Optional;

public class AdminDashboardController {
    @FXML private Label lblWelcome;

    @FXML private TextField txtSearch;
    @FXML private ComboBox<Role> cbFilterRole;

    @FXML private TableView<User> tblUsers;
    @FXML private TableColumn<User, Integer> colId;
    @FXML private TableColumn<User, String> colUsername;
    @FXML private TableColumn<User, String> colPassword;
    @FXML private TableColumn<User, Role> colRole;

    @FXML private TextField txtNewUsername;
    @FXML private TextField txtNewPassword;
    @FXML private ComboBox<Role> cbRole;
    @FXML private Label lblStatus;

    private UserModel userModel;
    private User currentUser;
    private FilteredList<User> filteredUsers;

    public void init(UserModel userModel, User currentUser) {
        this.userModel = userModel;
        this.currentUser = currentUser;

        lblWelcome.setText("Welcome, " + currentUser.getUsername() + " (" + currentUser.getRole() + ")");

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colPassword.setCellValueFactory(data -> new SimpleStringProperty("****"));

        // Filter list wraps the observable list so search/role filter work live
        filteredUsers = new FilteredList<>(userModel.getObservableUsers(), p -> true);
        tblUsers.setItems(filteredUsers);

        cbRole.getItems().addAll(Role.values());
        cbFilterRole.getItems().addAll(Role.values());

        txtSearch.textProperty().addListener((obs, oldVal, newVal) -> applyFilter());
        cbFilterRole.valueProperty().addListener((obs, oldVal, newVal) -> applyFilter());

        // Selecting a row populates the input fields
        tblUsers.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                txtNewUsername.setText(newVal.getUsername());
                txtNewPassword.setText(newVal.getPassword());
                cbRole.setValue(newVal.getRole());
                lblStatus.setText("");
            }
        });
    }

    private void applyFilter() {
        filteredUsers.setPredicate(user -> {
            String search = txtSearch.getText().toLowerCase().trim();
            Role roleFilter = cbFilterRole.getValue();
            boolean matchesSearch = search.isEmpty() || user.getUsername().toLowerCase().contains(search);
            boolean matchesRole = roleFilter == null || user.getRole() == roleFilter;
            return matchesSearch && matchesRole;
        });
    }

    @FXML
    private void handleCreateUser() {
        String username = txtNewUsername.getText().trim();
        String password = txtNewPassword.getText().trim();
        Role role = cbRole.getValue();

        if (username.isEmpty() || password.isEmpty() || role == null) {
            lblStatus.setText("Please fill in all fields.");
            return;
        }

        if (usernameExists(username, -1)) {
            lblStatus.setText("Username already exists.");
            return;
        }

        userModel.createUser(new User(username, password, role));
        handleClearFields();
    }

    @FXML
    private void handleUpdateUser() {
        User selected = tblUsers.getSelectionModel().getSelectedItem();
        if (selected == null) {
            lblStatus.setText("Select a user to update.");
            return;
        }

        String username = txtNewUsername.getText().trim();
        String password = txtNewPassword.getText().trim();
        Role role = cbRole.getValue();

        if (username.isEmpty() || password.isEmpty() || role == null) {
            lblStatus.setText("Please fill in all fields.");
            return;
        }

        if (usernameExists(username, selected.getId())) {
            lblStatus.setText("Username already exists.");
            return;
        }

        if (selected.getId() == currentUser.getId() && role != currentUser.getRole()) {
            lblStatus.setText("You cannot change your own role.");
            return;
        }

        selected.setUsername(username);
        selected.setPassword(password);
        selected.setRole(role);
        userModel.updateUser(selected);
        lblStatus.setText("");
    }

    @FXML
    private void handleDeleteUser() {
        User selected = tblUsers.getSelectionModel().getSelectedItem();
        if (selected == null) {
            lblStatus.setText("Select a user to delete.");
            return;
        }

        if (selected.getId() == currentUser.getId()) {
            lblStatus.setText("You cannot delete your own account.");
            return;
        }

        long adminCount = userModel.getObservableUsers().stream()
                .filter(u -> u.getRole() == Role.ADMIN).count();
        if (selected.getRole() == Role.ADMIN && adminCount <= 1) {
            lblStatus.setText("Cannot delete the last admin account.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Delete user '" + selected.getUsername() + "'?");
        alert.setContentText("This cannot be undone.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isEmpty() || result.get() != ButtonType.OK) return;

        userModel.deleteUser(selected);
        lblStatus.setText("");
    }

    @FXML
    private void handleClearFields() {
        tblUsers.getSelectionModel().clearSelection();
        txtNewUsername.clear();
        txtNewPassword.clear();
        cbRole.getSelectionModel().clearSelection();
        lblStatus.setText("");
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

    private boolean usernameExists(String username, int excludeId) {
        return userModel.getObservableUsers().stream()
                .anyMatch(u -> u.getUsername().equalsIgnoreCase(username) && u.getId() != excludeId);
    }
}
