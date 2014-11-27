package ua.ukrdev.deal.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.ukrdev.deal.form.User;
import ua.ukrdev.deal.service.UserService;

@Controller
@RequestMapping("/profile.html")
public class ProfileController {
	
	@Autowired
    private LoginValidation loginValidation;
    
    @Autowired
    private UserService userService;

    // Display the form on the get request
    @RequestMapping(method = RequestMethod.GET)
    public String showRegistration(Map model) {
        User user = new User();
        model.put("user", user);
        return "login";
    }

    @RequestMapping("delete")
    public ModelAndView deleteUser(@RequestParam int id) {
        userService.removeUser(id);
        return new ModelAndView("redirect:login");
    }
	
	@RequestMapping("edit")  
	 public ModelAndView editUser(@RequestParam int id, @ModelAttribute User user) {  
	  User userObject = userService.getUserById(id);
	  return new ModelAndView("edit", "userObject", userObject);  
	 }  
	  
	 @RequestMapping("update")  
	 public ModelAndView updateUser(@ModelAttribute User user) {  
	  userService.updateUser(user);
	  return new ModelAndView("redirect:login");  
	 }  
}
