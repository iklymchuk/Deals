package ua.ukrdev.deal.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.ukrdev.deal.form.User;
import ua.ukrdev.deal.service.UserService;

@Controller
@RequestMapping("/updateUser.html")
public class UpdateUser {
	    
	    @Autowired
	    private UserService userService;

	    @RequestMapping(value="/updateUser/{id}", method = RequestMethod.GET)
	    public ModelAndView editPage(@PathVariable Integer id) {
	    	ModelAndView modelAndView = new ModelAndView("updateUser");
	        User user = userService.getUserById(id);
	        modelAndView.addObject("user", user);
	        return modelAndView;
	    }
	    
	    @RequestMapping(value="/team/edit/{id}", method=RequestMethod.POST)
	    public ModelAndView edditingUser (@ModelAttribute User user, @PathVariable Integer id) {
	    	ModelAndView modelAndView = new ModelAndView("UserPage");
	    	userService.updateUser(user);
	    	return modelAndView;
	    }	    
}
	