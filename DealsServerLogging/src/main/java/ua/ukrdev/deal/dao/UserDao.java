package ua.ukrdev.deal.dao;

import ua.ukrdev.deal.form.User;

import java.util.List;

/**
 * Created by Eugene on 15.11.2014.
 */
public interface UserDao {
    public Integer addUser(User user);
    public List<User> listUsers(String role);
    public void removeUser(Integer id);
    public User checkIfUserExistsByUsername(String login);
    public User checkIfUserExistsByEmail(String email);
    public boolean checkIfUserWithSuchPasswordExists(String username, String password);
    public boolean checkRole(String username, String password, String role);
}
