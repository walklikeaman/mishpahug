package telran.ashkelon2018.mishpahug.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import telran.ashkelon2018.mishpahug.service.EventService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class EventController {

	@Autowired
	EventService eventService;

	@PostMapping("/event/creation")
	public CodeResponseDto addEvent(@RequestBody AddEventRequestDto addEventRequestDto, Principal principal) {
		return eventService.addEvent(addEventRequestDto, principal.getName());
	};

	@PostMapping("/event/allprogresslist?page={page}&size={size}")
	public EventListResponseDto listOfEventsInProgress(@PathVariable Integer page, @PathVariable Integer size,
			@RequestBody EventListRequestDto body) {
		return eventService.getListOfEventsInProgress(page, size, body);

	}

	@GetMapping("/event/calendar/{month}")
	public EventListForCalendarResponseDto getEventListForCalendar(@PathVariable int month, Principal principal) {
		return eventService.getEventListForCalendar(month, principal.getName());

	}

	@GetMapping("/event/own/")
	public MyEventResponseDto getMyEventInfo(@RequestBody EventId eventId, Principal principal) {
		return eventService.getMyEventInfo(eventId, principal.getName());

	}

	@GetMapping("/event/subscribed/")
	public SubscribedEventResponseDto getSubscribedEventInfo(@RequestBody EventId eventId, Principal principal) {
		return eventService.getSubscribedEventInfo(eventId, principal.getName());
	}

	@GetMapping("/event/currentlist")
	public List<MyEventResponseDto> getMyEventsList(Principal principal) {
		return eventService.getMyEventsList(principal.getName());

	}

	@GetMapping("/event/historylist")
	public List<MyEventsHistoryResponseDto> getMyEventsHistory(Principal principal) {
		return eventService.getMyEventsHistory(principal.getName());

	}

	@GetMapping("/event/participationlist")
	public List<ParticipationListResponseDto> getParticipationList(Principal principal) {
		return eventService.getParticipationList(principal.getName());
	}

	@PutMapping("/event/subscription/")
	public CodeResponseDto subscibeToEvent(@RequestBody EventId eventId, Principal principal) {
		return eventService.subscribeToEvent(eventId, principal.getName());
	}

	@PutMapping("/event/unsubscription/")
	public CodeResponseDto unsubscribeFromEvent(@RequestBody EventId eventId, Principal principal) {
		return eventService.unsubscribeToEvent(eventId, principal.getName());
	}

	@PutMapping("/event/invitation/{userId}")
	public InviteToEventResponseDto inviteToEvent(Principal principal, @RequestBody EventId eventId,
			@RequestParam String userId) {
		return eventService.inviteToEvent(principal.getName(), eventId, userId);
	}

	@PutMapping("/event/pending/")
	public ChangeEventResponseDto changeEventStatus(Principal principal, @RequestBody EventId eventId) {
		return eventService.changeEventStatus(principal.getName(), eventId);
	}

	@PutMapping("/event/vote/{voteCount}")
	public CodeResponseDto voteForEvent(Principal principal, @RequestBody EventId eventId,
			@PathVariable double voteCount) {
		return eventService.voteForEvent(principal.getName(), eventId, voteCount);
	}
}
