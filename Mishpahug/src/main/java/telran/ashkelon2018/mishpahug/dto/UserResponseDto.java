package telran.ashkelon2018.mishpahug.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString

public class UserResponseDto {
	String firstName;
	String lastName;
	LocalDate dateOfBirth;
	String gender;
	String maritalStatus;
	String confession;
	List<String> pictureLink;
	String phoneNumber;
	List<String> foodPreferences;
	List<String> languages;
	String description;
	double rate;
	int numberOfVoters;
}
