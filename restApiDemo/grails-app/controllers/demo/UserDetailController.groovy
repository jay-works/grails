package demo


import grails.rest.*
import grails.converters.*

class UserDetailController extends RestfulController {
    static responseFormats = ['json', 'xml']
    UserDetailController() {
        super(UserDetail)
    }
}
