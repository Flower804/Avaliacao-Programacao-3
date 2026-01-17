package trabalhoprog;  //Flower this is the name of the directory where I'm running the code it may be diferent for you
import java.util.Scanner;

public class Main {
	private static Users user = new Users("no_ussername", "no_password", "no_name", true, "not_defined"); 

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
      // System.out.println("The loggin was succesfull");
	    //Flower:try to find out why not working later
	    //System.out.println("Bem-vindo" + Users.return_user);
      System.out.println("Bem vindo " + username);
      user.setUsermame(username);
      user.setPassword(password);
	  } else {
      System.out.println("The loggin was not succesfull");
    }

    input.close();
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

    user.setUsermame(username);
    user.setPassword(password);
    FileHandler.saveUser(user);
  }

}
