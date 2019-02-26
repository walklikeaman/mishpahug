package telran.ashkelon2018.mishpahug.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import telran.ashkelon2018.mishpahug.dto.UserRegistrationResponseDto;
import telran.ashkelon2018.mishpahug.dto.UserResponseDto;
import telran.ashkelon2018.mishpahug.dto.UserStaticFieldsDto;
import telran.ashkelon2018.mishpahug.dto.UserUpdateDto;
import telran.ashkelon2018.mishpahug.service.UserService;
@CrossOrigin(origins="*", allowedHeaders= {"POST","OPTIONS","GET","PUT"})
@RestController
public class UserController {
	
	
	//sessionId
	//http servlet request.session id
	@Autowired
	UserService service;
	
	@PostMapping("/user/registration")
	public UserRegistrationResponseDto registrationUser(@RequestHeader("Authorization") String token) {
		return service.registrationUser(token);
	}
	@PostMapping("/user/login")
	public UserResponseDto loginUser (Principal principal) {
		return service.loginUser(principal.getName());
	}
	@PostMapping("/user/profile")	
	public UserResponseDto updateUserProfile(@RequestBody UserUpdateDto userUpdateDto, Principal principal) {
		return service.updateUserProfile(userUpdateDto, principal.getName());	
		
	}
	@GetMapping("/user/profile")
	public UserResponseDto getUserProfile(Principal principal) {
		return service.getUserProfile(principal.getName());
	}
	@GetMapping("user/staticfields")
	public UserStaticFieldsDto getStaticFields() {
		return service.getStaticFields();
	}


}
