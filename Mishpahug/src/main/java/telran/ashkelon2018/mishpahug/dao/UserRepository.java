package telran.ashkelon2018.mishpahug.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.ashkelon2018.mishpahug.domain.UserAccount;

public interface UserRepository extends MongoRepository<UserAccount, String> {
	
}
