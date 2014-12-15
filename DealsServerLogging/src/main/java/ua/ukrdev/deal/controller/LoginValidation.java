package ua.ukrdev.deal.controller;

/**
 * Created by Eugene on 22.11.2014.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.ukrdev.deal.form.User;
import ua.ukrdev.deal.service.UserService;

public class LoginValidation implements Validator {

    @Autowired
    private UserService userService;


    public boolean supports(Class clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    public void validate(Object obj, Errors errors) {
        User user = (User) obj;
        if (user.getUsername() == null || user.getUsername().length() == 0) {
            errors.rejectValue("username", "error.empty.field", "Please Enter User Name");
        }

        if (user.getPassword() == null || user.getPassword().length() == 0) {
            errors.rejectValue("password", "error.empty.field", "Please Enter Password");
        }

        if (!userService.checkIfUserWithSuchPasswordExists(user.getUsername(), user.getPassword())) {
            errors.rejectValue("password", "wrong username", "Incorrect username or password");
        }

    }
}