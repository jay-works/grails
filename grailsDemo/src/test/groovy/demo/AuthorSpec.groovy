package demo

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Author)
class AuthorSpec extends Specification {

    def author
    def setup() {
        author = new Author(firstname: 'A', lastname: 'B')
    }

    def cleanup() {
        author=null
    }

    void "test author has neccessary fields"() {
        given:
            setup()
        expect: "neccessary fields are validated"
            author.firstname == 'A'
            author.lastname == 'B'
    }

    void 'test maxlength for all the fields in author'() {
       //firstname
        when: 'firstname for a string of 21 characters '
        author.firstname =  'a' * 21

        then: 'firstname validation fails'
        !author.validate(['firstname'])
        author.errors['firstname'].code == 'maxSize.exceeded'

        when: 'firstname for a string of 20 characters'
        author.firstname = 'a' * 20

        then: 'firstname validation passes'
        author.validate(['firstname'])

        //lastname
        when: 'lastname for a string of 21 characters '
        author.lastname =  'a' * 21

        then: 'lastname validation fails'
        !author.validate(['lastname'])
        author.errors['lastname'].code == 'maxSize.exceeded'

        when: 'lastname for a string of 20 characters'
        author.lastname = 'a' * 20

        then: 'lastname validation passes'
        author.validate(['lastname'])
    }
    
    void 'test null constraints for all fields in author'() {
        //firstname
        when:
        author.firstname = null

        then:
        !author.validate(['firstname'])
        author.errors['firstname'].code == 'nullable'

        //lastname
        when:
        author.lastname = null

        then:
        !author.validate(['lastname'])
        author.errors['lastname'].code == 'nullable'
    }

    def "find author by firstname and lastname"() {
        setup:
        mockDomain(Author)

        when:
        new Author(firstname: firstname, lastname: lastname, email:email).save()

        then:
        Author.findByFirstnameAndLastname(firstname, lastname) != null

        where:
        firstname = "John"
        lastname = "Doe"
        email = "john@gmail.com"
    }

    

}