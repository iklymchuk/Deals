package ua.ukrdev.deal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.ukrdev.deal.form.User;
import ua.ukrdev.deal.service.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

import static java.lang.System.out;

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
        } else if (userService.checkRole(user.getUsername(), user.getPassword(), "MasterAdministrator")) {
        	map.put("user", new User());
            map.put("listUsers", userService.listUsers("MasterDealer"));
        	return "PageMasterAdministrator";
        } else if (userService.checkRole(user.getUsername(), user.getPassword(), "MasterDealer")) {
        	map.put("user", new User());
            map.put("listUsers", userService.listUsers("Dealer"));
        	return "PageMasterDealer";
        } else if (userService.checkRole(user.getUsername(), user.getPassword(), "Dealer")) {
        	 map.put("user", new User());
             map.put("listUsers", userService.listUsers("User"));
        	return "PageDealer";
        }
        out.println("User " + user.getUsername() + " logged in " + user.getRole());
        
        return "PageUser";
    }
 /*   
    @RequestMapping("/PageMasterAdministrator")
    public String listContactsMasterAdministrator(Map<String, Object> map) {

        map.put("user", new User());
        map.put("listUsers", userService.listUsers("MasterDealer"));

        return "PageMasterAdministrator";
    }
    
    @RequestMapping("/PageMasterDealer")
    public String listContactsPageMasterDealer(Map<String, Object> map) {

        map.put("user", new User());
        map.put("listUsers", userService.listUsers("Dealer"));

        return "PageMasterDealer";
    }
    
    @RequestMapping("/PageDealer")
    public String listContactsDealer(Map<String, Object> map) {

        map.put("user", new User());
        map.put("listUsers", userService.listUsers("User"));

        return "PageDealer";
    }
*/
}
