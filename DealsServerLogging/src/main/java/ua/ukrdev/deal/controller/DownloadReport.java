package ua.ukrdev.deal.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.ukrdev.deal.form.User;
import ua.ukrdev.deal.service.UserService;
import ua.ukrdev.deal.util.HibernateQueryResultDataSource;
import ua.ukrdev.deal.util.JasperManager;

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


	@RequestMapping(value = "/download/pdf/{id}", method = RequestMethod.GET)
	public void doSalesReportPdf(@PathVariable Integer id, HttpServletResponse response,
									HttpServletRequest request, Map<String, Object> map) throws ServletException, 
																	IOException, ClassNotFoundException,
																	SQLException, JRException {
		 
		 User user = userService.getUserById(id);
		 
		 	map.put("user", user);
		 	map.put("assignUser", userService.getAssignUser(user.getAssign()));    
		 	map.put("contextPath", request.getContextPath());
	        
	        	System.out.print("User in Get on download controller is:" + user.getUsername());
		 
		// Call DownloadService to do the actual report processing
		userService.downloadPdf(response, user.getUsername());
	}
	
	/*
	@RequestMapping(value = "/download/newpdf/{id}", method = RequestMethod.GET)
	public void doNewReportPdf(@PathVariable Integer id, HttpServletResponse response,
									HttpServletRequest request) throws JRException, IOException {
		 
		 		User user = userService.getUserById(id);
	        
	        	System.out.print("User in Get on download controller is:" + user.getUsername());
	        		
/*	        	    	
		// Call DownloadService to do the actual report processing
	   		 String path = request.getSession().getServletContext().getRealPath("/WEB-INF//dealsReportX.jrxml");
			 JasperReport jasperReport = JasperCompileManager.compileReport(path);

				
				//JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(userService.getAssignUsers(user.getUsername()));  
			 
			 String[] fields = new String[] { "type", "birthdate", "name"};
			 
			 HibernateQueryResultDataSource ds = new HibernateQueryResultDataSource(
					 									userService.getAssignReportUsers(user.getUsername(), fields), fields);
			 
			 JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(),  ds);
			 byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
			 
			 //uncomment this line to make browser download the file
			 response.setContentType("application/pdf");
			 response.setHeader("Content-Disposition", "attachment; filename=deals.pdf");
			 
			 response.getOutputStream().write(pdfBytes);
			 response.getOutputStream().flush();
			 response.getOutputStream().close();
			 response.flushBuffer();

*/		
/*			 

Map parameters = new HashMap();
parameters.put("Title", "The Cat Report");

String reportStream = request.getSession().getServletContext().getRealPath("/WEB-INF//dealsReportX.jrxml");
JasperDesign jasperDesign = JasperManager.loadXmlDesign(reportStream);
JasperReport jasperReport = JasperManager.compileReport(jasperDesign);

String[] fields = new String[] { "fname", "lname", "username", "email", "role", "balance"};
HibernateQueryResultDataSource ds = new HibernateQueryResultDataSource(userService.getAssignReportUsers(user.getUsername(), fields), fields);
JasperPrint jasperPrint = JasperManager.fillReport(jasperReport, parameters, ds);

JasperManager.printReportToPdfFile(jasperPrint, "the-cat-report.pdf");
	}
	
	*/

}
