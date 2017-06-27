package demo

class UserDetail {
    String username
	String password
	boolean active	
	String firstname
	String lastname
	String email
	static belongsTo=[role:Role]
	Depot depot
    Date dateCreated 
    Date lastUpdated  
    String modifiedBy
    String createdBy
    static constraints = {
        username nullable: false,unique: true,size:6..20
		password nullable: false,size:8..100
		firstname nullable:true,size:0..20
		lastname nullable:true,size:0..20
		email nullable:true,email:true,size:0..50 
		depot nullable:false
		role nullable:false
    }

    static mapping = {
		password column: '`password`'
		table 'user_detail'
	}
}
