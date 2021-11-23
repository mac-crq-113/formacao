import { inject, IRouter } from 'aurelia';
import { AccountsService } from "../services/accounts-service";

@inject()
export class Login {

    private formElem: HTMLFormElement;

    constructor(private accountsService: AccountsService, @IRouter private router: IRouter) {

    }

    login(_evt: SubmitEvent) {
        _evt.preventDefault();
        this.accountsService.login(this.formElem.username.value, this.formElem.password.value);
    }

}