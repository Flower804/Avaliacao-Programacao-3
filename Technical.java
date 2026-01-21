package trabalhoprog;

import java.util.Scanner;
import java.util.Vector;

public class Technical extends Users{
  private int NIF;
  private int cellPhone;
  private String household;

  public Technical(String aUsername, String aPassword, String aName, Boolean aState, String aType, int aNIF, int aCellPhone, String aHousehold) {
    super(aUsername, aPassword, aName, aState, aType);
        
    NIF = aNIF;
    cellPhone = aCellPhone;
    household = aHousehold;
  }

  public static String create_Tecnico(dados data, Scanner input){
    System.out.println("Por favor insira o nome de utilizador");
    String username = input.nextLine();

    System.out.println("Por favor insira a sua palavra pass");
    String password = input.nextLine();

    System.out.println("Por insira o nome");
    String nome = input.nextLine();

    Boolean estado = true;

    //=========================
    String type = "Tecnico";
    //=========================
    System.out.println("Por favor insira o seu NIF");
    int NIF = input.nextInt();
    input.nextLine();

    System.out.println("Por favor insira o seu numero de telefone");
    int numero_tele = input.nextInt();
    input.nextLine();

    System.out.println("Por favor insira a sua morada");
    String morada = input.nextLine();

    Technical tecnico = new Technical(username, password, nome, estado, type, NIF, numero_tele, morada);
    
    data.make_request(tecnico);
    return username;
    //data.add_User(tecnico);
    //FileHandler.saveCredentials(username, password);
  }

  public void accept_requests(dados data, Scanner input){
    Vector<Services> services = data.return_service_request();

    System.out.println("Escolha qual servico aceitar");

    Services service;
    for(int i = 0; i < services.size(); i++){
      service = services.get(i);
      System.out.println("service " + i + ":" + " codigo:" + service.get_code());
    }
    int choice = input.nextInt();
    input.nextLine();
    
    data.remove_service_request(choice);
    service = services.get(choice);
    data.add_service(service);
  }
}
