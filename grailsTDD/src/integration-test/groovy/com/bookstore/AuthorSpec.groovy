package com.bookstore


import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

@SuppressWarnings('MethodName')
@Rollback
@Integration
class AuthorSpec extends Specification {
    @Autowired
    AuthorService service

        void 'test save author'() {
            when:
            def author = new Author(firstName:'A',middleInitial:'B',lastName:'C',dateOfBirth:new Date(),gender:'male',email:'j@abc.com')
            [
                    [title: 'Grails 1', isbn: '111',published: new DateTime(1970, 12, 31)],
                    [title: 'Grails 2', isbn: '222',published: new DateTime(1980, 12, 31)],
                    [title: 'Grails 3', isbn: '333',published: new DateTime(1990, 12, 31)],
            ].each {
                author.addToBook(new Book(title: it.title, isbn: it.isbn,published:it.published))
            }
            //author.save()
            author = service.save(author)
            then:
            Author.count() == 1
            Book.count() == 3
        }
}
