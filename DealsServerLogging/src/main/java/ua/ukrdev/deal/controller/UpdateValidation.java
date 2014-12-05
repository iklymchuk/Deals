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
		
		if (!(user.getBalance() instanceof Integer) || user.getBalance() <=0) {
			errors.rejectValue("balance", "Incorrec balance");
		} 
	}
}
