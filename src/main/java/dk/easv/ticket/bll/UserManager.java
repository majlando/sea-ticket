package dk.easv.ticket.bll;

import dk.easv.ticket.be.User;
import dk.easv.ticket.dal.IUserDAO;
import dk.easv.ticket.dal.MockUserDAO;
import java.util.List;

public class UserManager {
    private final IUserDAO userDAO;

    public UserManager() { userDAO = new MockUserDAO(); }

    public User authenticate(String username, String password) {
        return userDAO.authenticate(username, password);
    }

    public List<User> getAllUsers() { return userDAO.getAllUsers(); }
    public void createUser(User user) { userDAO.createUser(user); }
    public void deleteUser(User user) { userDAO.deleteUser(user); }
    public void updateUser(User user) { userDAO.updateUser(user); }
}
