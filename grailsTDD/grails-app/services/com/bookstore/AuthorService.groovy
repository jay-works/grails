package com.bookstore

import grails.transaction.Transactional

@Transactional
class AuthorService {

    def serviceMethod() {

    }

     def save(data){
        println"Save starts"       
        def oAddress  
        def oSpecification 
        def oCategory 
                     
        Author author= new Author(data)  
            println"address starts"       
            data.addresss.each{address->            
                if(!address.id){                                     
                    oAddress= new Address(address)                                      
                    author.addToAddresss(oAddress)
                }                          
            }    

            println"book starts"   
            data.book.each{book->           
                if(!book.id){                            
                    oBook= new Book(book)                                      
                    author.addToBooks(oBook)
                }               
            }     
            return author  
        }
    }
