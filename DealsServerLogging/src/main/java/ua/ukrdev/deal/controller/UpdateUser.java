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
        
        System.out.print("User in Get on updateUser controller is:" + user.getLname());
        
        return modelAndView;      
    
	 }
	
	 @RequestMapping(value="/updateUser/{id}", method=RequestMethod.POST)
	 	public ModelAndView edditingUser(@ModelAttribute @Valid User user, BindingResult result,
	 										@PathVariable Integer id) {
		 
		 		updateValidation.validate(user, result);
		 		if (result.hasErrors()) {
		 			out.println("Error on login attempt: " + result.getAllErrors());
		 			return new ModelAndView("updateUser");
		 		}
	
		 			ModelAndView modelAndView = new ModelAndView("profileUser");		
		 				userService.updateUser(user);  
		 					String message = "User was successfully updated.";
		 					modelAndView.addObject("message", message);
	
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

					user.setUsername("newUser21");
					user.setFname("newUser21");
					user.setLname("newUser21");
					user.setPassword("newUse21");
					user.setConfirmPassword("newUse2r1");
					user.setEmail("newUser21");
				
			        user.setIslock("0");
			     		
			     	userService.updateUser(user);
        
        return "profileUser";
	}
*/
}

