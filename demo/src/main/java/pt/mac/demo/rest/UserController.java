
/**
 *
 */
package pt.mac.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.annotation.JsonView;

import pt.mac.demo.DemoViews;
import pt.mac.demo.entities.User;
import pt.mac.demo.repos.UserRepository;

/**
 *
 * @author pms
 * @since 25/11/2021
 */
@RestController
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@JsonView(DemoViews.User.Basic.class)
	@GetMapping("/authenticated")
	public User getAuthenticated(Authentication authentication) {
		return this.userRepository.findByUsername(authentication.getName());
	}

}
