import { Injectable } from '@angular/core';

export class User {
    id?: number;
    username: string;
    password: string;
    firstname: string;
    lastname: string;
    email: string;
    active: boolean;
    role_id: number;
    depot_id: number;

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }

}
