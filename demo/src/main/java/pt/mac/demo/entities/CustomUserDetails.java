package pt.mac.demo.entities;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author mario
 * @since 23/10/2021
 */
public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = -2495228964187111887L;

	private User entity;

	public CustomUserDetails(User user) {
		this.entity = user;
	}

	@Override
	public String getUsername() {
		return this.entity.getUsername();
	}

	@Override
	public String getPassword() {
		return this.entity.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority("User"));
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public User getEntity() {
		return this.entity;
	}
}