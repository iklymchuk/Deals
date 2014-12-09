package ua.ukrdev.deal.controller;

import static java.lang.System.out;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.ukrdev.deal.form.User;
import ua.ukrdev.deal.service.UserService;

@Controller
//@RequestMapping("/updateUser/{id}")
public class UpdateUser {
	
    @Autowired
    private UpdateValidation updateValidation;

	@Autowired
	private UserService userService;
	
	public void setUpdateValidation(
            UpdateValidation updateValidation) {
        this.updateValidation = updateValidation;
    }

	@RequestMapping(value = "/updateUser/{id}", method = RequestMethod.GET)
    public ModelAndView updateUser(@PathVariable Integer id) {

        ModelAndView modelAndView = new ModelAndView("updateUser");
        User user = userService.getUserById(id);
        modelAndView.addObject("user", user);
        modelAndView.addObject("assignUser", userService.getAssignUser(user.getAssign()));    
        
        	System.out.print("User in Get on updateUser controller is:" + user.getLname());
        
        return modelAndView;      
    
	 }

	
	 @RequestMapping(value="/updateUser/{id}", method=RequestMethod.POST)
	 	public ModelAndView edditingUser(@ModelAttribute @Valid User user, BindingResult result,
	 										@PathVariable Integer id,
	 										HttpServletRequest request) {
		 
		 		updateValidation.validate(user, result);
		 		if (result.hasErrors()) {
		 			out.println("Error on login attempt: " + result.getAllErrors());
		 			return new ModelAndView("updateUser");
		 		}
	
		 			ModelAndView modelAndView = new ModelAndView("profileUser");	

		 			user = userService.getUserById(id);
		 	        modelAndView.addObject("user", user);
		 	        modelAndView.addObject("assignUser", userService.getAssignUser(user.getAssign()));  
		 	        
		 	        //set new parameters
			 	       user.setRole(request.getParameter("role"));
			 	       user.setIslock(request.getParameter("islock"));
			 	       user.setAssign(request.getParameter("assign"));

			 	    //set balance
		 	        Integer assignUserBalance = userService.getAssignUser(user.getAssign()).getBalance();
		 	        Integer userBalance = user.getBalance();
			 	    Integer inputBalanceValue = Integer.parseInt(request.getParameter("balance"));
		 	        
			 	       user.setBalance(assignUserBalance + userBalance + inputBalanceValue);

		 
		 			//update
		 				userService.updateUser(user);  

	 	        return modelAndView;
	     }

	
	/*
	@RequestMapping(value = "/updateUser/{id}", method=RequestMethod.POST)
	public String updateUser (@Valid User user,
            BindingResult result, Map<String, Object> map) throws IOException {
		
			updateValidation.validate(user, result);
				if (result.hasErrors()) {
					out.println("Error on login attempt: " + result.getAllErrors());
					return "incorrectBalance";
				}
				
				userService.updateUser(user); 
				
				 map.put("user", new User());
	             //map.put("listUsers", userService.listUsers("User"));
	             map.put("assignUsers", userService.getAssignUsers(user.getUsername()));
	             map.put("currentUser", userService.getCurrentUser(user.getUsername()));
	             
	              
	             
	        	return "login.html";
	}
*/
}

