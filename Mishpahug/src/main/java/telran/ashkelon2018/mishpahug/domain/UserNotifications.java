package telran.ashkelon2018.mishpahug.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "notifications")
@Getter
@Setter
@EqualsAndHashCode(of = { "userId" })
@NoArgsConstructor
@AllArgsConstructor

public class UserNotifications {
	@Id
	String userId;
	List<Notification> notifications;

	public UserNotifications(String userId) {
		super();
		this.userId = userId;
		this.notifications = new ArrayList<>();
	}

}
