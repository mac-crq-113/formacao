import { inject, lifecycleHooks } from 'aurelia';
import { AccountCreate } from './pages/accounts/account-create';
import { Home } from './pages/home';
import { Login } from './pages/login';
import { PlaylistCreate } from './pages/playlists/playlist-create';
import { PlaylistEntriesList } from './pages/playlists/playlist-entries-list';
import { PlaylistList } from './pages/playlists/playlist-list';
import { AccountsService } from './services/accounts-service';


@inject()
@lifecycleHooks()
export class DemoApp {

    static routes = [
        {
            path: ['', 'home'],
            component: Home,
            title: 'Início'
        },
        {
            path: 'create-account',
            component: AccountCreate,
            title: 'Criar Conta'
        },
        {
            path: 'login',
            component: Login,
            title: 'Login'
        },
        {
            path: ['playlists', 'playlists/list'],
            component: PlaylistList,
            title: 'Listagem'
        },
        {
            path: 'playlists/new',
            component: PlaylistCreate,
            title: 'Criar'
        },
        {
            path: 'playlists/edit/:id',
            component: PlaylistCreate,
            title: 'Editar'
        },
        {
            path: 'playlists/:id/entries',
            component: PlaylistEntriesList,
            title: 'Editar'
        }
    ];

    constructor(private accountsService: AccountsService) {

    }


    canLoad(_vm, _params, _next, _current) {

        if (['login', 'account-create'].indexOf(_next.component.name) >= 0) {
            return true;
        } else {
            return this.accountsService.checkAuth();
        }

    }

}