package telran.ashkelon2018.mishpahug.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import telran.ashkelon2018.mishpahug.domain.Event;
import telran.ashkelon2018.mishpahug.domain.EventId;

public interface EventRepository extends MongoRepository<Event, EventId> {
	
	List<Event> findByDateFromBetweenAndStatusIn(LocalDate fromDate, LocalDate toDate, String...statuses);
	List<Event> findByStatus(String status);
	List<Event> findByOwnerAndStatusIn(String email, String...statuses);
	List<Event> findByOwnerAndDateFromIn(String email, LocalDate date);
	List<Event> findByDateFromBetweenAndOwnerIn(LocalDate fromDate, LocalDate toDate, String email);
	Page<Event> findByLocationNearAndStatusEquals(Point point, Distance distance, String status, Pageable pagable);
	Page<Event> findByStatus(String status, Pageable pagable);

}
