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
    accessToken?:string;
}