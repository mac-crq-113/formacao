import { inject, IRouter, lifecycleHooks } from 'aurelia';
import { AccountCreate } from './pages/accounts/account-create';
import { Home } from './pages/home';
import { Login } from './pages/login';
import { PlaylistCreate } from './pages/playlists/playlist-create';
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
        }
    ];

    constructor(private accountsService: AccountsService, @IRouter private appRouter: IRouter) {

    }

    canLoad(_vm, _params, _next, _current) {

        let routeToLoad = null;

        console.info("canLoad?", _next.component.name, "loggedIN?", this.accountsService.checkAuth())

        switch (_next.component.name) {
            case 'login':
            case 'account-create':
                routeToLoad = true;
                break;
            default:
                routeToLoad = this.accountsService.checkAuth() ? true : 'login'
                break;
        }

        console.info("routeToLoad", routeToLoad)

        return routeToLoad;

    }

}