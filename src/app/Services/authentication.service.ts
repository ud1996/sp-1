import { Injectable } from '@angular/core';
import { User } from '../Model/user.model';
import { UserService } from './user.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn:'root'
})

export class AuthenticationService{

    loggedIn:boolean=false;
    isAdmin:boolean=false;
    authSource:string;
    redirectUrl='/';
    userAuthenticated:User;
    accessToken:string;
    role:string;

    constructor(private httpClient:HttpClient){}
    authenticate(username:string,password:string):Observable<any>{
        let credentials = btoa(username + ':' + password);
        let headers = new HttpHeaders();
        headers = headers.set('Authorization','Basic '+ credentials);
        console.log(headers);
        return this.httpClient.get("http://localhost:8000/authenticate/", { headers });
    }

    logout(){
        this.redirectUrl="/";
        this.loggedIn=false;
    }

    isAdminUser(){
        return this.isAdmin;
    }

}