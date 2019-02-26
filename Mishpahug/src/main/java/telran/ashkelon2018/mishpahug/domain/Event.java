package telran.ashkelon2018.mishpahug.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.ashkelon2018.mishpahug.dto.AddressDto;
import telran.ashkelon2018.mishpahug.dto.ParticipantDto;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document(collection = "event")
@EqualsAndHashCode(of = { "eventId" })

public class Event {
	@Id
	EventId eventId;		
	String title;
	String holiday;
	AddressDto address;
	String confession;	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	LocalDateTime dateFrom;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	LocalDateTime dateTo;
	int duration;
	List<String> food;
	String description;
	String status;
	String owner; //Id of user that created this event
	Set<ParticipantDto> participants;

	public Event(EventId eventId, String title, String holiday, AddressDto addressDto, String confession, LocalDateTime dateFrom,
			LocalDateTime dateTo, List<String> food, String description, int duration) {
		this.eventId=eventId;
		this.title = title;
		this.holiday = holiday;
		this.address = addressDto;
		this.confession = confession;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.food = food;
		this.description = description;
		this.duration = duration;
		this.owner = eventId.getOwner();
		this.participants = new HashSet<>();
		this.status = "in progress";
	}

}
