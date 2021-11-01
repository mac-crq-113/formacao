package pt.mac.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.mac.demo.entities.Playlist;

/**
 *
 * @author mario
 * @since 01/11/2021
 */
@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

	List<Playlist> findByOwnerUsername(String username);

}