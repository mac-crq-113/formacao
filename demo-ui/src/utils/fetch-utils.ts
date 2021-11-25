import { HttpClient, inject, IRouter } from "aurelia";


@inject()
export class FetchUtils {
    constructor(private httpClient: HttpClient, @IRouter private router: IRouter) {
        this.httpClient.configure((_c) => {
            return _c.useStandardConfiguration()
                .withDefaults({ credentials: 'include', referrer: location.href })
                .withInterceptor({
                    response: (_response, _request) => {
                        if (_response.ok && (!_request.url.endsWith('/login') && !_request.url.endsWith('/logout'))) {
                            return _response.json().then(_r => _r.body);

                        }
                        return _response.text();
                    },
                    responseError: (_response: Response, _request, _client) => {
                        if (_response && _response.status === 401) {
                            router.load('login');
                        }

                        return _response;
                    }
                })
                .withBaseUrl('http://localhost:8080/demo');
        });
    }
    public getClient = () => this.httpClient;
}

