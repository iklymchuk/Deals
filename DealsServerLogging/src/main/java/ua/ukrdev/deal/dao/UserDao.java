package ua.ukrdev.deal.dao;

import ua.ukrdev.deal.form.User;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;

import org.hibernate.Session;

/**
 * Created by Eugene on 15.11.2014.
 */
public interface UserDao {
    public Integer addUser(User user);
    public List<User> listUsers(String role);
    public void updateUser(User user);
    public User checkIfUserExistsByUsername(String login);
    public User checkIfUserExistsByEmail(String email);
    public boolean checkIfUserWithSuchPasswordExists(String username, String password);
    public boolean checkRole(String username, String password, String role);
    public User getUserById(Integer userId);
    public User getCurrentUser(String username);
    public List<User> getAssignUsers (String assign);
    public boolean checkIsLocked (String username, String islock);
    public User getAssignUser(String username);
    
    public Session getSession();
    
    public JRDataSource getDataSource(String assign);
    public JRDataSource getALLDataSource ();
    
    public List<User> getAssignReportUsers (String assign, String[] fields);

}
