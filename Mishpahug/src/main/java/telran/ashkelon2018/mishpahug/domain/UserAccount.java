package telran.ashkelon2018.mishpahug.domain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = { "email" })
@Document(collection = "user")
@NoArgsConstructor

public class UserAccount {
	@Id
	String email;
	String password;
	String firstName;
	String lastName;
	String phoneNumber;
	LocalDate dateOfBirth;
	String maritalStatus;
	String gender;
	String confession;
	List<String> languages;
	List<String> foodPreferences;
	String description;
	List<String> pictureLink;
	List<EventId> subscribedEvents;

	double rate;
	int numberOfVoters;
	boolean isRegistered;

	public UserAccount(String firstName, String lastName, String phoneNumber, LocalDate dateOfBirth,
			String maritalStatus, String gender, String confession, List<String> languages,
			List<String> foodPreferences, String description, double rate, int numberOfVoters, boolean isRegistred) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.maritalStatus = maritalStatus;
		this.gender = gender;
		this.confession = confession;
		this.languages = languages;
		this.foodPreferences = foodPreferences;
		this.description = description;
		this.rate = 0;
		this.numberOfVoters = 0;
		

	}

	public UserAccount(String email, String password) {
		this.email = email;
		this.password = password;
	}

	// @Singular
	// Set<String> roles;
	//
	// public void addRole(String role) {
	// roles.add(role);
	// }
	//
	// public void removeRole(String role) {
	// roles.remove(role);
	// }
	//
}
