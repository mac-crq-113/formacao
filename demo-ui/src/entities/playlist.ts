import { IAccount } from './account';

export interface IPlaylist {
    id?: string;
    name?: string;
    description?: string;
    owner?: IAccount;
    entries: string[]
}