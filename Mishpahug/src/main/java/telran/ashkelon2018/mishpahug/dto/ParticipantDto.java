package telran.ashkelon2018.mishpahug.dto;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"email"})
public class ParticipantDto {
	@Id
	String email;
	String fullName;
	String confession;
	String gender;
	int age;
	List<String> pictureLink;
	String maritalStatus;
	List<String> foodPreferences;
	List<String> languages;
	Double rate;
	String phoneNumber;
	int numberOfVoters;
	boolean isInvited;
	boolean voted;
	
	public ParticipantDto(String email) {
		this.email = email;
	}
	
	
	
	
}

