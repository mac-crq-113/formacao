package pt.mac.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pt.mac.demo.entities.CustomUserDetails;
import pt.mac.demo.entities.User;
import pt.mac.demo.repos.UserRepository;

/**
 *
 * @author mario
 * @since 23/10/2021
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = this.userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new CustomUserDetails(user);

//		Optional<User> user = this.userRepository.findByUsername(username);
//		return user.map(MyUserPrincipal::new)
//				.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
	}
}