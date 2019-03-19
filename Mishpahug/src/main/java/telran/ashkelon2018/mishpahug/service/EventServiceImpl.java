package telran.ashkelon2018.mishpahug.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import telran.ashkelon2018.mishpahug.configuration.AccountConfiguration;
import telran.ashkelon2018.mishpahug.dao.EventRepository;
import telran.ashkelon2018.mishpahug.dao.NotificationRepository;
import telran.ashkelon2018.mishpahug.dao.UserRepository;
import telran.ashkelon2018.mishpahug.domain.Address;
import telran.ashkelon2018.mishpahug.domain.Event;
import telran.ashkelon2018.mishpahug.domain.EventId;
import telran.ashkelon2018.mishpahug.domain.Notification;
import telran.ashkelon2018.mishpahug.domain.UserAccount;
import telran.ashkelon2018.mishpahug.domain.UserNotifications;
import telran.ashkelon2018.mishpahug.dto.AddEventRequestDto;
import telran.ashkelon2018.mishpahug.dto.AddressDto;
import telran.ashkelon2018.mishpahug.dto.ChangeEventResponseDto;
import telran.ashkelon2018.mishpahug.dto.CodeResponseDto;
import telran.ashkelon2018.mishpahug.dto.EventDto;
import telran.ashkelon2018.mishpahug.dto.EventForCalendarDto;
import telran.ashkelon2018.mishpahug.dto.EventListForCalendarResponseDto;
import telran.ashkelon2018.mishpahug.dto.EventListRequestDto;
import telran.ashkelon2018.mishpahug.dto.EventListResponseDto;
import telran.ashkelon2018.mishpahug.dto.Filters;
import telran.ashkelon2018.mishpahug.dto.InviteToEventResponseDto;
import telran.ashkelon2018.mishpahug.dto.MyEventResponseDto;
import telran.ashkelon2018.mishpahug.dto.MyEventsHistoryResponseDto;
import telran.ashkelon2018.mishpahug.dto.OwnerDto;
import telran.ashkelon2018.mishpahug.dto.ParticipantDto;
import telran.ashkelon2018.mishpahug.dto.ParticipationListResponseDto;
import telran.ashkelon2018.mishpahug.dto.SubscribedEventResponseDto;
import telran.ashkelon2018.mishpahug.exceptions.BusyDateException;
import telran.ashkelon2018.mishpahug.exceptions.InvalidDataException;
import telran.ashkelon2018.mishpahug.exceptions.InvalidFilterParameters;
import telran.ashkelon2018.mishpahug.exceptions.InviteException;
import telran.ashkelon2018.mishpahug.exceptions.NotAssociatedEventException;
import telran.ashkelon2018.mishpahug.exceptions.RevoteException;
import telran.ashkelon2018.mishpahug.exceptions.UserIsNotAssociatedException;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	AccountConfiguration accountConfiguration;

	@Autowired
	UserRepository userRepository;

	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	NotificationRepository notificationRepository;

	@Override
	public CodeResponseDto addEvent(AddEventRequestDto addEventRequestDto, String email) {
		LocalDateTime dateFrom = addEventRequestDto.getDate().atTime(addEventRequestDto.getTime());
		LocalDateTime dateTo = dateFrom.plusHours(addEventRequestDto.getDuration());
		EventId eventId = new EventId(email, dateFrom);
		if (eventRepository.findById(eventId).orElse(null) != null) {
			throw new BusyDateException();
		}
		LocalDateTime checkDateFrom = addEventRequestDto.getDate().atTime(LocalTime.of(0, 0, 0));
		LocalDateTime checkDateTo = checkDateFrom.plusDays(1);
		boolean checktime1 = LocalDateTime.now().isBefore(dateFrom.minusHours(48));
		boolean checktime2 = LocalDateTime.now().isAfter(dateFrom.minusMonths(2));
		boolean checktime3 = false;

		List<Event> list = eventRepository.findByDateFromBetweenAndOwnerIn(checkDateFrom.toLocalDate(),
				checkDateTo.toLocalDate(), email);
		if (list.isEmpty()) {
			checktime3 = true;
		}
		if (!list.isEmpty()) {
			checktime3 = true;
			for (Event event : list) {
				if (!event.getDateTo().isBefore(dateFrom)
						&& !dateFrom.plusHours(addEventRequestDto.getDuration()).isBefore(event.getDateFrom())) {
					checktime3 = false;
				}
			}

		}
		if (!(checktime1 && checktime2 && checktime3)) {
			throw new InvalidDataException();
		}

		Event event = new Event(eventId, addEventRequestDto.getTitle(), addEventRequestDto.getHoliday(),
				addEventRequestDto.getAddress(), addEventRequestDto.getConfession(), dateFrom, dateTo,
				addEventRequestDto.getFood(), addEventRequestDto.getDescription(), addEventRequestDto.getDuration());
		eventRepository.save(event);
		return new CodeResponseDto(200, "Event Is Created");
	}

	@Override
	public EventListForCalendarResponseDto getEventListForCalendar(int month, String email) {

		int year = Year.now().getValue();
		LocalDate from = LocalDate.of(year, month, 1);
		LocalDate to = from.with(TemporalAdjusters.firstDayOfNextMonth());
		List<Event> events = eventRepository.findByDateFromBetweenAndStatusIn(from, to, "pending", "in progress");
		List<EventForCalendarDto> myEvents = new ArrayList<>();
		List<EventForCalendarDto> subscribedEvents = new ArrayList<>();
		for (Event event : events) {
			if (event.getOwner().equals(email)) {
				myEvents.add(eventToEventForCalendarDtoConverter(event));
			}
			if (isSubscribed(event.getParticipants(), email))
				;
			subscribedEvents.add(eventToEventForCalendarDtoConverter(event));
		}

		return new EventListForCalendarResponseDto(myEvents, subscribedEvents);
	}

	@Override
	public MyEventResponseDto getMyEventInfo(EventId eventId, String email) {
		Event event = eventRepository.findById(eventId).get();
		if (!event.getOwner().equals(email)) {
			throw new UserIsNotAssociatedException(409, "User is not associated with the event!");
		}

		Set<ParticipantDto> participants = new HashSet<>();
		if (event.getStatus().equals("in progress")) {
			participants = event.getParticipants();
		} else {
			for (ParticipantDto participantDto : event.getParticipants()) {
				if (participantDto.isInvited()) {
					participants.add(participantDto);

				}
			}
		}
		return MyEventResponseDto.builder().eventId(eventId).title(event.getTitle()).holiday(event.getHoliday())
				.confession(event.getConfession()).date(event.getDateFrom().toLocalDate())
				.time(event.getDateFrom().toLocalTime()).duration(event.getDuration()).food(event.getFood())
				.description(event.getDescription()).status(event.getStatus()).participants(participants).build();
	}

	@Override
	public SubscribedEventResponseDto getSubscribedEventInfo(EventId eventId, String email) {
		Event event = eventRepository.findById(eventId).orElse(null);

		if (!isSubscribed(event.getParticipants(), email)) {
			throw new UserIsNotAssociatedException(209, "User is not associated with the event!");
		}
		Address address;
		if (event.getStatus().equals("in progress")) {
			address = new Address(event.getAddress().getCity());
		} else {
			address = event.getAddress();
		}
		OwnerDto owner = userAccountToOwnerDtoConverter(userRepository.findById(eventId.getOwner()).get());
		return SubscribedEventResponseDto.builder().eventId(eventId).title(event.getTitle()).holiday(event.getHoliday())
				.confession(event.getConfession()).date(event.getDateFrom().toLocalDate())
				.time(event.getDateFrom().toLocalTime()).duration(event.getDuration()).address(address)
				.food(event.getFood()).description(event.getDescription()).status(event.getStatus()).owner(owner)
				.build();
	}

	@Override
	public List<MyEventResponseDto> getMyEventsList(String email) {
		List<Event> list = eventRepository.findByOwnerAndStatusIn(email, "in progress", "pending");
		return list.stream().map(e -> eventToMyEventResponseDtoConverter(e)).collect(Collectors.toList());
	}

	@Override
	public List<MyEventsHistoryResponseDto> getMyEventsHistory(String email) {

		return eventRepository.findByOwnerAndStatusIn(email, "done").stream()
				.map(e -> eventToMyEventsHistoryDtoConverter(e.getEventId()))
				.sorted((x, y) -> y.getDate().compareTo(x.getDate())).collect(Collectors.toList());
	}

	@Override
	public List<ParticipationListResponseDto> getParticipationList(String email) {
		UserAccount user = userRepository.findById(email).get();
		List<EventId> listEventId = user.getSubscribedEvents();
		List<Event> list = new ArrayList<>();
		for (EventId eventId : listEventId) {
			list.add(eventRepository.findById(eventId).get());
		}
		List<ParticipationListResponseDto> listResponse = new ArrayList<>();
		for (Event event : list) {
			OwnerDto owner = userAccountToOwnerDtoConverter(userRepository.findById(email).get());
			if (event.getStatus().equals("in progress")) {
				listResponse.add(new ParticipationListResponseDto(event.getEventId(), event.getTitle(),
						event.getHoliday(), event.getConfession(), event.getDateFrom().toLocalDate(),
						event.getDateFrom().toLocalTime(), event.getDuration(),
						new Address(event.getAddress().getCity()), event.getFood(), event.getDescription(),
						event.getStatus(), owner));
			}
			if (event.getStatus().equals("pending")) {
				listResponse.add(new ParticipationListResponseDto(event.getEventId(), event.getTitle(),
						event.getHoliday(), event.getConfession(), event.getDateFrom().toLocalDate(),
						event.getDateFrom().toLocalTime(), event.getDuration(), event.getAddress(), event.getFood(),
						event.getDescription(), event.getStatus(), owner));
			}
			if (event.getStatus().equals("done")) {
				listResponse.add(new ParticipationListResponseDto(event.getEventId(), event.getTitle(),
						event.getHoliday(), event.getConfession(), event.getDateFrom().toLocalDate(),
						event.getDescription(), event.getStatus(), owner));
			}
		}
		listResponse.sort((x, y) -> y.getDate().compareTo(x.getDate()));

		return listResponse;
	}

	// private Predicate<Event> predicateParticipant(String email) {
	// UserAccount userAccount = userRepository.findById(email).get();
	// return e -> userAccount.getSubscribedEvents().contains(e.getEventId());
	//
	// }

	@Override
	public CodeResponseDto subscribeToEvent(EventId eventId, String email) {

		Event event = eventRepository.findById(eventId).get();
		ParticipantDto participant = new ParticipantDto(email);
		if (event.getOwner().equals(email) || !event.getParticipants().add(participant)) {
			return new CodeResponseDto(409, "User is the owner of the event or already subscribed to it!");
		}
		UserAccount user = userRepository.findById(participant.getEmail()).get();
		participant.setFullName(user.getFirstName() + " " + user.getLastName());
		participant.setConfession(user.getConfession());
		participant.setGender(user.getGender());
		participant.setAge(Period.between(user.getDateOfBirth(), LocalDate.now()).getYears());
		participant.setPictureLink(user.getPictureLink());
		participant.setPhoneNumber(user.getPhoneNumber());
		participant.setMaritalStatus(user.getMaritalStatus());
		participant.setFoodPreferences(user.getFoodPreferences());
		participant.setLanguages(user.getLanguages());
		participant.setRate(user.getRate());
		participant.setNumberOfVoters(user.getNumberOfVoters());
		participant.setVoted(false);
		participant.setInvited(false);
		user.getSubscribedEvents().add(eventId);
		userRepository.save(user);
		eventRepository.save(event);
		return new CodeResponseDto(200, "User subscribed to the event!");
	}

	@Override
	public CodeResponseDto unsubscribeToEvent(EventId eventId, String email) {
		Event event = eventRepository.findById(eventId).get();

		if (!event.getStatus().equals("in progress")) {
			return new CodeResponseDto(409, "User can't unsubscribe from the event!");
		}
		event.getParticipants().removeIf(p -> p.getEmail().equals(email));
		UserAccount userAccount = userRepository.findById(email).get();
		userAccount.getSubscribedEvents().removeIf(e -> e.equals(eventId));
		userRepository.save(userAccount);
		eventRepository.save(event);
		return new CodeResponseDto(200, "User unsubscribed from the event!");
	}

	@Override
	public CodeResponseDto voteForEvent(String email, EventId eventId, double voteCount) {
		Event event = eventRepository.findById(eventId).get();
		ParticipantDto participant = event.getParticipants().stream().filter(p -> p.getEmail().equals(email))
				.findFirst().orElse(null);
		if (event.getStatus() != "done" || participant == null || participant.isVoted()) {
			throw new RevoteException();
		}
		participant.setVoted(true);
		UserAccount user = userRepository.findById(email).get();
		user.getSubscribedEvents().removeIf(e -> e.equals(eventId));
		UserAccount owner = userRepository.findById(event.getOwner()).get();
		double ownerRate = owner.getRate();
		int numberOfVoters = owner.getNumberOfVoters() + 1;
		double newRate = (ownerRate * numberOfVoters + voteCount) / (numberOfVoters);
		owner.setRate(newRate);
		owner.setNumberOfVoters(numberOfVoters);
		userRepository.save(owner);
		return new CodeResponseDto(200, "User vote is accepted!");
	}

	@Override
	public InviteToEventResponseDto inviteToEvent(String email, EventId eventId, String userId) {

		Event event = eventRepository.findById(eventId).get();
		if (!email.equals(event.getOwner())) {
			throw new NotAssociatedEventException();
		}
		ParticipantDto user = event.getParticipants().stream().filter(p -> p.getEmail().equals(userId)).findFirst()
			.orElse(null);
		if (user == null || user.isInvited()) {
			throw new InviteException();
		}
		user.setInvited(true);
		eventRepository.save(event);
		return new InviteToEventResponseDto(userId);
	}

	@Override
	public EventListResponseDto getListOfEventsInProgress(Integer page, Integer size, EventListRequestDto body,
			String email) {
		Double lat = body.getLocation().getLat();
		Double lng = body.getLocation().getLng();
		Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.DESC, "dateFrom"));
		Page<Event> listOfEvents;	
		if (lat == null && lng == null) {
			listOfEvents = eventRepository.findByStatus("in progress", pageable);
		}else {
			Point point = new Point(lat, lng);
			Distance distance = new Distance(body.getLocation().getRadius());
			listOfEvents = eventRepository.findByLocationNearAndStatusEquals(point, distance, "in progress",
					pageable);
		}
		Filters filters = body.getFilters();
		List<EventDto> content = new ArrayList<>();
		listOfEvents.forEach(e -> content.add(eventToEventDtoConverter(e)));
		LocalDate dateFrom = filters.getDateFrom();
		if (dateFrom != null) {
			if (dateFrom.isBefore(LocalDate.now())) {
				throw new InvalidFilterParameters();
			}
		}

		Stream<EventDto> stream = content.stream();
		if (email != null) {
			stream = stream.filter(e -> !e.getEventId().getOwner().equals(email));
		}
		if (filters.getDateFrom() != null) {
			stream = stream.filter(e -> e.getDate().isAfter(filters.getDateFrom()));
		}
		if (filters.getDateTo() != null) {
			stream = stream.filter(e -> e.getDate().isBefore(filters.getDateTo()));
		}
		if (filters.getHolidays() != null) {

			stream = stream.filter(e -> e.getHoliday().equalsIgnoreCase(filters.getHolidays()));
		}
		if (filters.getConfession() != null) {
			stream = stream.filter(e -> e.getConfession().equalsIgnoreCase(filters.getConfession()));
		}
		if (filters.getFood() != null) {
			stream = stream.filter(e -> e.getFood().contains(filters.getFood()));
		}

		return new EventListResponseDto(stream.collect(Collectors.toList()), page, size);

	}

	@Override
	public ChangeEventResponseDto changeEventStatus(String email, EventId eventId) {
		Event event = eventRepository.findById(eventId).get();
		if (!email.equals(event.getOwner())) {
			throw new NotAssociatedEventException();
		}
		event.setStatus("pending");
		eventRepository.save(event);	
		event.getParticipants().stream().filter(p -> p.isInvited()).forEach(p -> putNotification(p.getEmail(), eventId));;
		return new ChangeEventResponseDto(eventId, event.getStatus());
	}

	private void putNotification(String email, EventId eventId) {
		UserNotifications userNotifications = notificationRepository.findById(email).get();
		userNotifications.getNotifications().add(new Notification("Invitation", "Event in status pending", LocalDate.now(), "system", false, eventId));
		notificationRepository.save(userNotifications);
	}

	private EventDto eventToEventDtoConverter(Event event) {
		UserAccount user = userRepository.findById(event.getEventId().getOwner()).get();
		return EventDto.builder().eventId(event.getEventId()).title(event.getTitle()).holiday(event.getHoliday())
				.confession(event.getConfession()).date(event.getDateFrom().toLocalDate())
				.time(event.getDateFrom().toLocalTime()).duration(event.getDuration())
				.address(new AddressDto(event.getAddress().getCity(), event.getAddress().getPlace_id(),
						event.getLocation()))
				.food(event.getFood()).description(event.getDescription()).owner(userAccountToOwnerDtoConverter(user))
				.build();
	}

	private MyEventResponseDto eventToMyEventResponseDtoConverter(Event event) {

		return MyEventResponseDto.builder().eventId(event.getEventId()).title(event.getTitle())
				.holiday(event.getHoliday()).confession(event.getConfession()).date(event.getDateFrom().toLocalDate())
				.time(event.getDateFrom().toLocalTime()).duration(event.getDuration()).food(event.getFood())
				.description(event.getDescription()).status(event.getStatus()).participants(event.getParticipants())
				.build();
	}

	private MyEventsHistoryResponseDto eventToMyEventsHistoryDtoConverter(EventId eventId) {
		Event event = eventRepository.findById(eventId).get();
		return MyEventsHistoryResponseDto.builder().eventId(eventId).title(event.getTitle()).holiday(event.getHoliday())
				.confession(event.getConfession()).date(event.getDateFrom().toLocalDate()).food(event.getFood())
				.description(event.getDescription()).status(event.getStatus()).build();
	}

	private EventForCalendarDto eventToEventForCalendarDtoConverter(Event event) {
		return EventForCalendarDto.builder().eventId(event.getEventId()).title(event.getTitle())
				.date(event.getDateFrom().toLocalDate()).time(event.getDateFrom().toLocalTime())
				.duration(event.getDuration()).status(event.getStatus()).build();
	}

	private boolean isSubscribed(Set<ParticipantDto> set, String email) {
		for (ParticipantDto participant : set) {
			if (participant.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}

	private OwnerDto userAccountToOwnerDtoConverter(UserAccount user) {
		return OwnerDto.builder().fullName(user.getFirstName() + " " + user.getLastName())
				.confession(user.getConfession()).gender(user.getGender())
				.age(LocalDateTime.now().getYear() - user.getDateOfBirth().getYear()).pictureLink(user.getPictureLink())
				.maritalStatus(user.getMaritalStatus()).foodPreferences(user.getFoodPreferences())
				.languages(user.getLanguages()).rate(user.getRate()).numberOfVoters(user.getNumberOfVoters()).build();
	}

}
