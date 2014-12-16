package ua.ukrdev.deal.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

//@Controller
//@RequestMapping("/download")
public class JasperReportController extends HttpServlet {

	protected void doPost(HttpServletRequest request,
										HttpServletResponse response, 
										JRDataSource jrDataSource) throws JRException, IOException {

		 String path = request.getSession().getServletContext().getRealPath("/WEB-INF/dealsRebort2.jrxml");
		 JasperReport jasperReport = JasperCompileManager.compileReport(path);
		 JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), jrDataSource);
		 byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
		 
		 //uncomment this line to make browser download the file
		 response.setContentType("application/pdf");
		 response.setHeader("Content-Disposition", "attachment;filename=xxx.pdf");
		 
		 response.getOutputStream().write(pdfBytes);
		 response.getOutputStream().flush();
		 response.getOutputStream().close();
		 response.flushBuffer();
		 
	}
		 
}
	

	
	
	/*
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView doSalesMultiReport(@Valid User user, @RequestParam("type") String type,
    										ModelAndView modelAndView, ModelMap model) {

		Connection connection = null;		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/deals_db","root", "password");
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
		
		String userName = user.getUsername();
		out.println("Username -->>>" + userName);
		
		String sqlQuery = "SELECT fname, lname, username, balance FROM users where assign = " + userName;
		out.println("sqlQuery -->>>" + sqlQuery);

		JasperReportBuilder report = DynamicReports.report();
		report
			.columns(
					Columns.column("First Name", "fname", DataTypes.stringType()),
				    Columns.column("Last Name", "lname", DataTypes.stringType()),
					Columns.column("User name", "username", DataTypes.stringType()),
					Columns.column("Balance", "balance", DataTypes.integerType()))
					
			.title(
					Components.text("SimpleReportExample")
						.setHorizontalAlignment(HorizontalAlignment.CENTER))
						.pageFooter(Components.pageXofY())//show page number on the page footer
						.setDataSource(sqlQuery, 
								connection);	
			try {
			    //show the report
				report.show();
			
			    //export the report to a pdf file
				//report.toPdf(new FileOutputStream("/home/report.pdf"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return modelAndView;
	}
					
/*
		  JRDataSource datasource  = userService.getDataSource(user.getUsername());
		
		  model.addAttribute("datasource", datasource);
		  // Add the report format
		  model.addAttribute("format", type);
		   
		  // multiReport is the View of our application
		  // This is declared inside the /WEB-INF/jasper-views.xml
		  modelAndView = new ModelAndView("multiReport", model);
		   
		  // Return the View and the Model combined
		  return modelAndView;
    }
    */
    
	/*
   @RequestMapping(method = RequestMethod.GET , value = "pdf")
   public ModelAndView generatePdfReport(ModelAndView modelAndView){

	   out.println("--------------generate PDF report----------");

       Map<String,Object> parameterMap = new HashMap<String,Object>();
       
       User user = userService.getUserById(2);
components
       List<User> usersList = userService.getAssignUsers(user.getUsername());

       JRDataSource JRdataSource = new JRBeanCollectionDataSource(usersList);

       parameterMap.put("datasource", JRdataSource);

       //pdfReport bean has ben declared in the jasper-views.xml file
       modelAndView = new ModelAndView("pdfReport", parameterMap);

       return modelAndView;

	}
	*/

