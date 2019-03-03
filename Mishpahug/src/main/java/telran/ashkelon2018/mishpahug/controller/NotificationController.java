package telran.ashkelon2018.mishpahug.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.ashkelon2018.mishpahug.domain.Notification;
import telran.ashkelon2018.mishpahug.dto.CodeResponseDto;
import telran.ashkelon2018.mishpahug.dto.NotificationsCountResponseDto;
import telran.ashkelon2018.mishpahug.service.NotificationService;

@CrossOrigin(origins="*", allowedHeaders= "*" )
@RestController
public class NotificationController {

	@Autowired
	NotificationService notificationService;

	@GetMapping("/notification/list")
	public List<Notification> getNotificationList(Principal principal) {
		return notificationService.getNotificationList(principal.getName());
	}

	@PutMapping("/notification/isRead/{notificationId}")
	public CodeResponseDto notificationIsRead(Principal principal, @RequestParam int notificationId) {
		return notificationService.notificationIsRead(principal.getName(), notificationId);
	}

	@GetMapping("/notification/count")
	public NotificationsCountResponseDto  countUnreadNotifications(Principal principal) {
		return notificationService.countUnreadNotifications(principal.getName());
	}

	@PostMapping("/user/firebasetoken/add")
	public CodeResponseDto addFirebaseToken(Principal principal, String firebaseToken) {
		return notificationService.addFirebaseToken(principal.getName(), firebaseToken);
	}

	@DeleteMapping("/user/firebasetoken/delete")
	public CodeResponseDto deleteFirebaseToken(Principal principal, @RequestBody String firebaseToken) {
		return notificationService.deleteFirebaseToken(principal.getName(), firebaseToken);
	}

}
