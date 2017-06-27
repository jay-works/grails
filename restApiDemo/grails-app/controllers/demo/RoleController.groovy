package demo


import grails.rest.*
import grails.converters.*

class RoleController extends RestfulController {
    static responseFormats = ['json', 'xml']
    RoleController() {
        super(Role)
    }
}
