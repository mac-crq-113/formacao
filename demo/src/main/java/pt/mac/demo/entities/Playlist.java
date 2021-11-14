package pt.mac.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonView;

import pt.mac.demo.DemoViews;

/**
 *
 * @author mario
 * @since 30/10/2021
 */
@Entity
@Table(name = "pl_playlist")
@lombok.Data
public class Playlist {

	@JsonView(DemoViews.Playlist.Basic.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_playlist")
	private Long id;

	@JsonView(DemoViews.Playlist.Basic.class)
	@Column(name = "name_", nullable = false)
	private String name;

	@JsonView(DemoViews.Playlist.Basic.class)
	@Column(name = "description")
	private String description;

	@JsonView(DemoViews.Playlist.Basic.class)
	@JsonProperty(access = Access.READ_ONLY)
	@ManyToOne
	@JoinColumn(name = "id_owner", nullable = false)
	private User owner;

	@JsonProperty(access = Access.READ_ONLY)
	@JsonView(DemoViews.Playlist.Basic.class)
	@OneToMany(mappedBy = "playlist", fetch = FetchType.LAZY)
	private List<PlaylistEntry> entries = new ArrayList<>();

}
