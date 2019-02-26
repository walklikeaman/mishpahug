package telran.ashkelon2018.mishpahug.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.ashkelon2018.mishpahug.domain.EventId;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder

public class EventDto {

	EventId eventId;
	String title;
	String holiday;
	String confession;
	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate date;
	@JsonFormat(pattern = "HH:mm:ss")
	LocalTime time;
	int duration;
	AddressDto address; // city
	List<String> food;
	String description;
	OwnerDto owner;
	String maritalStatus;
	List<String> foodPreferences;
	List<String> languages;
	Double rate;
}
