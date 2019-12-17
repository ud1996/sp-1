import { Inject, Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../Model/user.model';
import { Observable } from 'rxjs';
import { AuthenticationService } from './authentication.service';

@Injectable({
    providedIn:'root'
})

export class UserService{
    credentials: string;
    accessToken: string = '';
    token:string;
    constructor(private httpClient: HttpClient,private authService:AuthenticationService){}

    authentcate(user:User):Observable<any>{
        console.log(user); 
       
        return this.httpClient.post<any>("http://localhost:8000/user/signup",user);
        
    }

    getAllUsers():Observable<any>{
        this.token = this.authService.accessToken;
        let headers = new HttpHeaders();
        headers = headers.set('Authorization','Bearer '+this.token);
        console.log(this.httpClient.get<any>("http://localhost:8000/user",{ headers }));
        
        return this.httpClient.get<any>("http://localhost:8000/user",{ headers });
       
    }

    getUser(email:string):Observable<any>{
        this.token = this.authService.accessToken;
        let headers = new HttpHeaders();
        headers = headers.set('Authorization','Bearer '+this.token);
        console.log("http://localhost:8000/user/getUser/"+email);
        return this.httpClient.get<any>(`http://localhost:8000/user/getUser/${email}`);
    }

    getWallet(email:string):Observable<any>{
        this.token = this.authService.accessToken;
        let headers = new HttpHeaders();
        headers = headers.set('Authorization','Bearer '+this.token);
        console.log("http://localhost:8000/user/getUser/"+email);
        return this.httpClient.get<any>(`http://localhost:8000/user/wallet/${email}`);
    }


    approveUser(id:number):Observable<any>{
        this.token = this.authService.accessToken;
        let headers = new HttpHeaders();
        headers = headers.set('Authorization','Bearer '+this.token);
        console.log("in servoce of user"+id);
        //return this.httpClient.get<any>("http://localhost:8000/user",{ headers });
       return this.httpClient.get<any>(`http://localhost:8000/admin/approveUser/${id}`,{ headers });
    }

    declineUser(id:number):Observable<any>{
        this.token = this.authService.accessToken;
        let headers = new HttpHeaders();
        headers = headers.set('Authorization','Bearer '+this.token);
        console.log("in servoce of user"+id);
       return this.httpClient.delete<void>(`http://localhost:8000/admin/declineUser/${id}`,{ headers });
    }

    getApprovedUsers(){
        this.token = this.authService.accessToken;
        let headers = new HttpHeaders();
        headers = headers.set('Authorization','Bearer '+this.token);
        return this.httpClient.get<any>(`http://localhost:8000/user/approvedUser/`,{ headers });
    }

    updateWalletBalance(email:string,amount:number):Observable<any>{

        this.token = this.authService.accessToken;
        let headers = new HttpHeaders();
        headers = headers.set('Authorization','Bearer '+this.token);
        return this.httpClient.post<any>(`http://localhost:8000/user/wallet/${email}/${amount}`,{ headers });
    }
}