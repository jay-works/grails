package demo

class Book {

    String title

    static belongsTo = [author: Author]

    static constraints = {
        title(blank:false,maxSize: 20)
    }

    String toString(){
        "$title"
    }
}
