import { inject, IRouter, Params } from 'aurelia';
import { IPlaylist } from './../../entities/playlist';
import { PlaylistsService } from './../../services/playlists-service';

@inject()
export class PlaylistCreate {

    private playlist: IPlaylist;

    constructor(private playlistsService: PlaylistsService, @IRouter private router: IRouter) {

    }

    load(_params: Params) {
        if (_params.id) {
            return this.playlistsService.get(_params.id).then(_p => {
                this.playlist = _p;
            });
        }
    }

    criar(_evt: SubmitEvent) {
        _evt.preventDefault();

        this.playlistsService.create(this.playlist).then(_p => {
            this.router.load('playlists');
        })
    }

    remove() {
        this.playlistsService.delete(this.playlist.id).then(_p => {
            this.router.load('playlists');
        })
    }
}