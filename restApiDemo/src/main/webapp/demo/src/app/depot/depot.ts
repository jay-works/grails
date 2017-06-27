import { Injectable } from '@angular/core';

export class Depot {
    id: number;
    code: string;
    name: string;
    active: boolean;

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }

}

