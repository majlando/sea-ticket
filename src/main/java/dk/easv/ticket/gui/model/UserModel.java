package dk.easv.ticket.gui.model;

import dk.easv.ticket.be.User;
import dk.easv.ticket.bll.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserModel {
    private ObservableList<User> usersToBeViewed;
    private UserManager userManager;

    public UserModel() {
        userManager = new UserManager();
        usersToBeViewed = FXCollections.observableArrayList();
        usersToBeViewed.addAll(userManager.getAllUsers());
    }

    public User authenticate(String username, String password) {
        return userManager.authenticate(username, password);
    }

    public ObservableList<User> getObservableUsers() {
        return usersToBeViewed;
    }

    public void createUser(User user) {
        userManager.createUser(user);
        usersToBeViewed.add(user); // Updates the UI instantly
    }

    public void deleteUser(User user) {
        userManager.deleteUser(user);
        usersToBeViewed.remove(user);
    }

    public void updateUser(User user) {
        userManager.updateUser(user);
        // Replace the item in the list so the TableView detects the change
        int index = usersToBeViewed.indexOf(user);
        if (index >= 0) {
            usersToBeViewed.set(index, user);
        }
    }
}