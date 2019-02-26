package telran.ashkelon2018.mishpahug.service;

import telran.ashkelon2018.mishpahug.dto.UserRegistrationResponseDto;
import telran.ashkelon2018.mishpahug.dto.UserResponseDto;
import telran.ashkelon2018.mishpahug.dto.UserStaticFieldsDto;
import telran.ashkelon2018.mishpahug.dto.UserUpdateDto;


public interface UserService {
	UserRegistrationResponseDto registrationUser(String token);
	
	UserResponseDto loginUser (String email);

	UserResponseDto updateUserProfile(UserUpdateDto userUpdateDto, String email);

	UserResponseDto getUserProfile(String email);

	UserStaticFieldsDto getStaticFields();
}
