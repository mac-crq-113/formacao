import { inject, IRouter } from 'aurelia';
import { AccountsService } from "../services/accounts-service";

@inject()
export class NavBar {

    constructor(private accountsService: AccountsService, @IRouter private router: IRouter) {

    }

    attached() {
        console.info(this.router)
    }

    logout() {
        this.accountsService.logout().then(_r => {
            this.router.load('login');
        })
    }
}