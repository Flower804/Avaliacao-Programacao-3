package trabalhoprog;  //Flower this is the name of the directory where I'm running the code it may be diferent for you
import java.util.Scanner;

public class Main {
  static private Scanner input = new Scanner(System.in);
  static private Users user = new Users("no_ussername", "no_password", "no_name", true, "not_defined"); 

  static private dados data = new dados();

  public static void main(String[] args){
    //Flower: debugging things
	  System.out.println("Working directory: " + System.getProperty("user.dir"));

    data = FileHandler.load_data_file();
	  
    if(data.return_user_n() == 0){
      //there were no users created
      System.out.println("Nao foram encontrados utilizadores, por favor crie um utilizador administrador");
      Admin.create_admin(data, 1, input);
      //TODO: should we just pass to the adm loop or??
      //admin_loop(Admin.create_admin(data));
    } else {
      //Users were found
      //procede as normal
      System.out.println("Por favor escolha o que pretende fazer \n 1- login \n 2- Registar-se");
      int choice = input.nextInt();
      input.nextLine();

      if(choice == 1){
        login();
      } else if(choice == 2){
        Sign_up();
      }
    }
    on_exit(data);
  }

  private static void on_exit(dados data){
    //find a way to get username
    System.out.println("Adeus " + user.return_user());
    input.close();
    FileHandler.save_data(data);
  }

  private static void login(){
    Boolean loggin = true;

    while(loggin){
	    //Flower
	    //TODO: Precisamos de traduzir todos os System.out.println que temos bro
      System.out.println("Please insert your username: ");
      String username = input.nextLine();

      System.out.println("Please insert your password: ");
      String password = input.nextLine();
    
      if(FileHandler.login(username, password)){
        loggin = false;
        System.out.println("Bem vindo " + username);
      
        Users user = data.return_user(username, password);
        //TODO: Flower TEMP FIX
        //This should never happen because logically if the credentials exist the user should exist too, but for some god ass reason it's happening
        if(user == null){
          System.out.println("ups, um erro aconteceu");
        } else {
          if(user.return_type().equals("admin")){
            Admin admin = (Admin) user; //trust me bro
            System.out.println("utilizador identificado com sucesso como admin");
            admin_loop(admin);

          }else if(user.return_type().equals("clients")){
            Client client = (Client) user;
            user_loop(client);

          }else if(user.return_type().equals("tecnico")){
            Technical tecnico = (Technical) user;
            //TODO: finish this section
            //
          }
        }
      } else {
        System.out.println("Nao existem credenciais correspondentes ao seu pedido");
      }
    }
  }
  
  private static void user_loop(Client current){
    System.out.println("on clients loop");
  }

  private static void admin_loop(Admin current){
    boolean current_run = true; 
    while(current_run){
      int choice;
      
      System.out.println("what would you like to do: " + "\n" + "1- See sign up requests" + "\n" + "2- associar Tecnico a Servico" + "\n" + "9- exit");
      choice = input.nextInt();
      input.nextLine();

      switch(choice){
        case(1):
          current.seeSignUpRequests(data, input);
          break;
        case(2):
          current.associateTecnitians(data, input);
          break;
        case(9):
          current_run = false;
          break;
      }

    }
  }

  private static void Sign_up(){
    System.out.println("Por favor escolha que tipo de utilizador pretende criar \n 1- cliente \n 2- Administrador \n 3- Tecnico");
    int tipo = input.nextInt();
    input.nextLine();

    if(tipo == 1){
      Client.create_Client(data, input);
    } else if(tipo == 2){
      Admin.create_admin(data, 2, input);
    } else if(tipo == 3){
      Technical.create_Tecnico(data, input);
    }
    //TODO: create else statement
  }
}
