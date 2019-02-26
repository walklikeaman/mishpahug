package telran.ashkelon2018.mishpahug.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import telran.ashkelon2018.mishpahug.configuration.AccountConfiguration;
import telran.ashkelon2018.mishpahug.configuration.UserCredentials;
import telran.ashkelon2018.mishpahug.dao.NotificationRepository;
import telran.ashkelon2018.mishpahug.dao.UserRepository;
import telran.ashkelon2018.mishpahug.domain.UserAccount;
import telran.ashkelon2018.mishpahug.domain.UserNotifications;
import telran.ashkelon2018.mishpahug.dto.UserRegistrationResponseDto;
import telran.ashkelon2018.mishpahug.dto.UserResponseDto;
import telran.ashkelon2018.mishpahug.dto.UserStaticFieldsDto;
import telran.ashkelon2018.mishpahug.dto.UserUpdateDto;
import telran.ashkelon2018.mishpahug.exceptions.EmptyProfileException;
import telran.ashkelon2018.mishpahug.exceptions.UserExistExcpetion;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AccountConfiguration accountConfiguration;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	NotificationRepository notificationRepository;

	@Override
	public UserRegistrationResponseDto registrationUser(String token) {
		UserCredentials credentials = accountConfiguration.tokenDecode(token);
		if (userRepository.existsById(credentials.getEmail())) {
			throw new UserExistExcpetion();
		}
		String hashPassword = encoder.encode(credentials.getPassword());
		UserAccount userAccount = new UserAccount(credentials.getEmail(), hashPassword);
		userRepository.save(userAccount);
		return new UserRegistrationResponseDto();
	}

	@Override
	public UserResponseDto updateUserProfile(UserUpdateDto userUpdateDto, String email) {

		UserAccount userAccount = userRepository.findById(email).get();
		if (!userAccount.isRegistered()) {
			userAccount.setRegistered(true);
			userAccount.setSubscribedEvents(new ArrayList<>());
			notificationRepository.save(new UserNotifications(email));

		}
		userAccount.setFirstName(userUpdateDto.getFirstName());
		userAccount.setLastName(userUpdateDto.getLastName());
		userAccount.setDateOfBirth(userUpdateDto.getDateOfBirth());
		userAccount.setGender(userUpdateDto.getGender());
		userAccount.setConfession(userUpdateDto.getConfession());
		userAccount.setMaritalStatus(userUpdateDto.getMaritalStatus());
		userAccount.setLanguages(userUpdateDto.getLanguages());
		userAccount.setFoodPreferences(userUpdateDto.getFoodPreferences());
		userAccount.setPhoneNumber(userUpdateDto.getPhoneNumber());
		userAccount.setDescription(userUpdateDto.getDescription());
		userAccount.setRegistered(true);
		userAccount.setPictureLink(userUpdateDto.getPictureLink());

		userRepository.save(userAccount);
		return convertToUserResponseDto(userAccount);
	}

	// private void checkUserUpdateDto(UserUpdateDto userUpdateDto) {
	// if (!(userUpdateDto.getFirstName() != null && userUpdateDto.getLastName() !=
	// null
	// && userUpdateDto.getDateOfBirth() != null && userUpdateDto.getGender() !=
	// null
	// && userUpdateDto.getConfession() != null && userUpdateDto.getMaritalStatus()
	// != null
	// && userUpdateDto.getLanguages() != null && userUpdateDto.getFoodPreferences()
	// != null
	// && userUpdateDto.getPhoneNumber() != null && userUpdateDto.getDescription()
	// != null)) {
	// throw new InvalidDataException();
	// }
	// }

	@Override
	public UserResponseDto getUserProfile(String email) {
		UserAccount userAccount = userRepository.findById(email).get();
		if (userAccount.isRegistered()) {
			return convertToUserResponseDto(userAccount);
		}
		throw new EmptyProfileException();
	}

	private UserResponseDto convertToUserResponseDto(UserAccount userAccount) {
		return new UserResponseDto(userAccount.getFirstName(), userAccount.getLastName(), userAccount.getDateOfBirth(),
				userAccount.getGender(), userAccount.getMaritalStatus(), userAccount.getConfession(),
				userAccount.getPictureLink(), userAccount.getPhoneNumber(), userAccount.getFoodPreferences(),
				userAccount.getLanguages(), userAccount.getDescription(), userAccount.getRate(),
				userAccount.getNumberOfVoters());
	}

	@Override
	public UserResponseDto loginUser(String email) {
		UserAccount userAccount = userRepository.findById(email).get();
		if (userAccount.isRegistered()) {
			return convertToUserResponseDto(userAccount);
		}
		throw new EmptyProfileException();

	}

	@Override
	public UserStaticFieldsDto getStaticFields() {
		return new UserStaticFieldsDto();
	}

}
