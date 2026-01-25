package trabalhoprog;  //Flower this is the name of the directory where I'm running the code it may be diferent for you
import java.util.Scanner;

public class Main {
  static private Scanner input = new Scanner(System.in);
  static private Users user = new Users("no_ussername", "no_password", "no_name", true, "not_defined"); 
  static private String csv_dir = "Documents/servicos.csv";

  static private dados data = new dados();
  static private session_record session = new session_record();

  public static void main(String[] args){
    //Flower: debugging things
	  System.out.println("Working directory: " + System.getProperty("user.dir"));

    data = FileHandler.load_data_file();
    session = FileHandler.load_last_session();
	  
    session.record_session();
    System.out.println("O programa foi executado: " + session.return_session_counter() + " - sendo o ultimo utilizador o: "+ session.return_username());

    if(data.return_user_n() == 0){
      //there were no users created
      System.out.println("Nao foram encontrados utilizadores, por favor crie um utilizador administrador");
      session.set_User(Admin.create_admin(data, 1, input));
      //TODO: should we just pass to the adm loop or??
      //admin_loop(Admin.create_admin(data));
    } else {
      //Users were found
      //procede as normal
      System.out.println("Por favor escolha o que pretende fazer \n 1- login \n 2- Registar-se \n 3- exportar servicos");
      int choice = input.nextInt();
      input.nextLine();

      if(choice == 1){
        login();
      } else if(choice == 2){
        Sign_up();
      } else if(choice == 3){
        data.create_csv_output(csv_dir);
      }
    }
    on_exit();
  }

  private static void on_exit(){
    //find a way to get username
    System.out.println("Adeus " + user.return_user());
    input.close();
    FileHandler.save_data(data);
    FileHandler.save_session(session);
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
        
        session.set_User(username);

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
            Tecnico_loop(tecnico);
          }
        }
      } else {
        System.out.println("Nao existem credenciais correspondentes ao seu pedido");
      }
    }
  }
  
  //I mean it's the client's loop but who cares with consistent and accurate method naming
  private static void user_loop(Client current){
    //System.out.println("on clients loop");
    boolean current_run = true;
    while(current_run){
      System.out.println("O que gostaria de fazer?" + "\n" + "1- executar pedido de servico" + "\n" + "2- gerir pedidos de servico \n9- sair");
      int choice = input.nextInt();
      input.nextLine();

      switch(choice){
        case(1):
          current.request_a_service(data, input);
          break;
        case(2):
          current.manage_services(data, input);
          break;
        case(9):
          current_run = false;
          break;
      }
    }
  }

  private static void admin_loop(Admin current){
    boolean current_run = true; 
    while(current_run){
      int choice;
      
      System.out.println("what would you like to do: " + "\n" + "1- See sign up requests" + "\n" + "2- Verificar pedidos de servico\n" + "3- manage services" + "9- exit");
      choice = input.nextInt();
      input.nextLine();

      switch(choice){
        case(1):
          current.seeSignUpRequests(data, input);
          break;
        case(2):
          current.accept_requests(data, input);
          //current.associateTecnitians(data, input);
          break;
        case(3):
          current.manage_services(data, input);
          break;
        case(9):
          current_run = false;
          break;
      }

    }
  }

  private static void Tecnico_loop(Technical current){
    Boolean current_run = true;
    while(current_run){
      int choice;

      System.out.println("O que pretende fazer? \n" + "1- Aceitar pedido de Servico de utilizadores \n2- intruduze data \n3- adicionar area medica \n4- manage services \n9- Sair");
      choice = input.nextInt();
      input.nextLine();

      switch(choice){
        case(1):
          current.accept_requests(data, input);
          break;
        case(2):
          current.intruduze_data(data, input);
          break;
        case(3):
          current.create_medical_area(data, input);
          break;
        case(4):
          current.manage_services(data, input);
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
      session.set_User(Client.create_Client(data, input));
    } else if(tipo == 2){
      session.set_User(Admin.create_admin(data, 2, input));
    } else if(tipo == 3){
      session.set_User(Technical.create_Tecnico(data, input));
    }
    //TODO: create else statement
  }
}
