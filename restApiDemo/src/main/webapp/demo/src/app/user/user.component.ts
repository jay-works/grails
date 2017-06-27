import { Component, OnInit } from '@angular/core';
import { User } from './user';
import { Depot } from '../depot/depot';
import { Role } from '../role/role';
import { UserService } from './user.service';
import { RoleService } from '../role/role.service';
import { DepotService } from '../depot/depot.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

 newUser: User = new User();

  constructor(private userService: UserService) {
  }

  createUser() {
    this.userService.createUser(this.newUser);
    this.newUser = new User();
  }


  get users() {
    return this.userService.getUsers();
  }

  ngOnInit() {
  }

}
