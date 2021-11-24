import { IPlaylist } from './../../entities/playlist';
import { inject } from 'aurelia';
import { PlaylistsService } from './../../services/playlists-service';

@inject()
export class PlaylistList {

    private playlists: IPlaylist[]

    constructor(private pService: PlaylistsService) {

    }

    load() {
        return this.pService.getAll().then(_ps => {
            console.info("entrou!!", _ps);
            this.playlists = _ps;
        })

    }
}