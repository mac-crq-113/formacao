import { HttpClient, inject, IRouter, json } from 'aurelia';
import { IAccount } from './../entities/account';
import { FetchUtils } from './../utils/fetch-utils';

@inject()
export class AccountsService {

    public userIsLoggedIn = false;
    private client: HttpClient;

    constructor(private fetchUtils: FetchUtils, @IRouter private router: IRouter) {
        this.client = this.fetchUtils.getClient();
    }

    async login(_user, _pwd) {

        const form = new FormData();
        form.set('username', _user);
        form.set('password', _pwd);

        return this.client.post('/login', form).then(() => {
            this.userIsLoggedIn = true;
            this.router.load('home');
            console.info('logged in', this.userIsLoggedIn);
        });
    }

    async logout() {

        return this.client.get('/logout').then(() => {
            this.userIsLoggedIn = false;
            this.router.load('home');
        });
    }

    async register(_account: IAccount) {
        this.client.post('/accounts', json(_account));
    }


    async checkAuth() {
        
        return this.userIsLoggedIn;
    }

}