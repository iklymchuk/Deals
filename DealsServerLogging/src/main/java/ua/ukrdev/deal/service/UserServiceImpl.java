package ua.ukrdev.deal.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.ukrdev.deal.dao.UserDao;
import ua.ukrdev.deal.form.User;
import ua.ukrdev.deal.util.Exporter;
import ua.ukrdev.deal.util.ExporterPdf;

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

	public Session getSession() {
		return userDAO.getSession();
	}
	
	@SuppressWarnings("unchecked")
	public void downloadXLS(HttpServletResponse response, String assign) throws JRException {

	// Retrieve our data source
	
	JRDataSource ds = userDAO.getDataSource(assign);
	 
	// params is used for passing extra parameters
	HashMap params = new HashMap();
	params.put("Title", "DealsReport");
	 
	InputStream reportStream = this.getClass().getResourceAsStream("/reportForDeals.jrxml");
	 
	// Retrieve our report template
	JasperDesign jd = JRXmlLoader.load(reportStream);
	 
	// You can also load the template by directly adding the actual path, i.e.
	//JasperDesign jd = JRXmlLoader.load("c:/krams/jasper/reports/tree-template.jrxml");
	 
	// You can also let Spring inject the template
	// See http://stackoverflow.com/questions/734671/read-file-in-classpath
	 
	// Compile our report layout
	JasperReport jr = JasperCompileManager.compileReport(jd);
	 
	// Creates the JasperPrint object
	// It needs a JasperReport layout and a datasource
	JasperPrint jp = JasperFillManager.fillReport(jr, params, ds);
	 
	// Create our output byte stream
	// This is the stream where the data will be written
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 
	// Export to output stream
	// The data will be exported to the ByteArrayOutputStream baos
	// We delegate the exporting  to a custom Exporter instance
	// The Exporter is a wrapper class I made. Feel free to remove or modify it
	Exporter exporter = new Exporter();
	exporter.export(jp, baos);
	 
	// Set our response properties
	// Here you can declare a custom filename
	String fileName = "DealsReport.xls";
	response.setHeader("Content-Disposition", "inline; filename="
	+ fileName);
	// Make sure to set the correct content type
	// Each format has its own content type
	response.setContentType("application/vnd.ms-excel");
	response.setContentLength(baos.size());
	 
	// Write to reponse stream
	writeReportToResponseStream(response, baos);
	}
	
	@SuppressWarnings("unchecked")
	public void downloadPdf(HttpServletResponse response, String assign) throws JRException {

	// Retrieve our data source
	
	JRDataSource ds = userDAO.getALLDataSource();
	 
	// params is used for passing extra parameters
	HashMap params = new HashMap();
	params.put("Title", "DealsReport");
	 
	InputStream reportStream = this.getClass().getResourceAsStream("/reportForDeals.jrxml");
	 
	// Retrieve our report template
	JasperDesign jd = JRXmlLoader.load(reportStream);
	 
	// You can also load the template by directly adding the actual path, i.e.
	//JasperDesign jd = JRXmlLoader.load("c:/krams/jasper/reports/tree-template.jrxml");
	 
	// You can also let Spring inject the template
	// See http://stackoverflow.com/questions/734671/read-file-in-classpath
	 
	// Compile our report layout
	JasperReport jr = JasperCompileManager.compileReport(jd);
	 
	// Creates the JasperPrint object
	// It needs a JasperReport layout and a datasource
	JasperPrint jp = JasperFillManager.fillReport(jr, params, ds);
	 
	// Create our output byte stream
	// This is the stream where the data will be written
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 
	// Export to output stream
	// The data will be exported to the ByteArrayOutputStream baos
	// We delegate the exporting  to a custom Exporter instance
	// The Exporter is a wrapper class I made. Feel free to remove or modify it
	ExporterPdf exporter = new ExporterPdf();
	exporter.export(jp, baos);
	 
	// Set our response properties
	// Here you can declare a custom filename
	String fileName = "DealsReport.pdf";
	response.setHeader("Content-Disposition", "inline; filename="
	+ fileName);
	// Make sure to set the correct content type
	// Each format has its own content type
	response.setContentType("application/pdf");
	response.setContentLength(baos.size());
	 
	// Write to reponse stream
	writeReportToResponseStream(response, baos);
	}
	
	private void writeReportToResponseStream(HttpServletResponse response,	ByteArrayOutputStream baos) {

		try {
			// Retrieve the output stream
			ServletOutputStream outputStream = response.getOutputStream();
			// Write to the output stream
			baos.writeTo(outputStream);
			// Flush the stream
			outputStream.flush();
			 
		} catch (Exception e) {

		}
	}

	public JRDataSource getALLDataSource() {
		return userDAO.getALLDataSource();
	}

	public List<User> getAssignReportUsers(String assign, String[] fields) {
		return userDAO.getAssignReportUsers(assign, fields);
	}
}