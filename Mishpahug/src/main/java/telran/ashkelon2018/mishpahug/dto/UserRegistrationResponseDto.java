package telran.ashkelon2018.mishpahug.dto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class UserRegistrationResponseDto {

	String firstName;
	String lastName;
	LocalDate dateOfBirth;
	List<String> pictureLink;
	String phoneNumber;
	List<String> foodPreferences;
	List<String> languages;
	String description;
	List<String> confession;
	List<String> gender;
	List<String> maritalStatus;

	public UserRegistrationResponseDto() {
		this.confession = Arrays.asList("religious", "irreligious");
		this.gender = Arrays.asList("male", "female");
		this.maritalStatus = Arrays.asList("married", "single", "couple");
		this.foodPreferences = Arrays.asList("vegetarian", "kosher", "non-vegetarian");
		this.languages = Arrays.asList("Hebrew", "English", "Russian");
	}
}
