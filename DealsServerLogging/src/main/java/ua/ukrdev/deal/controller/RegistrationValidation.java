package ua.ukrdev.deal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import ua.ukrdev.deal.form.User;
import ua.ukrdev.deal.service.UserService;

@Component("registrationValidator")
public class RegistrationValidation {

    @Autowired
    private UserService userService;

	public boolean supports(Class<?> klass) {
		return User.class.isAssignableFrom(klass);
	}

	public void validate(Object target, Errors errors) {
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
                "NotEmpty.registration.username",
                "User Name must not be Empty.");
		String username = user.getUsername();
		if ((username.length()) > 50) {
			errors.rejectValue("username",
					"lengthOfUser.registration.username",
					"User Name must not more than 50 characters.");
		}
		if (!(user.getPassword()).equals(user
                .getConfirmPassword())) {
            errors.rejectValue("password",
                    "matchingPassword.registration.password",
                    "Password and Confirm Password Not match.");
        }

      if (userService.checkIfUserExistsByEmail(user.getEmail())!=null) {
          errors.rejectValue("email",
                  "emailExists",
                  "User with such email already exist.");
      }
        if (userService.checkIfUserExistsByUsername(user.getUsername())!=null) {
            errors.rejectValue("username",
                    "usernameExists",
                    "User with such username already exist.");
        }



    }
}
