import { inject } from 'aurelia';
import { IPlaylist } from './../../entities/playlist';
import { PlaylistsService } from './../../services/playlists-service';

@inject()
export class PlaylistList {

    private playlists: IPlaylist[]

    constructor(private pService: PlaylistsService) {

    }

    load() {
        return this.pService.getAll().then(_ps => this.playlists = _ps)

    }
}