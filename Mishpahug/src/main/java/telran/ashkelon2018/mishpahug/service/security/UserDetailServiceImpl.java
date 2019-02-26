package telran.ashkelon2018.mishpahug.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import telran.ashkelon2018.mishpahug.dao.UserRepository;
import telran.ashkelon2018.mishpahug.domain.UserAccount;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserAccount userAccount = userRepository.findById(email)
//				.orElse(null);
				.orElseThrow(()->new UsernameNotFoundException(email+ " not found"));
//		if (userAccount==null) {
//			throw new UsernameNotFoundException(email+" not found");
//		}
		String password = userAccount.getPassword();
		String[] roles = new String[0];
		
		return new User(email, password, AuthorityUtils.createAuthorityList(roles));
	}

}
