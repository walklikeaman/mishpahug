package telran.ashkelon2018.mishpahug.domain;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@EqualsAndHashCode(of = {"notificationId"})
public class Notification {
	@Id
	int notificationId;
	String title;
	String message;
	LocalDate date;
	String type;
	boolean isRead;
	EventId eventId;
}
