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

  public void associateTecnitians(dados data){
    Vector<Technical> tecnicos = data.return_tecnicos();
    Scanner input = new Scanner(System.in);

    Technical tecnico;
    System.out.println("escolha um tecnico para associar a um servico \n");
    for(int i = 0; i < tecnicos.size(); i++){
      tecnico = tecnicos.get(i);
      
      //TODO: add more info to the print if necessary
      System.out.println("tecnico " + i + "- nome: " + tecnico.return_user() + "\n");
    }
    int choice = input.nextInt();
    tecnico = tecnicos.get(choice);

    Vector<Services> services = data.return_services();
    //Flower: 
    //like ok same question as in the method before like... what exactly do we print?
    //TODO: RODRIGO VE AS MENSAGENS QUE TE MANDEI A PERGUNTAR SOBRE A INTERPRETACAO DISTO, obrigado :)
    Services service;
    System.out.println("Escolha um servico ao tecnico " + tecnico.return_user() + " ser associado \n");
    for(int i = 0; i < services.size(); i++){
      service = services.get(i);
      
      //Flower: for simplicity sake I'll only print the tecnico's name
      tecnico = service.return_tecnico();
      //and like, I'm not sure if I should just print every attribute of service or not so.... yeah... just tale the code and tecnico
      System.out.println("servico n-" + i + ": codigo " + service.get_code() + " | tecnico responsavel: " + tecnico.return_user());
    }
    choice = input.nextInt();
    data.remove_service(choice);

    service = services.get(choice);

    service.set_Tecnico(tecnico);
    data.add_service(service);
  } 
}
