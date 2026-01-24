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
    
    //Flower;
    public void set_total_value(float totalServiceValue){
      this.totalServiceValue = totalServiceValue;
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
    public manage_service(dados data, Scanner input, int edit){
      switch(edit){
        case 1:
          Vector<Analyses> new_analises = create_array_analises();
          break;
        case 2:
          System.out.println("indique o novo valor total de analise: ");
          float new_value = input.nextLong();
          input.nextLine();
          set_total_value(new_value);
          break;
      }
    }

    //Flower
    private Vector<Analyses> create_array_analises(){
      Vector<Analyses> selected_analyses = new Vector<>();

      System.out.println("por favor escolha as analises que pretenda adicionar - ou 9 para parar de adicionar");
      Vector<Analyses> existing_analyses = data.return_analises();

      Iterator<Analyses> it = existing_analyses.iterator();
      while(it.hasNext()){
        //TODO: print the Analyses info, this should be something done by Rodrigo since he is responsible 
        //by Analyses (R21) and the creation of Services R19 so this function should have been done from 
        //the start by him
      }
      int choice = input.nextInt();
      input.nextLine();
      
      if(choice == 9){
        System.out.println("Parou de selecionar analises");
        return selected_analyses;
      }else{
        Analyses analise = existing_analyses.get(choice);
        selected_analyses.add(analise);
      }
    }

    //Flower
    //another example of what I've been talking about
    //and bro stop using chatGPT, I can also give you misinformation and I'm beutiful bitch
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
