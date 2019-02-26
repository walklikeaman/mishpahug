package telran.ashkelon2018.mishpahug.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.ashkelon2018.mishpahug.domain.Event;
import telran.ashkelon2018.mishpahug.domain.EventId;

public interface EventRepository extends MongoRepository<Event, EventId> {
	List<Event> findByDateFromBetweenAndStatusIn(LocalDate fromDate, LocalDate toDate, String...statuses);
	List<Event> findByStatus(String status);
	List<Event> findByOwnerAndStatusIn(String email, String...statuses);
	
	//List<Event> findbyAll(Predicate<Event> predicate);
}
