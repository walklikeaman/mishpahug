package telran.ashkelon2018.mishpahug.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventListForCalendarResponseDto {
	List<EventForCalendarDto> myEvents;
	List<EventForCalendarDto> subscribedEvents;

	public EventListForCalendarResponseDto() {
		this.myEvents = new ArrayList<>();
		this.subscribedEvents = new ArrayList<>();
	}

}
