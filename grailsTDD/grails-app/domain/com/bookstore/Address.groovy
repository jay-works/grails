package com.bookstore

class Address {
    String address1
    String address2
    String city
    String state
    static constraints = {
        address1(maxSize:55)
        address2(maxSize:55, nullable:true)
        city(maxSize:30)
        state(maxSize:30)
    }
}
