import { inject, IRouter, lifecycleHooks } from 'aurelia';
import { Home } from './home';
import { Create } from './pages/accounts/create';
import { Login } from './pages/login';
import { PlaylistCreate } from './pages/playlists/playlist-create';
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
            component: Create,
            title: 'Criar Conta'
        },
        {
            path: 'login',
            component: Login,
            title: 'Login'
        },
        {
            path: 'playlists',
            component: Login,
            title: 'Login'
        },
        {
            path: 'playlists/new',
            component: PlaylistCreate,
            title: 'Login'
        }
    ];

    constructor(private accountsService: AccountsService, @IRouter private appRouter: IRouter) {

    }

    canLoad(_vm, _params, _next, _current) {

        console.info("entrou 1111111111!!!", this.accountsService.userIsLoggedIn, _next.component.name);

        let routeToLoad=null;

        switch(_next.component.name){
            case 'login':
            case 'create-account':
                routeToLoad = true;
                break;
            default:
                routeToLoad = this.accountsService.checkAuth()? 'login' : false
        }

        return routeToLoad;
             
    }

}