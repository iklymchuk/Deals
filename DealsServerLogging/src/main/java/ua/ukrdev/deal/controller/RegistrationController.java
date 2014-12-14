package ua.ukrdev.deal.controller;

import static java.lang.System.out;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.hibernate.validator.internal.util.logging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ua.ukrdev.deal.form.User;
import ua.ukrdev.deal.service.SendEmail;
import ua.ukrdev.deal.service.UserService;
import ua.ukrdev.deal.util.Serv;

@Controller
@RequestMapping("/registrationform.html")
public class RegistrationController {
    private final Integer masterAdministratorStartBalance = 100;
    private final Integer masterDealerStartBalance = 50;
    private final Integer dealerStartBalance = 50;
    private final Integer userStartBalance = 50;
    private final Integer defaultOthersStartBalance = 0;
    
    private final String defaultLockStatus = "no";
    private final String defaultAssignValue = "lampros";
   
    Serv serv = new Serv();

    @Autowired
    private RegistrationValidation registrationValidation;
    
    @Autowired
    private ReCaptcha reCaptchaService = null;

    @Autowired
    private UserService userService;

    public void setRegistrationValidation(
            RegistrationValidation registrationValidation) {
        this.registrationValidation = registrationValidation;
    }

    // Display the form on the get request
    @RequestMapping(method = RequestMethod.GET)
    public String showRegistration(Map model) {
        User user = new User();
        model.put("user", user);
        return "registrationform";
    }

    // Process the form.
    @RequestMapping(method = RequestMethod.POST)
    public String processRegistration(@Valid User user,
                                      BindingResult result, Map<String, Object> map,
                                      //captcha
                                      @RequestParam("recaptcha_challenge_field") String challangeField,
                                      @RequestParam("recaptcha_response_field") String responseField,
                                      //ServletRequest servletRequest,
                                      //upload
                                      @RequestParam(value = "image", required = false) MultipartFile image,
                                      HttpServletRequest request) throws IOException, SQLException, FileUploadException {

        // set custom Validation by user
        registrationValidation.validate(user, result);
        
        String remoteAddr = request.getRemoteAddr();
        //ReCaptchaResponse reCaptchaResponse = this.reCaptcha.checkAnswer(remoteAddress, challangeField, responseField);
        
        ReCaptchaResponse reCaptchaResponse = reCaptchaService.checkAnswer(remoteAddr, challangeField, responseField);
        
        if (result.hasErrors()) {
            return "registrationform";
        } else if (!reCaptchaResponse.isValid()) {
        	map.put("errorMessage", "CAPTCHA Validation Failed! Try Again.");
        	out.println("CAPTCHA Validation Failed! Try Again.");
            return "registrationform";
            
        } else if (!result.hasErrors() && reCaptchaResponse.isValid()) {
        	
        
        	if (!image.isEmpty()) {

        		/*
        		image.getContentType() -->>> image/png
    			image.getName() -->>> image
    			image.getOriginalFilename() -->>> url.png
        		 */
        		
        		String fileType = (image.getContentType()).split(Pattern.quote("/"))[1];
        		out.println("fileType -->>> " + fileType);
        		String newFileName = request.getParameter("username");
        		out.println("newFileName -->>> " + newFileName);
        		 
        		try {
        		saveImage(request, newFileName + "." + fileType, image, user);
        		} catch (IOException e) {
        			result.reject(e.getMessage());
        			out.println("IOException");
        		return "registrationform";
        		}
        	}

     
            //another action after upload
			setStartBalance(user);
			user.setAssign(defaultAssignValue);
			user.setIslock(defaultLockStatus);
			out.println(userService.addUser(user));
			out.println("User " + user.getEmail() + " added");
			sendNotificationEmail(user);
			
			map.put("user", new User());
			//map.put("listUsers", userService.listUsers("User"));
			map.put("assignUsers", userService.getAssignUsers(user.getUsername()));
			map.put("currentUser", userService.getCurrentUser(user.getUsername()));
			
			return "profileUser";
        }
        return "profileUser";
    }
   
