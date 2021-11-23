import { HttpClient, inject } from 'aurelia';
import { FetchUtils } from '../utils/fetch-utils';

@inject()
export class PlaylistsService {

    private client: HttpClient;

    constructor(private fetchUtils: FetchUtils) {
        this.client = this.fetchUtils.getClient();
    }


    getAll() {
        return this.client.get('/playlists');
    }

}