package demo

class Author {

    String firstname
    String lastname
    String email
    static hasMany = [books:Book]

    static constraints = {
        firstname(blank:false,maxSize:20)
        lastname(blank:false,maxSize:20)
        email(email: true)
    }

    String toString(){
        "$firstname $lastname ($id)"
    }
}
