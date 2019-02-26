package telran.ashkelon2018.mishpahug.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import telran.ashkelon2018.mishpahug.domain.EventId;


@AllArgsConstructor
@Builder
@Getter
public class EventForCalendarDto {
	EventId eventId;
	String title;
	LocalDate date;
	LocalTime time;
	int duration;
	String status;
}
