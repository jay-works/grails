package com.bookstore

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.hibernate.HibernateSpec

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
 @SuppressWarnings(['MethodName', 'DuplicateNumberLiteral'])
@TestFor(AuthorService)
class AuthorServiceSpec extends HibernateSpec  {

    def setup() {
        def book1 = new Book(title: "IT",isbn:'123456',published:new Date())
        def book2 = new Book(title: "IT",isbn:'123456',published:new Date())
        Author author = new Author(firstName:'A',middleInitial:'B',lastName:'C',dateOfBirth:new Date(),gender:'male',email:'j@abc.com')
       /* author.address.address1='XYZ'
        author.address.address2='ABC'
        author.address.city='CHN'
        author.address.state='TN'
        author.addBook(book1)
        author.addBook(book2)*/
    }

    def cleanup() {
    }

    void "test Save Author"() {
        given:
        setup()
        def authorService = mockFor(AuthorService)

        when: "Save author"
            authorService.save(author)

        then: "Record Saved"
            Author.count() == 1
    }
}
