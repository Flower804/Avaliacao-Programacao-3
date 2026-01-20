package trabalhoprog;  //Flower this is the name of the directory where I'm running the code it may be diferent for you
import java.util.Scanner;

public class Main {
  static Users user = new Users("no_ussername", "no_password", "no_name", true, "not_defined"); 

  static dados data = new dados();

  public static void main(String[] args){
    //Flower: debugging things
	  System.out.println("Working directory: " + System.getProperty("user.dir"));
      
    data = FileHandler.load_data_file();
	  
    if(data.return_user_n() == 0){
      //there were no users created
      System.out.println("Nao foram encontrados utilizadores, por favor crie um utilizador administrador");
      Admin.create_admin(data, 1);
      //TODO: should we just pass to the adm loop or??
      //admin_loop(Admin.create_admin(data));
    } else {
      //Users were found
      //procede as normal
      Scanner input = new Scanner(System.in);
      System.out.println("Por favor escolha o que pretende fazer \n 1- login \n 2- Registar-se");
      int choice = input.nextInt();
      
      input.close();
      if(choice == 1){
        login();
      } else if(choice == 2){
        Sign_up();
      }
    }
  }

  private static void on_exit(){
    //find a way to get username
    System.out.println("Adeus " + user.return_user());
  }

  private static void login(){
    Scanner input = new Scanner(System.in);
    
	  //Flower
	  //TODO: Precisamos de traduzir todos os System.out.println que temos bro
    System.out.println("Please insert your username: ");
    String username = input.nextLine();

    System.out.println("Please insert your password: ");
    String password = input.nextLine();
    
    input.close();
    if(FileHandler.login(username, password)){
      System.out.println("Bem vindo " + username);
      
      Users user = data.return_user(username, password);
      if(user.return_type().equals("adm")){
        Admin admin = (Admin) user; //trust me bro
        admin_loop(admin);

      }else if(user.return_type().equals("clients")){
        Client client = (Client) user;
        user_loop(client);

      }else if(user.return_type().equals("tecnico")){
        Technical tecnico = (Technical) user;
        //TODO: finish this section
        //
      }

	  } else {
      System.out.println("The loggin was not succesfull");
    }
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
      
      input.close();
      switch(choice){
        case(1):
          current.seeSignUpRequests(data);
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
    Scanner input = new Scanner(System.in);
    System.out.println("Por favor escolha que tipo de utilizador pretende criar \n 1- cliente \n 2- Administrador \n 3- Tecnico");
    int tipo = input.nextInt();

    input.close();

    if(tipo == 1){
      Admin.create_admin(data, 2);
    } else if(tipo == 2){
      Client.create_Client(data);
    } else if(tipo == 3){
      Technical.create_Tecnico(data);
    }
    //TODO: create else statement
  }
}
