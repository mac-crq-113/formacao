package pt.mac.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author mario
 * @since 30/10/2021
 */
@Entity
@Table(name = "pl_playlist_entry")
@lombok.Data
public class PlaylistEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_entry")
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "decription")
	private String description;

	@Column(name = "url", nullable = false)
	private String url;

	@ManyToOne
	@JoinColumn(name = "id_type", nullable = false)
	private PlaylistType entryType;

	@ManyToOne
	@JoinColumn(name = "id_playlist", nullable = false)
	private Playlist playlist;
}
