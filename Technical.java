package trabalhoprog;

import java.util.Scanner;

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

  public static void create_Tecnico(dados data, Scanner input){
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
    //data.add_User(tecnico);
    //FileHandler.saveCredentials(username, password);
  }
}
