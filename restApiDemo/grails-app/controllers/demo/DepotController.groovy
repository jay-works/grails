package demo


import grails.rest.*
import grails.converters.*

class DepotController extends RestfulController {
    static responseFormats = ['json', 'xml']
    DepotController() {
        super(Depot)
    }
}
