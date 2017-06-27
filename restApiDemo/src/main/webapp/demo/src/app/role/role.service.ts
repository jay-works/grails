import { Injectable } from '@angular/core';

import { Role } from '../role/role';

let roles: Object[] = []

@Injectable()
export class RoleService {
    lastId: number = 3;


    roles: Role[] = [
        { id: 1, code: 'ADMIN', description: 'ADMINISTRATOR', active: true },
        { id: 2, code: 'USER', description: 'USER ROLE', active: true }
    ]

    constructor() { }

    // Simulate GET /roles
    getRoles(): Role[] {
        return this.roles;
    }

    // Simulate POST /roles
    createRole(role: Role): RoleService {
        if (!role.id) {
            role.id = ++this.lastId;
        }
        this.roles.push(role);
        return this;
    }

    // Simulate GET /roles/:id
    getRoleById(id: number): Role {
        return this.roles
            .filter(role => role.id === id)
            .pop();
    }

    // Simulate PUT /roles/:id
    updateRole(id: number, values: Object = {}): Role {
        let role = this.getRoleById(id);
        if (!role) {
            return null;
        }
        Object.assign(role, values);
        return role;
    }
}