import {inject} from 'aurelia';
import { AccountsService } from '../../services/accounts-service';
@inject()
export class Create{
    
    private formElem:HTMLFormElement;

    constructor(private accountsService:AccountsService){

    }

    criar(_evt:SubmitEvent){
        _evt.preventDefault();

        const account = Object.fromEntries(new FormData(this.formElem));
        this.accountsService.register(account);
    }
}