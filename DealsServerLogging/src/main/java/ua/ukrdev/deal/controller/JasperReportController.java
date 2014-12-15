package ua.ukrdev.deal.controller;

import static java.lang.System.out;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.ukrdev.deal.form.User;
import ua.ukrdev.deal.service.UserService;

@Controller
@RequestMapping("/report/")
public class JasperReportController {
	
	@Autowired
	private UserService userService;
	
   @RequestMapping(method = RequestMethod.GET , value = "pdf")
   public ModelAndView generatePdfReport(ModelAndView modelAndView){

	   out.println("--------------generate PDF report----------");

       Map<String,Object> parameterMap = new HashMap<String,Object>();
       
       User user = userService.getUserById(2);

       List<User> usersList = userService.getAssignUsers(user.getUsername());

       JRDataSource JRdataSource = new JRBeanCollectionDataSource(usersList);

       parameterMap.put("datasource", JRdataSource);

       //pdfReport bean has ben declared in the jasper-views.xml file
       modelAndView = new ModelAndView("pdfReport", parameterMap);

       return modelAndView;

	}

}
