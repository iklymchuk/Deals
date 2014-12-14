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
    public ModelAndView updateUser(@PathVariable Integer id, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("updateUser");
        User user = userService.getUserById(id);
        modelAndView.addObject("user", user);
        modelAndView.addObject("assignUser", userService.getAssignUser(user.getAssign()));    
        
        modelAndView.addObject("contextPath", request.getContextPath());
        
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
	
		 			ModelAndView modelAndView = new ModelAndView("successUpdate");	

		 			user = userService.getUserById(id);
		 	        modelAndView.addObject("user", user);
		 	        modelAndView.addObject("assignUser", userService.getAssignUser(user.getAssign()));  
		 	        
		 	        //set new parameters
			 	       user.setRole(request.getParameter("role"));
			 	       user.setIslock(request.getParameter("islock"));
			 	       user.setAssign(request.getParameter("assign"));

			 	    //set balance
			 	   User assignUser = userService.getAssignUser(user.getAssign());
			 	       
		 	        Integer assignUserBalance = assignUser.getBalance();
		 	        Integer userBalance = user.getBalance();
			 	    Integer inputBalanceValue = Integer.parseInt(request.getParameter("balance"));

			 	    //update assignUser
			 	    if (assignUserBalance >= inputBalanceValue) {
			 	    	
			 	    	if (!userService.checkRole(assignUser.getUsername(), assignUser.getPassword(),
			 	    								"MasterAdministrator")) {
			 	    		assignUser.setBalance(assignUserBalance - inputBalanceValue);
			 				userService.updateUser(assignUser); 
			 	    	}

		 			//update user
		 				user.setBalance(userBalance + inputBalanceValue);
		 				userService.updateUser(user);  
		 				
		 				modelAndView.addObject("currentUser", assignUser);   
		 				modelAndView.addObject("user", user);   

		 				
		 				//modelAndView.addObject("assignUsers", userService.getAssignUsers(user.getUsername()));
		 				//modelAndView.addObject("currentUser", userService.getCurrentUser(user.getUsername()));

		 				return modelAndView;
	 	        
			 	   } else {
			 		   return new ModelAndView("incorrectBalance");	
			 		   
	     }
	 }
}

