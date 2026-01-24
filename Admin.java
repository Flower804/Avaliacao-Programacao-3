package trabalhoprog;

import java.util.Scanner;
import java.util.Vector;
import java.util.Iterator;

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

  public static String create_admin(dados data, int mode, Scanner input){
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
    Users user = new Users(username, password, nome, estado, type);  
    Admin admin = new Admin(user);
    
    if(mode == 1){ //no users were found so creating admin by default
      data.add_User(admin);
      FileHandler.saveCredentials(username, password);
      //return admin;
    } else {
      data.make_request(admin);
    }
    return username;
  }

  public void seeSignUpRequests(dados data, Scanner input){
    Vector<Users> requests = data.return_user_requests();
    
    if(requests.size() == 0){
      System.out.println("Nao ha pedidos de Registo neste momento");
    } else {
      //Flower
      //Here we print like... all of the User's infor or....?
      //like for simplicity's sake I'm just fonna print the name and like type ig but we deff gotta decide this
      Users user;
      for(int i = 0; i < requests.size(); i++){
        user = requests.get(i);
        System.out.println("pedido " + i + " - username: " + user.return_user() + " | tipo: " + user.return_type() + "\n");

      }
      System.out.println("Por favor escolha um pedido para aceitar");
      int choice = input.nextInt();
      input.nextLine();

      user = requests.get(choice);
      FileHandler.saveCredentials(user.return_user(), user.return_password());
      data.remove_request(choice);
      data.add_User(user);
    }
  }

  public void accept_requests(dados data, Scanner input){
    //Flower
    //this is basically just like copied from Technical.java, because like R13 says that tecnicos must accept the service,
    //but then R18 and R20 says that the Admin must accept, idk bro I just did both out of confusion
    Vector<Services> services = data.return_service_request();
    
    if(services.size() == 0){
      System.out.println("Nao ha nenhum servico sem tecnico responsavel neste momento");
    } else {
      System.out.println("Escolha qual servico selecionar");

      Services service;
      for(int i = 0; i < services.size(); i++){
        service = services.get(i);
        System.out.println("service " + i + ":" + " codigo:" + service.get_code());
      }
      int choice = input.nextInt();
      input.nextLine();
    
      service = services.get(choice);
      System.out.println();
      System.out.println("Escolha o que fazer: 1- Aprovar 2- Rejeitar");
      int decition = input.nextInt();
      input.nextLine();
    
      int result;
      if(decition == 1){
        service.setState("aprovado");
        result = associateTecnitians(data, input, service);
      } else {
        service.setState("rejeitado");
        result = 1; 
      }
    
      if(result == 1){ //tecnico finished without issue 
        data.remove_service_request(choice);
        data.add_service(service);
      } else {
        System.out.println("Desculpe nao conseguimos processar o servico, progresso nao salvo");
      }
    }
  }
  
  //Flower
  //R11 and R18 are like the same?? continuation???? 
  //like I'm just gonna make it a continuation.....
  public int associateTecnitians(dados data, Scanner input, Services service){
    Vector<Technical> tecnicos = data.return_tecnicos();
    
    if(tecnicos.size() == 0){
      System.out.println("Nao ha nenhum tecnico disponivel");

      return 0;
    } else {
      Technical tecnico;
      System.out.println("escolha um tecnico para associar ao servico \n");
      for(int i = 0; i < tecnicos.size(); i++){
        tecnico = tecnicos.get(i);
      
        //TODO: add more info to the print if necessary
        System.out.println("tecnico " + i + "- nome: " + tecnico.return_user() + "\n");
      }
      int choice = input.nextInt();
      input.nextLine();
      tecnico = tecnicos.get(choice);
      service.set_Tecnico(tecnico);

      return 1;
    }
  }

  public void manage_services(dados data, Scanner  input){
    Vector<Services> current_services = data.return_services();

    System.out.println("selecione qual servico pertende gerir - ou selecione 9 para sair");
    Iterator<Services> it = current_services.iterator();
    int counter = 0;
    while(it.hasNext()){
      counter++;
      Services service = it.next();
      
      //TODO: finish puting everything Here
      System.out.println("servico n" + counter + "- codigo: " + service.get_code());
    }
    int choice = input.nextInt();
    input.nextLine();
    
    if(choice == 9){
      System.out.println("Escolheu nao gerir nenhum sevico");
      //exits
    } else {
      Services service = current_services.get(choice);
      
      System.out.println("O que pretende editar?");
      System.out.println("1-Lista de analises: " + "[inserir a a lista de analises] " + " 2-valor total de servico" + "etc..." + " 0- sair");
      choice = input.nextInt();
      input.nextLine();

      service.manage_service(data, input, choice);
      }
    }
  }
}
