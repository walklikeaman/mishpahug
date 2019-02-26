package telran.ashkelon2018.mishpahug.service;

import java.util.List;

import telran.ashkelon2018.mishpahug.domain.Notification;
import telran.ashkelon2018.mishpahug.dto.CodeResponseDto;
import telran.ashkelon2018.mishpahug.dto.NotificationsCountResponseDto;

public interface NotificationService {
	List<Notification> getNotificationList(String email);

	CodeResponseDto notificationIsRead(String email, int notificationId);

	NotificationsCountResponseDto countUnreadNotifications(String email);

	CodeResponseDto addFirebaseToken(String email, String firebaseToken);

	CodeResponseDto deleteFirebaseToken(String email, String firebaseToken);

	

	
}
