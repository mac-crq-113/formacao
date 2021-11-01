/**
 *
 */
package pt.mac.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author mario
 * @since 30/10/2021
 */
@Entity
@Table(name = "pl_playlist_type")
@lombok.Data
public class PlaylistType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_type")
	private Integer id;

	@Column(name = "code")
	private String code;

	@Column(name = "decription")
	private String description;

}
