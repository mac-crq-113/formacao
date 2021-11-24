import { HttpClient, inject, json } from 'aurelia';
import { FetchUtils } from '../utils/fetch-utils';
import { IPlaylist } from './../entities/playlist';

@inject()
export class PlaylistsService {


    private client: HttpClient;

    constructor(private fetchUtils: FetchUtils) {
        this.client = this.fetchUtils.getClient();
    }

    create(_newPlaylist: IPlaylist): Promise<IPlaylist> {
        return this.client.post('/playlists', json(_newPlaylist)).then(FetchUtils.handleResponse);
    }

    getAll(): Promise<IPlaylist[]> {
        return this.client.get('/playlists').then(FetchUtils.handleResponse);
    }

    get(_id: string): Promise<IPlaylist> {
        return this.client.get('/playlists/' + _id).then(FetchUtils.handleResponse);
    }

    delete(_id: string): Promise<IPlaylist> {
        return this.client.delete('/playlists/' + _id).then(FetchUtils.handleResponse);
    }



}