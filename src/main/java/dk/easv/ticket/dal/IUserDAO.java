package dk.easv.ticket.dal;

import dk.easv.ticket.be.User;
import java.util.List;

public interface IUserDAO {
    List<User> getAllUsers();
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    User authenticate(String username, String password);
}
