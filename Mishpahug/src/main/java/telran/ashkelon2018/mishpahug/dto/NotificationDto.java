package telran.ashkelon2018.mishpahug.dto;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.ashkelon2018.mishpahug.domain.EventId;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDto {
	@Id
	int notificationId;
	String title;
	String message;
	LocalDate date;
	String type;
	boolean isRead;
	EventId eventId;
}
