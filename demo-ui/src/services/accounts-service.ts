import { HttpClient, inject, json } from 'aurelia';
import { IAccount } from './../entities/account';
import { FetchUtils } from './../utils/fetch-utils';

@inject()
export class AccountsService {

    public userIsLoggedIn = false;

    private client: HttpClient;

    constructor(private fetchUtils: FetchUtils) {
        this.client = this.fetchUtils.getClient();
    }

    async login(_user, _pwd) {

        const form = new FormData();

        form.set('username', _user);
        form.set('password', _pwd);

        return this.client.post('/login', form).then(() => this.userIsLoggedIn = true);
    }

    async logout() {
        return this.client.get('/logout').then(() => this.userIsLoggedIn = false);
    }

    async register(_account: IAccount) {
        this.client.post('/accounts', json(_account));
    }


    checkAuth() {
        return this.userIsLoggedIn;
    }

}