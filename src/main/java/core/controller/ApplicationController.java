package core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.dto.CredentialsData;
import core.model.User;
import core.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/")
public class ApplicationController {
	
	@Autowired private UserService userService;
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
    public Boolean login(@RequestBody CredentialsData credentialsData) {
		User user = userService.findByUsername(credentialsData.getUserName());
		return user != null && user.getPassword().equals(credentialsData.getPassword());
    }

}
