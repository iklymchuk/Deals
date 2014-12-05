package ua.ukrdev.deal.controller;

import static java.lang.System.out;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.ukrdev.deal.form.User;
import ua.ukrdev.deal.service.UserService;

@Controller
@RequestMapping("/login.html")
public class LoginFormController {
	
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

    // Process the form.
    @RequestMapping(method = RequestMethod.POST)
    public String loginProcess(@Valid User user,
                               BindingResult result, Map<String, Object> map) throws IOException {
        // set custom Validation by user
        loginValidation.validate(user, result);
        if (result.hasErrors())  {
            out.println("Error on login attempt: " + result.getAllErrors());
            return "login";
            
        } else if (userService.checkIsLocked(user.getUsername(), "yes")) {
        	out.println("Error on login attempt: Account was locked!");
            return "locked";
        } else {
        	 map.put("user", new User());
             //map.put("listUsers", userService.listUsers("User"));
             map.put("assignUsers", userService.getAssignUsers(user.getUsername()));
             map.put("currentUser", userService.getCurrentUser(user.getUsername()));
        	return "profileUser";
        }
    }   

}    

