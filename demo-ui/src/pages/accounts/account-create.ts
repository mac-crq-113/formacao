import { IAccount } from './../../entities/account';
import { inject, IRouter } from 'aurelia';
import { AccountsService } from '../../services/accounts-service';


@inject()
export class AccountCreate {

    private formElem: HTMLFormElement;

    private account: IAccount;
    private password2: string;

    constructor(private accountsService: AccountsService, @IRouter private router: IRouter) {
        this.account = {};
    }

    get disable() {
        console.info("test")
        return this.account.password != this.password2 || this.formElem.checkValidity() === false;
    }

    criar(_evt: SubmitEvent) {
        this.accountsService.register(this.account).then(_r => {
            this.router.load('login');
        });
    }
}