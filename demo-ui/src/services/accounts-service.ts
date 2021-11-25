import { HttpClient, inject, json } from 'aurelia';
import { IAccount } from './../entities/account';
import { FetchUtils } from './../utils/fetch-utils';

@inject()
export class AccountsService {

    public userIsLoggedIn = false;
    public currentUser;

    private client: HttpClient;

    constructor(private fetchUtils: FetchUtils) {
        this.client = this.fetchUtils.getClient();
    }

    async login(_user, _pwd) {
        return this.client.get('/user/authenticated', { headers: { "Authorization": `Basic ${btoa(_user + ':' + _pwd)}` } }).then(FetchUtils.handleResponse).then((_u) => {
            this.userIsLoggedIn = true
            this.currentUser = _u;
        });
    }

    async logout() {
        return this.client.get('/logout').then((_r) => {
            this.userIsLoggedIn = false;
            this.currentUser = null;
            return true;
        });
    }

    async getUserAuthenticated() {
        return this.client.get('/user/authenticated').then(FetchUtils.handleResponse)
    }

    async register(_account: IAccount) {
        this.client.post('/accounts', json(_account)).then(FetchUtils.handleResponse);
    }


    async checkAuth() {
        if (this.userIsLoggedIn) {
            return true;
        }

        return this.getUserAuthenticated().then(_user => {
            this.userIsLoggedIn = true
            this.currentUser = _user;
            return true;
        }).catch(() => {
            return 'login'
        });
    }

}