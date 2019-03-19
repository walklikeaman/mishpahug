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
	List<String> holiday;
	String description;
	List<String> confession;
	List<String> gender;
	List<String> maritalStatus;

	public UserRegistrationResponseDto() {
		this.confession = Arrays.asList("Irreligious", "Religious");
		this.gender = Arrays.asList("Female", "Male");
		this.maritalStatus = Arrays.asList( "Single", "Married", "Divorced", "In a civil union", "In a relationship", "Widowed");
		this.foodPreferences = Arrays.asList("Kosher", "Vegetarian", "Any");
		this.languages = Arrays.asList( "Hebrew", "English", "French", "Russian", "Spain");
		this.holiday = Arrays.asList("Shabat", "Purim", "Pesach", "Rosh Hashana", "Sukkot");
	}
}
