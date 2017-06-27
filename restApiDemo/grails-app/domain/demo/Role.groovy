package demo
import grails.databinding.BindingFormat

class Role {
    String code
    String description
    @BindingFormat('dd-MMM-yyyy')  
    Date dateCreated 
    Date lastUpdated  
    String modifiedBy
    String createdBy
    Boolean active
    static hasMany = [users:UserDetail]
    static constraints = {
         code nullable: false, unique: true,size:0..20
         description nullable: false, size:0..100
         modifiedBy nullable: true,size:0..20
         createdBy nullable:true,size:0..20		
    }   
}
