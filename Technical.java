package trabalhoprog;

import java.util.Scanner;
import java.util.Vector;
import java.util.Iterator;

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
  
  public int get_NIF(){
    return NIF;
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
    String type = "tecnico";
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
    
    service = services.get(choice);
    data.remove_service_request(choice);
    data.add_service(service);
  }

  public void intruduze_data(dados data, Scanner input){
    //analises laboratoriais, categorias, testes, areas medicas, fornecedores, encomendas
    System.out.println("Que tipo de dados gostaria de adicionar?\n 1- adicionar fornecedor \n 9- sair");
    int choice = input.nextInt();
    input.nextLine();

    switch(choice){
      case(1):
        fornecedor.create_fornecedor(data, input);
        break;

      case(9):
        break;
    }
  }
  
  public void manage_services(dados data, Scanner input){
    Vector<Services> current_services = data.return_service_by_tecnico();

    System.out.println("selecione qual servico pertende gerir - ou selecione 9 para sair");
    Iterator<Services> it = current_services.iterator();
    int counter = 0;
    while(it.hasNext()){
      counter++;
      Services service = it.next();

      System.out.println("servico n" + counter + "- codigo: " + service.get_code() + " - estado: " + service.get_state());
    }
    int choice = input.nextInt();
    input.nextLine();

    if(choice == 9){
      System.out.println("escolheu nao gerir nenhum dos seus servicos");
    } else {
      Services service = current_services.get(choice);

      System.out.println("O que pretende editar?");
      System.out.println("1- Lista de analises: " + "[TODO: inserir a lista de analises]" + " 2- valor total de servico " + service.get_totalServiceValue() + " 3- estado" + service.get_state());
      choice = input.nextInt();
      input.nextLine();

      service.manage_service(data, input, choice);
    }
  }

  public void create_medical_area(dados data, Scanner input){
    area_medica.create_area_medica(data, input);
  }

  public void create_encomenda(dados data, Scanner input){
    encomendas.create_encomenda(data, input);
  }
}
