import { AccountsService } from './services/accounts-service';
import { PlaylistsService } from './services/playlists-service';
export class Home {

    private playlists;

    constructor(private playlistService: PlaylistsService, private accountsService: AccountsService) {

    }

}