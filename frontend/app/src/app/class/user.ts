import { Title } from './title';
import { Suffix } from './suffix';
import { ProfileType } from './profile-type';

export class User {
    id : number;
    firstName : string;
    lastName : string;
    title : Title;
    suffix: Suffix;
    profileType : ProfileType;
}
