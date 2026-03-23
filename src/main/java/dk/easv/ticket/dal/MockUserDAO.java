package dk.easv.ticket.dal;

import dk.easv.ticket.be.Role;
import dk.easv.ticket.be.User;
import java.util.ArrayList;
import java.util.List;

public class MockUserDAO implements IUserDAO {
    private List<User> users = new ArrayList<>();
    private int nextId = 6;

    public MockUserDAO() {
        users.add(new User(1, "admin",       "123", Role.ADMIN));
        users.add(new User(2, "coordinator", "123", Role.COORDINATOR));
        users.add(new User(3, "gert",        "123", Role.COORDINATOR));
        users.add(new User(4, "jens",        "123", Role.COORDINATOR));
        users.add(new User(5, "bent",        "123", Role.ADMIN));
    }

    @Override
    public List<User> getAllUsers() { return new ArrayList<>(users); }

    @Override
    public void createUser(User user) {
        user.setId(nextId++);
        users.add(user);
    }

    @Override
    public void deleteUser(User user) { users.remove(user); }

    @Override
    public void updateUser(User updated) {
        for (User u : users) {
            if (u.getId() == updated.getId()) {
                u.setUsername(updated.getUsername());
                u.setPassword(updated.getPassword());
                u.setRole(updated.getRole());
                return;
            }
        }
    }

    @Override
    public User authenticate(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}