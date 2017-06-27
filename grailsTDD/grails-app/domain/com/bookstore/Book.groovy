package com.bookstore

class Book {
    String title
    String isbn
    Date published
   
    static belongsTo = [author: Author]
    static constraints = {
        title(maxSize:100, nullable:false)
        isbn(maxSize:100, nullable:false)
    }
}
