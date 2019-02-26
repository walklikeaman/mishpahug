package telran.ashkelon2018.mishpahug.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import telran.ashkelon2018.mishpahug.domain.EventId;
@Getter
@AllArgsConstructor
@Builder
public class MyEventsHistoryResponseDto {
	EventId eventId;
	String title;
	String holiday;
	String confession;
	LocalDate date;
	List<String> food;
	String description;
	String status;
	

}
