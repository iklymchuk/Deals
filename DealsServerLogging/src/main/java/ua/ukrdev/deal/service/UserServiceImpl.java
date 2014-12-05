package ua.ukrdev.deal.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.ukrdev.deal.dao.UserDao;
import ua.ukrdev.deal.form.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eugene on 15.11.2014.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UserDao userDAO;

    public Integer addUser(User user) {
        return (Integer) sessionFactory.getCurrentSession().save(user);
    }
    
    @Transactional
    	public List<User> listUsers(String role) {
            return userDAO.listUsers(role);
    }
    
    public User checkIfUserExistsByUsername(String username) {
        List<User> user1List = new ArrayList<User>();
        Query query = sessionFactory.getCurrentSession().createQuery("from User where username = :login");
        query.setParameter("login", username);
        user1List = query.list();
        if (user1List.size() > 0)
            return user1List.get(0);
        else
            return null;
    }

    public User checkIfUserExistsByEmail(String email) {
        List<User> user1List = new ArrayList<User>();
        Query query = sessionFactory.getCurrentSession().createQuery("from User where email = :mail");
        query.setParameter("mail", email);
        user1List = query.list();
        if (user1List.size() > 0)
            return user1List.get(0);
        else
            return null;
    }

    public boolean checkIfUserWithSuchPasswordExists(String username, String password) {
        List<User> user1List = new ArrayList<User>();
        Query query = sessionFactory.getCurrentSession().createQuery("from User where username = :user and password= :pass");
        query.setParameter("user", username);
        query.setParameter("pass", password);
        user1List = query.list();
        if (user1List.size() > 0)
            return true;
        else
            return false;
    }

	public boolean checkRole(String username, String password,
			String role) {
		
		  		List<User> user1List = new ArrayList<User>();
	        Query query = sessionFactory.getCurrentSession().createQuery("from User where username = :user and password= :pass and role = :role");
	        query.setParameter("user", username);
	        query.setParameter("pass", password);
	        query.setParameter("role", role);
	        user1List = query.list();
	        if (user1List.size() > 0)
	            return true;
	        else
	            return false;
	    }
	
	@SuppressWarnings("unchecked")
	public List<User> getAssignUsers (String assign) {
	    Query query = sessionFactory.getCurrentSession().createQuery("from User where assign = :assign");
	    query.setParameter("assign", assign);
	    return query.list();
	}
	
	public User getUserById(Integer id) {  
    	return userDAO.getUserById(id);  
    }  

    public void updateUser (User user) {   
	     userDAO.updateUser(user);
    }  

	public User getCurrentUser(String username) {
		return userDAO.getCurrentUser(username);
	}

	public boolean checkIsLocked(String username, String islock) {
        List<User> user1List = new ArrayList<User>();
        Query query = sessionFactory.getCurrentSession().createQuery("from User where username = :user and islock = :islock");
        query.setParameter("user", username);
        query.setParameter("islock", islock);
        user1List = query.list();
        if (user1List.size() > 0)
            return true;
        else
            return false;
	}  
}