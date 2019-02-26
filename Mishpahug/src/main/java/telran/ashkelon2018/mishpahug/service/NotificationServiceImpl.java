package telran.ashkelon2018.mishpahug.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.ashkelon2018.mishpahug.configuration.AccountConfiguration;
import telran.ashkelon2018.mishpahug.dao.NotificationRepository;
import telran.ashkelon2018.mishpahug.dao.UserRepository;
import telran.ashkelon2018.mishpahug.domain.Notification;
import telran.ashkelon2018.mishpahug.domain.UserNotifications;
import telran.ashkelon2018.mishpahug.dto.CodeResponseDto;
import telran.ashkelon2018.mishpahug.dto.NotificationsCountResponseDto;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AccountConfiguration accountConfiguration;

	@Override
	public List<Notification> getNotificationList(String email) {
		// UserNotifications user =
		// notificationRepository.findById(email).orElse(null);// TODO check user? sort?
		// if (user == null) {
		// throw new WrongLoginOrPasswordException();
		// }
		return notificationRepository.findById(email).get().getNotifications();
	}

	@Override
	public CodeResponseDto notificationIsRead(String email, int notificationId) {
		// TODO error 409
		UserNotifications user = notificationRepository.findById(email).get();
		user.getNotifications().stream().filter(n -> n.getNotificationId() == notificationId).findFirst().get()
				.setRead(true);
		notificationRepository.save(user);
		return new CodeResponseDto(200, "Notification is updated!");
	}

	@Override
	public NotificationsCountResponseDto countUnreadNotifications(String email) {
		UserNotifications user = notificationRepository.findById(email).get();
		long count = user.getNotifications().stream().filter(n -> n.isRead() == false).count();
		return new NotificationsCountResponseDto(count);
	}

	@Override
	public CodeResponseDto addFirebaseToken(String token, String fireBaseToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeResponseDto deleteFirebaseToken(String token, String fireBaseToken) {
		// TODO Auto-generated method stub
		return null;
	}

}