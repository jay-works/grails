import { Injectable } from '@angular/core';

export class Role {
    id: number;
    code: string;
    description: string;
    active: boolean;

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }

}
