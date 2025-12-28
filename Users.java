package trabalhoprog;

//RF3, 
public class Users {
    private String username;
    protected String password;
    protected String name; 
    private String email;
    protected boolean state; //(online/offline)
    protected String type; //(adm/clients/technical)
     
    public Users(String aUsername, String aPassword, String aName, Boolean aState, String aType){
        username = aUsername;
        password = aPassword;
        name = aName;
        state = aState;
        type = aType;
    }
    public void setUsermame(String aUsername){
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
    //Flower: idk

    public String toFileString() { //Flower: this is super insecure so.....
        return username + "," + password;
    }

    public String return_user(){
    	return username;
    }
}
