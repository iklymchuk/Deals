package ua.ukrdev.deal.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import ua.ukrdev.deal.form.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eugene on 15.11.2014.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;
    

    public Session getSession() {
    	return sessionFactory.getCurrentSession();
    }
    
    public Integer addUser(User user1) {
        return (Integer) sessionFactory.getCurrentSession().save(user1);
    }

    @SuppressWarnings("unchecked")
    public List<User> listUsers(String role) {
        //return sessionFactory.getCurrentSession().createCriteria(User.class).list();
    	Query query = sessionFactory.getCurrentSession().createQuery("from User where role = :role");
    	
    	query.setParameter("role", role);
	    return query.list();
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

    public User getUserById(Integer id) {  
    	User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
    	return user;
    }  

	public void updateUser (User user) {  
	    	sessionFactory.getCurrentSession().update(user);
    }  

	public User getCurrentUser(String username) {
		List<User> user1List = new ArrayList<User>();
        Query query = sessionFactory.getCurrentSession().createQuery("from User where username = :user");
        query.setParameter("user", username);
        user1List = query.list();
        if (user1List.size() > 0)
            return user1List.get(0);
        else
            return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAssignUsers (String assign) {
	    Query query = sessionFactory.getCurrentSession().createQuery("from User where assign = :assign");
	    query.setParameter("assign", assign);
	    return query.list();
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
	
	public User getAssignUser(String username) {
		List<User> user1List = new ArrayList<User>();
        Query query = sessionFactory.getCurrentSession().createQuery("from User where username = :user");
        query.setParameter("user", username);
        user1List = query.list();
        if (user1List.size() > 0)
            return user1List.get(0);
        else
            return null;
	}
}
