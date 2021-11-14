package pt.mac.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonView;

import pt.mac.demo.DemoViews;

/**
 *
 * @author mario
 * @since 23-10-2021
 */
@Entity
@Table(name = "users")
@lombok.Data
public class User {

	@JsonProperty(access = Access.READ_ONLY)
	@JsonView(DemoViews.User.Basic.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long id;

	@JsonView(DemoViews.User.Basic.class)
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "password_", nullable = false, unique = true)
	private String password;

	@JsonView(DemoViews.User.Basic.class)
	@Column(name = "fullname", nullable = false)
	private String fullname;

}
