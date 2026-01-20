package trabalhoprog;

import java.util.Scanner;
import java.util.Vector;

public class Admin extends Users{
    public Admin(Users user){
        super(user.username, user.password, user.name, user.state, user.type);
    }
    
    public boolean setType(String aType, String aUsername){
        if(aType.equals("Admin")){//Rodrigo: Tem que se ver a melhor opção a usar, supostamente só os admins podem alterar o tipo e tem que se pesquisar o username da pessoa a quem ele quer alterar
            type = aType;         //Flower: what....?
            return true;
        }
        return false;
    }

    public static void create_admin(dados data, int mode){
      Scanner input = new Scanner(System.in);

      System.out.println("Por favor insira o nome de utilizador");
      String username = input.nextLine();

      System.out.println("Por favor insira a password");
      String password = input.nextLine();

      System.out.println("Por favor nome");
      String nome = input.nextLine();
      
      //TODO: fix the estado thing so when the user logs in it turns to true, and when he leaves the app it turns to false
      //and all of other file
      Boolean estado = true;

      //=====================
      String type = "admin";
      //==========================
      input.close();

      Users user = new Users(username, password, nome, estado, type);  
      Admin admin = new Admin(user);
      
      if(mode == 1){ //no users were found so creating admin by default
        data.add_User(admin);
        FileHandler.saveCredentials(username, password);
        //return admin;
      } else {
        data.make_request(admin);
      }
    }

    public void seeSignUpRequests(dados data){
      Vector<Users> requests = data.return_user_requests();
      //Flower
      //Here we print like... all of the User's infor or....?
      //like for simplicity's sake I'm just fonna print the name and like type ig but we deff gotta decide this
      Users user;
      for(int i = 0; i < requests.size(); i++){
        user = requests.get(i);
        System.out.println("pedido " + i + " - username: " + user.return_user() + " | tipo: " + user.return_type() + "\n");

      }
      Scanner input = new Scanner(System.in);
      System.out.println("Por favor escolha um pedido para aceitar");
      int choice = input.nextInt();

      user = requests.get(choice);
      FileHandler.saveCredentials(user.return_user(), user.return_password());
      data.remove_request(choice);
      data.add_User(user);

      input.close();
    }
  /*
  public void seeSignUpRequests(){
    //read file of SIgnUpRequests
    //numerate them and send them
    Vector<String> current_requests = FileHandler.return_userrequests();
    for(int i = 0; i < current_requests.size(); i++){
      String request = current_requests.get(i); 
      String[] data = request.split(",");
      System.out.println("request " + i + "- name: " + data[0] + " password: " + data[1]);
    }
    
    Scanner reader = new Scanner(System.in);
    System.out.println("que utilizador quer aprovar");
    int choice = reader.nextInt();
    
    String request_to_accept = current_requests.get(choice);
    String data[] = request_to_accept.split(",");

    Users user_to_accept = new Users(data[0], data[1], "no_name", false, data[2]);
    FileHandler.saveUser(user_to_accept);
    FileHandler.removeUser(choice);
  } 

  public void associateTecnitians(){
    Scanner choice = new Scanner(System.in);
    System.out.println("Escolhe um tecnico");
    Vector<String> tecnicos = FileHandler.return_tecnicos();
    int choice_tecntitian = choice.nextInt();
    
    String[] tecnical_choice = (tecnicos.get(choice_tecntitian)).split(",");

    Technical tecnico = new Technical(tecnical_choice[0], tecnical_choice[1], "no_name", false, tecnical_choice[2], 123, 456, "no_adress");
    
    System.out.println("Escolha um servico para assoiar o tecnico");
    Vector<String> services = FileHandler.return_services();
    int choice_service = choice.nextInt();

    String[] service_choice = (services.get(choice_service)).split(",");
    Services serviso = new Services(123, 4, "aproved");
    serviso.set_Tecnico(tecnico);
    
    FileHandler.remove_service(choice_service);
    FileHandler.addService(serviso);
    //TODO: make a function to update re-write the service's data on the services txt file with the new tecnico
  }
  */
}
