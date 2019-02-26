package telran.ashkelon2018.mishpahug.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter

public class UserUpdateDto {
	String firstName;
	String lastName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate dateOfBirth;
	String gender;
	String maritalStatus;
	String confession;
	List<String> pictureLink;
	String phoneNumber;
	List<String> foodPreferences;
	List<String> languages;
	String description;
}
