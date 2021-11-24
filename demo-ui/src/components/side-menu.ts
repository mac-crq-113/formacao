import { inject } from 'aurelia';
import { Offcanvas } from 'bootstrap'
import { AccountsService } from '../services/accounts-service';
import { PlaylistsService } from '../services/playlists-service';

@inject()
export class SideMenu {
    private sideMenuElem: HTMLDivElement;

    private playlists;

    constructor(private accountsService: AccountsService, private playlistsService: PlaylistsService) {

    }


    attached() {
        new Offcanvas(this.sideMenuElem, {});
        this.sideMenuElem.addEventListener('show.bs.offcanvas', () => {
            this.playlistsService.getAll().then(_pl => this.playlists = _pl)
        })
    }



}