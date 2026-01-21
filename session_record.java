package trabalhoprog;

import java.io.Serializable;

public class session_record implements Serializable{
  private int session_counter = 0;
  private String last_user;

  public void record_session(){
    session_counter = session_counter + 1;
    //TODO:==debuggin========
    System.out.println("session recorded sucessfuly");
    //===========================
  }
  
  public void set_User(String user){
    last_user = user;
  }

  public int return_session_counter(){
    return session_counter;
  }

  public String return_username(){
    return last_user;
  }
}
