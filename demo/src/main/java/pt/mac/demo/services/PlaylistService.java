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
		// se tiver id tem de existir e pertencer ao utilizador
		if (playlist.getId() != null) {
			this.findPlaylistById(playlist.getId(), user);
		}

		// garantir que o owner está preenchido com o utilizador atual
		playlist.setOwner(user.getEntity());

		// guardar
		return this.playlistRepo.save(playlist);
	}

	public void deletePlaylist(Long idPlaylist, CustomUserDetails user) {
		// verificar se existe e se pertence ao utilizador
		Playlist playlist = this.findPlaylistById(idPlaylist, user);

		// remover
		this.playlistRepo.delete(playlist);
	}

	// entries ////////////////////////////////////////////////////////////////
	public PlaylistEntry savePlaylistEntry(PlaylistEntry playlistEntry, CustomUserDetails user) {
		// verificar se a playlist existe e pertence ao utilizador
		this.findPlaylistById(playlistEntry.getPlaylist().getId(), user);
		if (playlistEntry.getId() != null) {
			this.findPlaylistEntry(playlistEntry.getId(), user);
		}

		// guarar entrada
		return this.playlistEntryRepo.save(playlistEntry);
	}

	public List<PlaylistEntry> findAllPlaylistEntries(Long idPlaylist, CustomUserDetails user) {
		// verificar se a playlist existe e pertence ao utilizador
		this.findPlaylistById(idPlaylist, user);

		// obter entradas
		return this.playlistEntryRepo.findAllByPlaylistId(idPlaylist);
	}

	public PlaylistEntry findPlaylistEntry(Long idEntry, CustomUserDetails user) {
		// obter a entrada
		PlaylistEntry entry = this.playlistEntryRepo.findById(idEntry)
				.orElseThrow(() -> new RuntimeException("Entry " + idEntry + " not found"));

		// verificar se a playlist existe e pertence ao utilizador
		this.findPlaylistById(entry.getPlaylist().getId(), user);

		// devolver
		return entry;
	}

	public void deletePlaylistEntry(Long idEntry, CustomUserDetails user) {
		// obter a entrada
		PlaylistEntry entry = this.findPlaylistEntry(idEntry, user);

		// verificar se a playlist existe e pertence ao utilizador
		this.findPlaylistById(entry.getPlaylist().getId(), user);

		// remover
		this.playlistEntryRepo.delete(entry);
	}

	// end entries ////////////////////////////////////////////////////////////

}
