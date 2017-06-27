import { Injectable } from '@angular/core';
import { User } from './user';

@Injectable()
export class UserService {

  lastId: number = 3;


  users: User[] = [
    {
      id: 1, username: 'user1', password: 'admin', firstname: 'User', lastname: '1', email: 'user1@gmail.com', active: true, role_id: 1, depot_id: 1
    },
    { id: 2, username: 'user2', password: 'admin', firstname: 'User', lastname: '2', email: 'user2@gmail.com', active: true, role_id: 2, depot_id: 2 }
  ]

  constructor() { }

  // Simulate GET /users
  getUsers() : User[] {
    return this.users;
  }

  // Simulate POST /users
  createUser(user: User): UserService {
    if (!user.id) {
      user.id = ++this.lastId;
    }
    this.users.push(user);
    return this;
  }

  // Simulate GET /users/:id
  getUserById(id: number): User {
    return this.users
      .filter(user => user.id === id)
      .pop();
  }

  // Simulate PUT /users/:id
  updateUser(id: number, values: Object = {}): User {
      let user = this.getUserById(id);
      if (!user) {
        return null;
      }
      Object.assign(user, values);
      return user;
    }
  
}
