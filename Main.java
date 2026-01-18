package trabalhoprog;  //Flower this is the name of the directory where I'm running the code it may be diferent for you
import java.util.Scanner;

public class Main {
  static Users user = new Users("no_ussername", "no_password", "no_name", true, "not_defined"); 

  public static void main(String[] args){
    //Flower: debugging things
	  System.out.println("Working directory: " + System.getProperty("user.dir"));

	    
	  if(!FileHandler.checkadm()){
      Scanner input = new Scanner(System.in);

      System.out.println("Please choose what you want to do: " + "\n" + "1- loggin" + "\n" + "2- Sign in");
      int choice = input.nextInt();

      if(choice == 1){
        login();
      } else if(choice == 2){
		    Sign_up();
      }

      input.close();

      on_exit();
    } else {
      System.out.println("No admin found please create on adm");
      //Flower
      //TODO: create a create adm function
		  Admin.create_admin();
    }
  }

  private static void on_exit(){
    //find a way to get username
    System.out.println("Adeus " + user.return_user());
  }

  private static void login(){
    Scanner input = new Scanner(System.in);
    //FileHandler fileHandler = new FileHandler();

    /*TODO: Flower: this is only temporary, and only aplies to users, like, you cant
            choose if you want to be logged in as admin or user or etc...
    */

	  //Flower
	  //TODO: Precisamos de traduzir todos os System.out.println que temos bro
    System.out.println("Please insert your username: ");
    String username = input.nextLine();

    System.out.println("Please insert your password: ");
    String password = input.nextLine();

    if(FileHandler.login(username, password)){
      System.out.println("Bem vindo " + username);
      
      FileHandler.loadUser(user);

      switch((user.return_type()).trim()){
        case("adm"):
          admin_loop();
          break;
        case("clientes"):
          user_loop();
          break;
        case("technical"):
          break;
      }
	  } else {
      System.out.println("The loggin was not succesfull");
    }

    input.close();
  }
  
  private static void user_loop(){
    System.out.println("on clients loop");
  }

  private static void admin_loop(){
    Admin current = new Admin(user);
    
    boolean current_run = true; 
    while(current_run){
      int choice;
      Scanner input = new Scanner(System.in);

      System.out.println("what would you like to do: " + "\n" + "1- See sign up requests" + "\n" + "2- exit");
      choice = input.nextInt();

      switch(choice){
        case(1):
          current.seeSignUpRequests();
          break;
        case(2):
          current_run = false;
          break;
      }

    }
  }

  private static void Sign_up(){
    /*FLower
      TODO: pass this to user class
    */
    Scanner input = new Scanner(System.in);
    //FileHandler fileHandler = new FileHandler();
    user.setType("user");

    /*Flower
     TODO: theres this little errors but it works + part of this code is repeated from the loggin, maybe we can do smth about this
    */

    System.out.println("Please insert your username: ");
    String username = input.nextLine();

    System.out.println("Please insert your password: ");
    String password = input.nextLine();

    user.setUsername(username);
    user.setPassword(password);
    FileHandler.doUserRequest(user);
  }

}
