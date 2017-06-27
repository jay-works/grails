import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
@Injectable()
export class AuthService {
 
  constructor(
    private _router: Router){}
 
  logout() {
    localStorage.removeItem("email");
    this._router.navigate(['login']);
  }
 
  login(user){
    var authenticatedUser = users.find(u => u.email === user.email);
    if (authenticatedUser && authenticatedUser.password === user.password){
      localStorage.setItem("email", authenticatedUser.email);
      this._router.navigate(['home']);      
      return true;
    }
    return false;
 
  }
 
   checkCredentials(){
    if (localStorage.getItem("email") === null){
        this._router.navigate(['login']);
    }
  } 
}


export class User {
  constructor(
    public email: string,
    public password: string) { }
}
 
var users = [
  new User('admin@admin.com','adm9'),
  new User('user1@gmail.com','a23')
];
