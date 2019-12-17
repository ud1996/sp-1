import { Wallet } from './wallet.model';
import { Transaction } from './transaction.model';

export interface User{
    userId?:number;
    email:string;
    firstName?:string;
    lastName?:string;
    password?:string;
    gender?:string;
    vendorId?: number;
    isApproved?:boolean;
    age?:number;
    contactNumber?:number;
    wallet ?: Wallet;
    transaction ?:Transaction;
    accessToken?:string;
}