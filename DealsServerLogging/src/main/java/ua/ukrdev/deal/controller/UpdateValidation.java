package ua.ukrdev.deal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.ukrdev.deal.form.User;
import ua.ukrdev.deal.service.UserService;

@Component("updateValidator")
public class UpdateValidation {

    @Autowired
    private UserService userService;

	public boolean supports(Class<?> klass) {
		return User.class.isAssignableFrom(klass);
	}

	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		//String isLock = user.getIslock();
		Integer balance = user.getBalance();
		//String assign = user.getAssign();
		
		if (balance <=0 ) {
			errors.rejectValue("balance", "Balance should be > 0");
		} 
		
		if (!(balance instanceof Integer)) {
			errors.rejectValue("balance", "Balance should be Integer");
		} 
	}
}
