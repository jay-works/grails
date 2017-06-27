import { Component, OnInit } from '@angular/core';
import {AuthService} from '../login/auth.service'

@Component({
  selector: 'app-home',
  providers: [AuthService],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

  constructor(
        private _service:AuthService){}
 
    ngOnInit(){
        this._service.checkCredentials();
    }
 
    logout() {
        this._service.logout();
    }

}
