package trabalhoprog;

/*
RF19:
Rodrigo Gonçalves
Comments: ---
*/
import java.util.*;
import java.util.Scanner;
import java.io.Serializable;

public class Services implements Serializable{
    private int code;
    private Vector <Analyses> analysesList;
    private float totalServiceValue;
    private String state; //RG: Awaits approval/ Aproved / Concluded 
    private Date dateBeggin; //RG:  This date is the date when service beggin
    private Date conclusionDate; //RG: This date is the service completion date
    private Technical tecnico_responsavel; //Flower


    public Services(int aCode, float aTotal, String aState){
        code = aCode;
        totalServiceValue = aTotal;
        state = aState;
        dateBeggin = new Date();
        conclusionDate = null;
        analysesList = new Vector<>();
        tecnico_responsavel = null; //Flower
    }
    
    //Flower
    public void set_Tecnico(Technical tecnico){
      tecnico_responsavel = tecnico; 
    }

    public void setState(String aState){
        state = aState;
    }
    
    //Flower
    public int get_code(){
      return code;
    }
    
    //like sure I can just return a Tecnico here
    public Technical return_tecnico(){
      return tecnico_responsavel;
    }

    public void orderAnalyses(){
        
    }
     
    //Flower
    public static void create_Service(dados data, Scanner input){
      System.out.println("Por favor insira o codigo do servico");
      int code = input.nextInt();
      input.nextLine();

      System.out.println("por favor insira o valor total do servico");
      float total = input.nextLong();
      input.nextLine();

      //System.out.println("por favor indique o estado do servico");
      //String estado = input.nextLine();
      String estado = "iniciado";

      Services service = new Services(code, total, estado);
      //data.add_service(service);
      data.request_service(service);
    }
}
