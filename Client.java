package trabalhoprog;

import java.util.logging.Handler;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Vector;

public class Client extends Users{
  private int NIF;
  private int cellPhone;
  private String household;
    
  public Client(String aUsername, String aPassword, String aName, Boolean aState, String aType, int aNIF, int aCellPhone, String aHousehold ) {
    super(aUsername, aPassword, aName, aState, aType);
    NIF = aNIF;
    cellPhone = aCellPhone;
    household = aHousehold;
  }
  
  public String get_username(){
    return username;
  }

  public static String create_Client(dados data, Scanner input){
    System.out.println("Por favor insira o nome de utilizador");
    String username = input.nextLine();

    System.out.println("Por favor insira a sua palavra pass");
    String password = input.nextLine();

    System.out.println("Por insira o nome");
    String nome = input.nextLine();

    Boolean estado = true;

    //=========================
    String type = "clients";
    //=========================
    System.out.println("Por favor insira o seu NIF");
    int NIF = input.nextInt();
    input.nextLine();

    System.out.println("Por favor insira o seu numero de telefone");
    int numero_tele = input.nextInt();
    input.nextLine();

    System.out.println("Por favor insira a sua morada");
    String morada = input.nextLine();

    Client client = new Client(username, password, nome, estado, type, NIF, numero_tele, morada);
    
    data.make_request(client);
    //data.add_User(client);
    //FileHandler.saveCredentials(username, password);
    return username;
  }

  public void request_a_service(dados data, Scanner input){
    Services.create_Service(data, input, get_username());
  }

  public void manage_services(dados data, Scanner input){
    Vector<Services> current_services = data.return_services_by_client(get_username());
    
    System.out.println("Selecione qual servico pertende gerir - ou selecione 9 para sair");
    Iterator<Services> it = current_services.iterator();
    int counter = 0;
    while(it.hasNext()){
      counter++;
      Services service = it.next();

      //TODO: finish putting the things bla bla bla..
      System.out.println("service n" + counter + " - codigo: " + service.get_code());
    }
    int choice = input.nextInt();
    input.nextLine();

    if(choice == 9){
      System.out.println("Escolheu nao gerir nenhum dos seus servicos");
      //exit
    } else {
      Services service = current_services.get(choice);

      System.out.println("O que pretende editar?");
      System.out.println("1- Lista de analises: " + "[inserir a lista de analises] " + "2-valor total de servico " + service.get_totalServiceValue());
      choice = input.nextInt();
      input.nextLine();
      
      if((choice != 1) || (choice != 2)){
        System.out.println("escolha invalida");
      } else {
        service.manage_service(data, input, choice);
        data.save_updated_service(service, 2);
      }
    }
  }
}
