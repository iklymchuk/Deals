package ua.ukrdev.deal.controller;

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
@RequestMapping("/profileUser.html")
public class ProfileUserContoller {
    
    @Autowired
    private UserService userService;

    // Display the form on the get request
    @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.GET)
    public ModelAndView updateUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = userService.getUserById(userId);
        ModelAndView model = new ModelAndView("updateUser");
        model.addObject("user", user);
        
        System.out.print("User in Get on updateUser controller is:" + user.getLname());
        
        return model;      
    
	 }

    @RequestMapping(method = RequestMethod.POST)
    public String loginProcess(@Valid User user,
                               BindingResult result, Map<String, Object> map) throws IOException {

        	 map.put("user", new User());
             //map.put("listUsers", userService.listUsers("User"));
             map.put("assignUsers", userService.getAssignUsers(user.getUsername()));
             map.put("currentUser", userService.getCurrentUser(user.getUsername()));

        	return "updateUser";
        }

}
