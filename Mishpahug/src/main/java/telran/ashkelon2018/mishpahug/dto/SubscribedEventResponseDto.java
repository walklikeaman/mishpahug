package telran.ashkelon2018.mishpahug.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import telran.ashkelon2018.mishpahug.domain.Address;
import telran.ashkelon2018.mishpahug.domain.EventId;
@Builder
@AllArgsConstructor	
@Getter
public class SubscribedEventResponseDto {
	@Id
	EventId eventId;
	String title;
	String holiday;
	String confession;
	LocalDate date;
	LocalTime time;
	int duration;
	Address address; //в статусе in progress только city, в статусе pending full address
	List<String> food;
	String description;
	String status;
	OwnerDto owner; //owner c number of voters

	
}

