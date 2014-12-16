package ua.ukrdev.deal.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.ukrdev.deal.form.User;
import ua.ukrdev.deal.service.UserService;

@Controller
public class DownloadReport {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/download/xls/{id}", method = RequestMethod.GET)
	public void doSalesReportXLS(@PathVariable Integer id, HttpServletResponse response,
									HttpServletRequest request, Map<String, Object> map) throws ServletException, 
																	IOException, ClassNotFoundException,
																	SQLException, JRException {
		 
		 User user = userService.getUserById(id);
		 
		 	map.put("user", user);
		 	map.put("assignUser", userService.getAssignUser(user.getAssign()));    
		 	map.put("contextPath", request.getContextPath());
	        
	        	System.out.print("User in Get on download controller is:" + user.getUsername());
		 
		// Call DownloadService to do the actual report processing
		userService.downloadXLS(response, user.getUsername());
	}

}
