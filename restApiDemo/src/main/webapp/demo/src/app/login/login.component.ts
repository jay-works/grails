import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {AuthService, User} from './auth.service'
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'login-form',
  providers: [AuthService],
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  public user = new User('','');
  public errorMsg = '';
 
  constructor(
  private _service:AuthService) {}
 
  login() {
        if(!this._service.login(this.user)){
            this.errorMsg = 'login failed';
        }
  }
  ngOnInit() {
  }

}
