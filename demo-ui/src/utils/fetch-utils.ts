import { HttpClient, inject, IRouter } from "aurelia";


@inject()
export class FetchUtils {

    constructor(private httpClient: HttpClient, @IRouter private router: IRouter) {
        this.httpClient.configure((_c) => {
            return _c.useStandardConfiguration()
                .withDefaults({ credentials: 'include', referrer: location.href })
                .withBaseUrl('http://localhost:8080/demo');
        });
    }
    public getClient = () => this.httpClient;


    static handleResponse(_r: Response) {
        if (_r && _r.ok) {
            return _r.json().then(_r => _r.body);
        } else {
            return _r;
        }
    }
}

