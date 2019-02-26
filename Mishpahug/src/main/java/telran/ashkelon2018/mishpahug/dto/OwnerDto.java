package telran.ashkelon2018.mishpahug.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
@Getter
@AllArgsConstructor
@Builder

public class OwnerDto {
	String fullName;
	String confession;
	String gender;
	int age;
	List<String> pictureLink;
	String maritalStatus;
	List<String> foodPreferences;
	List<String> languages;
	Double rate;
	int numberOfVoters; //есть только в SubscribedEventDto 
}