    private void assignPhotIfUploaded(User user) throws IOException, SQLException {

        String photoName = System.getProperty("java.io.tmpdir") + "/"+serv.getUploadedPhotoName();
        if (serv.getUploadedPhotoName()!=null)  {
//            byte[] photoBytes = readBytesFromFile(photoName);
//            user.setPhoto(photoBytes);
            out.println(serv.getUploadedPhotoName());
            user.setPhoto(serv.getUploadedPhotoName());
        }
    }

    private byte[] readBytesFromFile(String filePath) throws IOException {
        File inputFile = new File(filePath);
        FileInputStream inputStream = new FileInputStream(inputFile);
        byte[] fileBytes = new byte[(int) inputFile.length()];
        inputStream.read(fileBytes);
        inputStream.close();

        return fileBytes;
    }

    private static void saveBytesToFile(String filePath, byte[] fileBytes) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(filePath);
        outputStream.write(fileBytes);
        outputStream.close();
    }

    private void setStartBalance(User user){
        if ("User".equals(user.getRole())) user.setBalance(userStartBalance); else
        if ("MasterDealer".equals(user.getRole())) user.setBalance(masterDealerStartBalance); else
        if ("Dealer".equals(user.getRole())) user.setBalance(dealerStartBalance); else user.setBalance(defaultOthersStartBalance);
    }

    private void sendNotificationEmail (User user) {
        SendEmail se = new SendEmail();
        //se.send("lamrpos.karageorgos@gmail.com", "New user notification", "New user "+user.getFname()+" "+user.getLname()+" "+user.getUsername()+" "+user.getEmail()+" registerd."+"\n Role is "+user.getRole());
        se.send("klymhuk.ivan@gmail.com", "New user notification", "New user: "+user.getFname()+" "+user.getLname()+" "+user.getUsername()+" "+user.getEmail()+" registerd."+"\n Role is "+user.getRole());
    }
    
    private String formatNewNameBasedOnTime(String currentName) {
        Date dt = new Date();
        out.println(dt.getTime());
        if (currentName.split(Pattern.quote(".")).length>0)
        return (dt.getTime()+"."+currentName.split(Pattern.quote("."))[1]);
        else return (dt.getTime()+"_");
    }
    
    ///////////////update image
    private void validateImage(MultipartFile image) {
    	if (!image.getContentType().equals("image/jpeg") | !image.getContentType().equals("image/png")) {
    		out.println("image.getContentType() -->>> " + image.getContentType());
    		out.println("image.getName() -->>> " + image.getName());
    		out.println("image.getOriginalFilename() -->>> " + image.getOriginalFilename());
    		
    		/*
    		image.getContentType() -->>> image/png
			image.getName() -->>> image
			image.getOriginalFilename() -->>> url.png
    		 */
    		
    		out.println("Only JPG images are accepted");
    	throw new RuntimeException("Only JPG images are accepted");
    	}
    }
    	 
    	private void saveImage(HttpServletRequest request, String filename, MultipartFile image, User user) throws RuntimeException, IOException {
    	try {
    		File file = new File(request.getRealPath("/") + "/images/" + filename);
    				
    		out.println("getPathInfo() -->>>" + request.getPathInfo());
    		out.println("getContextPath() -->>>" + request.getContextPath());
    		out.println("getRealPath(\"/\") -->>>" + request.getRealPath("/"));

    				FileUtils.writeByteArrayToFile(file, image.getBytes());
    				
    				user.setPhoto("images\\" + filename);
    				
    				System.out.println("Go to the location:  " + file.toString()
    				+ " on your computer and verify that the image has been stored.");
    	} catch (IOException e) {
    		out.println("catch IOException in the saveImage");
    	}
    }
}
