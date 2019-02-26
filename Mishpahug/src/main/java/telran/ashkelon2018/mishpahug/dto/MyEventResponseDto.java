package telran.ashkelon2018.mishpahug.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.ashkelon2018.mishpahug.domain.EventId;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class MyEventResponseDto {

	EventId eventId;
	String title;
	String holiday;
	String confession;
	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate date;
	@JsonFormat(pattern = "HH:mm:ss")
	LocalTime time;
	int duration;
	List<String> food;
	String description;
	String status;
	Set<ParticipantDto> participants;

}
