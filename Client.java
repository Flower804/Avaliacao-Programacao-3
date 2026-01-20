package trabalhoprog;

import java.util.logging.Handler;
import java.util.Scanner;

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
  
  public static void create_Client(dados data){
    Scanner input = new Scanner(System.in);
    
    System.out.println("Por favor insira o nome de utilizador");
    String username = input.nextLine();

    System.out.println("Por favor insira a sua palavra pass");
    String password = input.nextLine();

    System.out.println("Por insira o nome");
    String nome = input.nextLine();

    Boolean estado = true;

    //=========================
    String type = "client";
    //=========================
    System.out.println("Por favor insira o seu NIF");
    int NIF = input.nextInt();

    System.out.println("Por favor insira o seu numero de telefone");
    int numero_tele = input.nextInt();

    System.out.println("Por favor insira a sua morada");
    String morada = input.nextLine();
    
    input.close();

    Client client = new Client(username, password, nome, estado, type, NIF, numero_tele, morada);
    
    //TODO: add the pedido de registo thingy
    data.add_User(client);
    FileHandler.saveCredentials(username, password);
  }
}
