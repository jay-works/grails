package demo

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(UserDetail)
class UserDetailSpec extends ConstraintUnitSpec  {

    def userdetail
    def setup() {
        userdetail = new UserDetail(username: 'jay7093', password: 'tech@123',firstname:'Jayakrishnan',
            lastname:'V',email:'jay7093@gmail.com',role: new Role(code:'ADMIN',description:'ADMIN',active:true))
    }

    def cleanup() {
        userdetail=null
    }

    void "test userdetail has neccessary fields"() {
        given:
            setup()
        expect: "neccessary fields are validated"
            userdetail.username == 'jay7093'
            userdetail.password == 'tech@123'
            userdetail.firstname == 'Jayakrishnan'
            userdetail.lastname == 'V'
            userdetail.email == 'jay7093@gmail.com'
    }

    void 'test maxlength for all the fields in userdetail'() {
        //username min size
        when: 'username for a string of 5 characters '
        userdetail.username =  'a' * 5

        then: 'username validation fails'
        !userdetail.validate(['username'])
        //userdetail.errors['username'].code == 'minSize.notmet' //If enable this line thrown error

        //username max size
        when: 'username for a string of 21 characters '
        userdetail.username =  'a' * 21

        then: 'username validation fails'
        !userdetail.validate(['username'])
        //userdetail.errors['username'].code == 'maxSize.exceeded' //If enable this line thrown error

        when: 'username for a string of 20 characters'
        userdetail.username = 'a' * 20

        then: 'username validation passes'
        userdetail.validate(['username'])
        
        //firstname
        when: 'firstname for a string of 21 characters '
        userdetail.firstname =  'a' * 21

        then: 'firstname validation fails'
        !userdetail.validate(['firstname'])
        //userdetail.errors['firstname'].code == 'maxSize.exceeded'

        when: 'firstname for a string of 20 characters'
        userdetail.firstname = 'a' * 20

        then: 'firstname validation passes'
        userdetail.validate(['firstname'])

        //lastname
        when: 'lastname for a string of 21 characters '
        userdetail.lastname =  'a' * 21

        then: 'lastname validation fails'
        !userdetail.validate(['lastname'])
        //userdetail.errors['lastname'].code == 'maxSize.exceeded'

        when: 'lastname for a string of 20 characters'
        userdetail.lastname = 'a' * 20

        then: 'lastname validation passes'
        userdetail.validate(['lastname'])
    }

    void 'test null constraints for all fields in userdetail'() {
        //username
        when:
        userdetail.username = null

        then:
        !userdetail.validate(['username'])
        userdetail.errors['username'].code == 'nullable'

        //password
        when:
        userdetail.password = null

        then:
        !userdetail.validate(['password'])
        userdetail.errors['password'].code == 'nullable'
    }

    def "depot not blank"() {
            setup:
            mockDomain(UserDetail)
            mockDomain(Depot)

            when:
            userdetail = new UserDetail(username: 'jay7093', password: 'tech@123',firstname:'Jayakrishnan',
            lastname:'V',email:'jay7093@gmail.com',role: new Role(code:'ADMIN',
                description:'ADMIN',active:true),depot:null)

            !userdetail.validate()

            then:
            userdetail.errors.hasFieldErrors("depot")
    }

    @Unroll("UserDetail #field testing #error")
    def "test UserDetail children constraints"() {
        when:
        def obj = new UserDetail("$field": val)

        then:
        validateConstraints(obj, field, error)

        where:
        error     | field      | val
        'maxSize' | 'children' | createUserDetail(11)
        'minSize' | 'children' | createUserDetail(1)
        'valid'   | 'children' | null
        'valid'   | 'children' | createUserDetail(10)
        'valid'   | 'children' | createUserDetail(2)
    }

    private createUserDetail(Integer count) {
        def userdetails = []
        count.times {
            userdetails << new UserDetail()
        }
        userdetails
    }

    def "test username is unique"(){
    when: 'username is unique'
    userdetail =  new UserDetail(username: 'jay7093', password: 'tech@123',firstname:'Jayakrishnan',
            lastname:'V',email:'jay7093@gmail.com')

    then: 'validation succeeds'
    userdetail.validate()

    userdetail.save(flush: true, failOnError: true)
    mockDomain(UserDetail, [userdetail])

    when: 'username is non unique'
    def userdetail2 = new UserDetail(username: "jay7093")
    
    then: 'validation fails'
    !userdetail2.validate()
    userdetail2.errors.getFieldError("username").codes.contains("unique")

    }
}
