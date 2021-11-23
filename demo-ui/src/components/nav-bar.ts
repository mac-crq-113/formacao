import { inject } from 'aurelia';
import { AccountsService } from "../services/accounts-service";

@inject()
export class NavBar {

    constructor(private accountsService: AccountsService) {

    }

    logout(){
        this.accountsService.logout()
    }
}