/**
 *
 */
package pt.mac.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import pt.mac.demo.DemoViews;
import pt.mac.demo.entities.User;
import pt.mac.demo.services.CustomUserDetailsService;

/**
 *
 * @author mario
 * @since 14/11/2021
 */
@RestController
@RequestMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

	@Autowired
	private CustomUserDetailsService userService;

	// Save
	@JsonView(DemoViews.Playlist.Basic.class)
	@PostMapping
	public User createAccount(@RequestBody User user) {
		return this.userService.create(user);
	}

}
