package trabalhoprog;

import java.io.Serializable;
import java.util.Scanner;

public class area_medica implements Serializable{
  String designacao;
  String familia;

  public area_medica(String designacao, String familia){
    this.designacao = designacao;
    this.familia = familia;
  }

  public static void create_area_medica(dados data, Scanner input){
    System.out.println("Por favor insira a designacao da area medica");
    String designacao = input.nextLine();

    System.out.println("Por favor insira a familia da area medica");
    String familia = input.nextLine();

    area_medica area = new area_medica(designacao, familia);

    data.add_area_medica(area);
    System.out.println("area medica guardada com sucesso");
  }
}
