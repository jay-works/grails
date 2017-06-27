package com.bookstore

class Author {
    String firstName
    String middleInitial
    String lastName
    Date dateOfBirth
    String gender
    String email
    Address address
    static hasMany = [book: Book]

    static constraints = {
        firstName(nullable: false, minSize: 1, maxSize: 25, blank: false)
        middleInitial(nullable: true, maxSize: 1)
        lastName(nullable: false, minSize: 1, maxSize: 35, blank: false)
        gender(blank: false, nullable: false, maxSize: 6, inList: ['male', 'female'])
        email(unique:true,email: true,nullable: false,blank: false, notEqual: "bill@microsoft.com")
        address(nullable: true)
        book(nullable: true)
    }
}
