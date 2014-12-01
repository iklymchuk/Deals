package ua.ukrdev.deal.controller;

import static java.lang.System.out;

import java.io.IOException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.ukrdev.deal.form.User;
import ua.ukrdev.deal.service.UserService;

@Controller
@RequestMapping("/updateUser/{id}")
public class UpdateUser {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
		public ModelAndView editPage(@PathVariable Integer id) {
			ModelAndView modelAndView = new ModelAndView("updateUser");
			User user = userService.getUserById(id);
			modelAndView.addObject("user", user);
			return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	 public String updateUser () throws IOException {
		
		User user = new User();
		
		
		user.setId(3);
		user.setUsername("newUser21");
		user.setFname("newUser21");
		user.setLname("newUser21");
		user.setPassword("newUse21");
		user.setConfirmPassword("newUse2r1");
		user.setEmail("newUser21");
		
		
		 //user.setBalance(15);
         //user.setAssign("asign");
         user.setIslock("0");
     		
     	userService.updateUser(user);
        
        return "index";
	}
	
    private String getInputAssign (@RequestParam("assignValue") String assignValue) {
		return assignValue;
    }
    
    private String getInputBalance (@RequestParam("addBalance") String balance) {
		return balance;
    }
}
