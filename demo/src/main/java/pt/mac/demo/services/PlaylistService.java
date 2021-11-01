/**
 *
 */
package pt.mac.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.mac.demo.entities.CustomUserDetails;
import pt.mac.demo.entities.Playlist;
import pt.mac.demo.entities.PlaylistEntry;
import pt.mac.demo.repos.PlaylistEntryRepository;
import pt.mac.demo.repos.PlaylistRepository;

/**
 * @author mario
 * @since 01/11/2021
 */
@Service
public class PlaylistService {

	@Autowired
	private PlaylistRepository playlistRepo;

	@Autowired
	private PlaylistEntryRepository playlistEntryRepo;

	public List<Playlist> findAll() {
		return this.playlistRepo.findAll();
	}

	public List<Playlist> findAllByOwner(CustomUserDetails user) {
		return this.playlistRepo.findByOwnerUsername(user.getUsername());
	}

	public Playlist findPlaylistById(Long idPlaylist, CustomUserDetails user) {
		Playlist playlist = this.playlistRepo.findById(idPlaylist)
				.orElseThrow(() -> new RuntimeException("Playlist " + idPlaylist + " not found"));
		if (playlist.getOwner().getUsername().equals(user.getUsername())) {
			return playlist;
		} else {
			throw new RuntimeException("Not allowed");
		}

	}

	public Playlist savePlaylist(Playlist playlist, CustomUserDetails user) {
		// se tiver id tem de existir e ser pertencer ao utilizador

		playlist.setOwner(user.getEntity());
		return this.playlistRepo.save(playlist);
	}

	public void deletePlaylist(Long idPlaylist, CustomUserDetails user) {
		Playlist playlist = this.findPlaylistById(idPlaylist, user);
		this.playlistRepo.delete(playlist);
	}

	public PlaylistEntry savePlaylistEntry(PlaylistEntry playlistEntry, CustomUserDetails user) {
		// verificar se a playlist existe e pertence ao utilizador

		return this.playlistEntryRepo.save(playlistEntry);
	}

	public List<PlaylistEntry> findAllPlaylistEntries(Long idPlaylist, CustomUserDetails user) {
		return this.playlistEntryRepo.findAllByPlaylistId(idPlaylist);
	}

	public PlaylistEntry findPlaylistEntry(Long idEntry, CustomUserDetails user) {
		// verificar se a playlist existe e pertence ao utilizador
		return this.playlistEntryRepo.findById(idEntry)
				.orElseThrow(() -> new RuntimeException("Entry " + idEntry + " not found"));
	}

	public void deletePlaylistEntry(Long idEntry, CustomUserDetails user) {
		PlaylistEntry entry = this.findPlaylistEntry(idEntry, user);
		this.playlistEntryRepo.delete(entry);
	}

}
