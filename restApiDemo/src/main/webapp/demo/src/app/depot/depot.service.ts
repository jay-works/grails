import { Injectable } from '@angular/core';
import { Depot } from '../depot/depot';

@Injectable()
export class DepotService {
    lastId: number = 3;

    depots: Depot[] = [{ id: 1, code: "CHN", name: "Chennai", active: true },
    { id: 2, code: "MUM", name: "Mumbai", active: true }]

    constructor() { }

    // Simulate GET /depots
    getDepots(): Depot[] {
        return this.depots;
    }

    // Simulate POST /depots
    createDepot(depot: Depot): DepotService {
        if (!depot.id) {
            depot.id = ++this.lastId;
        }
        this.depots.push(depot);
        return this;
    }

    // Simulate GET /depots/:id
    getDepotById(id: number): Depot {
        return this.depots
            .filter(depot => depot.id === id)
            .pop();
    }

    // Simulate PUT /depots/:id
    updateDepot(id: number, values: Object = {}): Depot {
        let depot = this.getDepotById(id);
        if (!depot) {
            return null;
        }
        Object.assign(depot, values);
        return depot;
    }
}