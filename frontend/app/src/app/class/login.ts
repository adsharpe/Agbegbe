import { User } from './user';

export class Login {
    id : number;
    username : string;
    password : string;
    registrationDate : number;
    enabled : boolean;
    tokenValue : string;
    tokenRefresh : string;
    tokenDateTime : number;
    user : User;
}
