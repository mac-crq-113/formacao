import { IAccount } from './../../entities/account';
import { inject, IRouter } from 'aurelia';
import { AccountsService } from '../../services/accounts-service';


@inject()
export class AccountCreate {


    private account: IAccount;

    constructor(private accountsService: AccountsService, @IRouter private router: IRouter) {
        this.account = {};
    }

    criar(_evt: SubmitEvent) {
        this.accountsService.register(this.account).then(_r => {
            this.router.load('login');
        });
    }
}