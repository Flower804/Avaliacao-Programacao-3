package trabalhoprog;

//RF3,
//
import java.io.Serializable;

public class Users implements Serializable{
    protected String username;
    protected String password;
    protected String name; 
    protected String email;
    protected boolean state; //(online/offline)
    protected String type; //(adm/clients/technical)
    
    public Users(String aUsername, String aPassword, String aName, Boolean aState, String aType){
        username = aUsername;
        password = aPassword;
        name = aName;
        state = aState;
        type = aType;
	  } 

    public void setUsername(String aUsername){
        username = aUsername;
    }
    public void setPassword(String aPassword){
        password = aPassword;
    }
    public void setName(String aName){
        name = aName;
    }
    public void setEmail(String aEmail){
        email = aEmail;
    }
    //Rodr: Acho que não se pode alterar o tipo, apenas admins podem.
    //Flower: I need it so gonna create it 
    public void setType(String atype){
      type = atype;
    }

    public String toFileString() { //Flower: this is super insecure so.....
        return username + "," + password + ',' + type;
    }

    public String return_user(){
    	return username;
    }
    
    public String return_password(){
      return password;
    }

    public String return_type(){
      return type;
    }
}
