package com.bookstore

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Address)
class AddressSpec extends Specification {

    def address
    def setup() {
        address = new Address(address1:'A', address2:'B',city:'C', state:'D')
    }

    def cleanup() {
        address=null
    }

    //Create the following method first before creating domain model, if TDD is followed
    void "test address has neccessary fields"() {
        given:
            setup()
        expect: "neccessary fields are validated"
            address.address1 == 'A'
            address.address2 == 'B'
            address.city == 'C'
            address.state == 'D'
    }

    void 'test maxlength for all the fields in address'() {

        //address1
        when: 'address1 for a string of 56 characters '
        address.address1 =  'a' * 56

        then: 'address1 validation fails'
        !address.validate(['address1'])
        address.errors['address1'].code == 'maxSize.exceeded'

        when: 'address1 for a string of 55 characters'
        address.address1 = 'a' * 55

        then: 'address1 validation passes'
        address.validate(['address1'])

        //address2
        when: 'address2 for a string of 56 characters '
        address.address2 =  'a' * 56

        then: 'address2 validation fails'
        !address.validate(['address2'])
        address.errors['address2'].code == 'maxSize.exceeded'

        when: 'address2 for a string of 55 characters'
        address.address2 = 'a' * 55

        then: 'address2 validation passes'
        address.validate(['address2'])

        //city
        when: 'city for a string of 31 characters '
        address.city =  'a' * 31
        
        then: 'city validation fails'
        !address.validate(['city'])
        address.errors['city'].code == 'maxSize.exceeded'

        when: 'city for a string of 30 characters'
        address.city = 'a' *30

        then: 'city validation passes'
        address.validate(['city'])

        //state
        when: 'state for a string of 31 characters '
        address.state =  'a' * 31
        
        then: 'state validation fails'
        !address.validate(['state'])
        address.errors['state'].code == 'maxSize.exceeded'

        when: 'state for a string of 30 characters'
        address.state = 'a' *30

        then: 'state validation passes'
        address.validate(['state'])
    }

}
