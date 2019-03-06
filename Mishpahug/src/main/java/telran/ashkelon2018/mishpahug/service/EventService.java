package telran.ashkelon2018.mishpahug.service;

import java.util.List;

import telran.ashkelon2018.mishpahug.domain.EventId;
import telran.ashkelon2018.mishpahug.dto.AddEventRequestDto;
import telran.ashkelon2018.mishpahug.dto.ChangeEventResponseDto;
import telran.ashkelon2018.mishpahug.dto.CodeResponseDto;
import telran.ashkelon2018.mishpahug.dto.EventListForCalendarResponseDto;
import telran.ashkelon2018.mishpahug.dto.EventListRequestDto;
import telran.ashkelon2018.mishpahug.dto.EventListResponseDto;
import telran.ashkelon2018.mishpahug.dto.InviteToEventResponseDto;
import telran.ashkelon2018.mishpahug.dto.MyEventResponseDto;
import telran.ashkelon2018.mishpahug.dto.MyEventsHistoryResponseDto;
import telran.ashkelon2018.mishpahug.dto.ParticipationListResponseDto;
import telran.ashkelon2018.mishpahug.dto.SubscribedEventResponseDto;

public interface EventService {
	CodeResponseDto addEvent (AddEventRequestDto addEventRequestDto, String email);
	EventListForCalendarResponseDto getEventListForCalendar(int month, String email);
	MyEventResponseDto getMyEventInfo(EventId eventId, String email);
	SubscribedEventResponseDto getSubscribedEventInfo(EventId eventId, String email);
	List<MyEventResponseDto> getMyEventsList(String email); 
	List<MyEventsHistoryResponseDto> getMyEventsHistory(String email);
	List<ParticipationListResponseDto> getParticipationList(String email);
	CodeResponseDto subscribeToEvent(EventId eventId, String email);
	CodeResponseDto unsubscribeToEvent(EventId eventId, String email);
	CodeResponseDto voteForEvent(String email, EventId eventId, double voteCount);
	InviteToEventResponseDto inviteToEvent(String email, EventId eventId, String userId);
	EventListResponseDto getListOfEventsInProgress(Integer page, Integer size, EventListRequestDto body, String email);
	ChangeEventResponseDto changeEventStatus(String email, EventId eventId);
	
	
	
	
	
	
}
