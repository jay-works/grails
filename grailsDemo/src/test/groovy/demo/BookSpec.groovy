package demo

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Book)
class BookSpec extends Specification {

        def book
        def setup() {
            book = new Book(title: 'Grails Unleashed',author:new Author(firstname:"John",lastname:"Doe",email:'john@gmail.com'))
        }

        def cleanup() {
            book=null
        }

        def "find book by title"() {
            setup:
            mockDomain(Book)
            mockDomain(Author)

            when:
            new Book(title: title,author:new Author(firstname:"John",lastname:"Doe",email:'john@gmail.com').save()).save()

            then:
            Book.findByTitle(title) != null

            where:
            title = "Nice book"
        }

        def "title not longer than 20 characters"() {
            setup:
            mockForConstraintsTests(Book)
            mockDomain(Author)

            when:
            def book =new Book(title:title, author:new Author(firstname:"John",lastname:"Doe",email:'john@gmail.com').save())
            !book.validate()

            then:
            //book.errors.hasFieldErrors("title")
            book.errors['title'].code == 'maxSize.exceeded'

            where:
            title="123456789012345678901"
        }

        void 'test maxlength for title in book'() {
            //title
            when: 'title for a string of 21 characters '
            book.title =  'a' * 21

            then: 'title validation fails'
            !book.validate(['title'])
            book.errors['title'].code == 'maxSize.exceeded'

            when: 'title for a string of 20 characters'
            book.title = 'a' * 20

            then: 'title validation passes'
            book.validate(['title'])
        }
            
        def "title not blank"() {
            setup:
            mockForConstraintsTests(Book)
            mockDomain(Author)

            when:
            def book =new Book(title:title, author:new Author(firstname:"John",lastname:"Doe",email:'john@gmail.com').save())
            !book.validate()

            then:
            book.errors.hasFieldErrors("title")

            where:
            title=""
        }

        def "author not blank"() {
            setup:
            mockForConstraintsTests(Book)
            mockDomain(Author)

            when:
            def book =new Book(title:"nice title", author:null)
            !book.validate()

            then:
            book.errors.hasFieldErrors("author")
        }

    }
