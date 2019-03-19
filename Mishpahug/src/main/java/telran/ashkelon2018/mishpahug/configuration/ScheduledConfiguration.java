package telran.ashkelon2018.mishpahug.configuration;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import telran.ashkelon2018.mishpahug.dao.EventRepository;
import telran.ashkelon2018.mishpahug.domain.Event;

@Configuration
@EnableScheduling
public class ScheduledConfiguration {

	@Autowired
	EventRepository eventRepository;

	@Scheduled(cron = "0 0 0/12 * * *")
	public void scheduleTaskChangeStatusToUndone() {
		List<Event> list = eventRepository.findByStatus("in progress");
		for (Event event : list) {
			if (event.getDateFrom().minusHours(48).isBefore(LocalDateTime.now())) {
				System.out.println(event.getEventId().toString());
				event.setStatus("not done");
				eventRepository.save(event);
			}
		}
	}

	@Scheduled(cron = "0 0 0/12 * * *")
	public void scheduleTaskChangeStatusToDone() {
		List<Event> list = eventRepository.findByStatus("pending");
		for (Event event : list) {
			if (event.getDateTo().isBefore(LocalDateTime.now())) {
				event.setStatus("done");
				eventRepository.save(event);
			}
		}
	}
}