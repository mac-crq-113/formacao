package pt.mac.demo.entities;

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

/**
 *
 * @author mario
 * @since 30/10/2021
 */
@Entity
@Table(name = "pl_playlist")
@lombok.Data
public class Playlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_playlist")
	private Long id;

	@Column(name = "name_", nullable = false)
	private String name;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "id_owner", nullable = false)
	private User owner;

	@OneToMany(mappedBy = "playlist", fetch = FetchType.LAZY)
	private List<PlaylistEntry> entries;

}
