package trabalhoprog;  //Flower this is the name of the directory where I'm running the code it may be diferent for you
import java.util.Scanner;

public class Main {
  static Users user = new Users("no_ussername", "no_password", "no_name", true, "not_defined"); 

  dados data = new Dados();

  public static void main(String[] args){
    //Flower: debugging things
	  System.out.println("Working directory: " + System.getProperty("user.dir"));
      
    data = FileHandler.load_data_file();
	  
    if(data.return_user_n() == 0){
      //there were no users created
      System.out.println("Nao foram encontrados utilizadores, por favor crie um utilizador administrador");
      Admin.create_admin(data);
      //TODO: should we just pass to the adm loop or??
      //admin_loop(Admin.create_admin(data));
    } else {
      //Users were found
      //procede as normal
      login();
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
      
      Users user = dados.return_user(username, password);
      if(user.return_type().equals("adm")){
        Admin admin = (Admin) user; //trust me bro
        admin_loop(admin);

      }else if(user.return_type().equals("clients")){
        Client client = (Client) user;
        user_loop(client);

      }else if(user.return_type().equals("tecnico")){
        Thechnical tecnico = (Thechnical) user;
        //TODO: finish this section
        //
      }

	  } else {
      System.out.println("The loggin was not succesfull");
    }

    input.close();
  }
  
  private static void user_loop(Client current){
    System.out.println("on clients loop");
  }

  private static void admin_loop(Admin current){
    boolean current_run = true; 
    while(current_run){
      int choice;
      Scanner input = new Scanner(System.in);

      System.out.println("what would you like to do: " + "\n" + "1- See sign up requests" + "\n" + "2- associar Tecnico a Servico" + "\n" + "9- exit");
      choice = input.nextInt();

      switch(choice){
        case(1):
          current.seeSignUpRequests();
          break;
        case(2):
          current.associateTecnitians();
          break;
        case(9):
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
