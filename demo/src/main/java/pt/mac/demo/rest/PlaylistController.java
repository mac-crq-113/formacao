/**
 *
 */
package pt.mac.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import pt.mac.demo.DemoViews;
import pt.mac.demo.entities.CustomUserDetails;
import pt.mac.demo.entities.Playlist;
import pt.mac.demo.entities.PlaylistEntry;
import pt.mac.demo.services.PlaylistService;

/**
 * @author mario
 * @since 31/10/2021
 */
@RestController
@RequestMapping(path = "/playlists", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlaylistController {

	@Autowired
	private PlaylistService playlistService;

	// Find all user's playlists
	@JsonView(DemoViews.Playlist.Basic.class)
	@GetMapping
	public List<Playlist> getPlaylists(@AuthenticationPrincipal CustomUserDetails user) {
		return this.playlistService.findAllByOwner(user);
	}

	// Find by id
	@JsonView(DemoViews.Playlist.Basic.class)
	@GetMapping(path = "/{idPlaylist}")
	public Playlist getPlaylistById(@PathVariable Long idPlaylist, @AuthenticationPrincipal CustomUserDetails user) {
		return this.playlistService.findPlaylistById(idPlaylist, user);
	}

	// Save
	@JsonView(DemoViews.Playlist.Basic.class)
	@PostMapping
	public Playlist savePlaylist(@RequestBody Playlist playlist, @AuthenticationPrincipal CustomUserDetails user) {
		return this.playlistService.savePlaylist(playlist, user);
	}

	// Delete
	@DeleteMapping(path = "/{idPlaylist}")
	public void deletePlaylist(@PathVariable Long idPlaylist, @AuthenticationPrincipal CustomUserDetails user) {
		this.playlistService.deletePlaylist(idPlaylist, user);
	}

	// Find all entries
	@JsonView(DemoViews.PlaylistEntry.Basic.class)
	@GetMapping(path = "/{idPlaylist}/entries")
	public List<PlaylistEntry> getEntries(@PathVariable Long idPlaylist,
			@AuthenticationPrincipal CustomUserDetails user) {
		return this.playlistService.findAllPlaylistEntries(idPlaylist, user);
	}

	// Find entry by id
	@JsonView(DemoViews.PlaylistEntry.Basic.class)
	@GetMapping(path = "/entries/{idEntry}")
	public PlaylistEntry getEntry(@PathVariable Long idEntry, @AuthenticationPrincipal CustomUserDetails user) {
		return this.playlistService.findPlaylistEntry(idEntry, user);
	}

	// Save entry
	@JsonView(DemoViews.PlaylistEntry.Basic.class)
	@PostMapping(path = "/{id}/entries")
	public PlaylistEntry saveEntry(@RequestBody PlaylistEntry playlistEntry,
			@AuthenticationPrincipal CustomUserDetails user) {
		return this.playlistService.savePlaylistEntry(playlistEntry, user);
	}

	// Delete entry
	@DeleteMapping(path = "/entries/{idEntry}")
	public void deleteEntry(@PathVariable Long idEntry, @AuthenticationPrincipal CustomUserDetails user) {
		this.playlistService.deletePlaylistEntry(idEntry, user);
	}

}
