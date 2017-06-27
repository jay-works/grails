package com.bookstore

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Book)
class BookSpec extends Specification {

    def book
    def pubdate 
    def setup() {
        pubdate  = new Date()
        book = new Book(title:'A', isbn:'B',published:pubdate)
    }

    def cleanup() {
        book=null
    }

    //Create the following method first before creating domain model, if TDD is followed
    void "test book has neccessary fields"() {
        given:
            setup()
        expect: "neccessary fields are validated"
            book.title == 'A'
            book.isbn == 'B'
            book.published == pubdate
    }

    void 'test maxlength for all the fields in book'() {
       //title
        when: 'title for a string of 101 characters '
        book.title =  'a' * 101

        then: 'title validation fails'
        !book.validate(['title'])
        book.errors['title'].code == 'maxSize.exceeded'

        when: 'title for a string of 55 characters'
        book.title = 'a' * 55

        then: 'title validation passes'
        book.validate(['title'])

        //isbn
        when: 'isbn for a string of 101 characters '
        book.isbn =  'a' * 101

        then: 'isbn validation fails'
        !book.validate(['isbn'])
        book.errors['isbn'].code == 'maxSize.exceeded'

        when: 'isbn for a string of 100 characters'
        book.isbn = 'a' * 100

        then: 'isbn validation passes'
        book.validate(['isbn'])
    }

    void 'test title cannot be null'() {
        when:
        book.title = null

        then:
        !book.validate(['title'])
        book.errors['title'].code == 'nullable'
    }

    void 'test isbn cannot be null'() {
        when:
        book.isbn = null

        then:
        !book.validate(['isbn'])
        book.errors['isbn'].code == 'nullable'
    }
}

