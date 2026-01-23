package trabalhoprog;

import java.util.Scanner;
import java.io.Serializable;

public class fornecedor implements Serializable{
  private String nome;
  private String morada;
  private int contacto_telefonico;

  public fornecedor(String nome, String morada, int contacto_telefonico){
    this.nome = nome;
    this.morada = morada;
    this.contacto_telefonico = contacto_telefonico;
  }
  
  public String get_nome(){
    return nome;
  }

  public static void create_fornecedor(dados data, Scanner input){
    System.out.println("Por favor insira o nome do fornecedor");
    String nome = input.nextLine();

    System.out.println("Por favor insira a morada");
    String morada = input.nextLine();

    System.out.println("Por favor insira o contacto telefonico");
    int contacto = input.nextInt();
    input.nextLine();

    fornecedor forn = new fornecedor(nome, morada, contacto);

    data.add_fornecedor(forn);
  }
}
