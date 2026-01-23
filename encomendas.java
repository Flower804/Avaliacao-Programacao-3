package trabalhoprog;

import java.io.Serializable;
import java.util.Scanner;
import java.util.Vector;
import java.util.Date;

public class encomendas implements Serializable{
  private Vector<ChemicalComponent> compomentes;
  private int quantidade;
  private Date data_pedido;
  private Date data_entrega;
  private int codigo; //TODO: verify that this code is unique in all of the encomendas
                      //it's quite simple, we just go to data and ask "yo gimme all of the encomendas you have stored"
                      //then assign a code to this encomenda, if that code is already present in any other encomenda, we just change it, and repeat
                      //see simple

  private fornecedor fornecedor_encomendado;

  public encomendas(Vector<ChemicalComponent> componentes, int quantidade, fornecedor fornecedor_encomendado, dados data){
    this.compomentes = compomentes;
    this.quantidade = quantidade;
    this.data_pedido = new Date();
    this.fornecedor_encomendado = fornecedor_encomendado;
    this.codigo = 0;

    Boolean code_unique = false;
    Vector<encomendas> encomendas_ativas = data.return_encomendas();
    while(!code_unique){
      //like ok my idea for the code is just to start at 0 and then go up until the code is unique
      //LMAOOOO this is like, so ineficient because of like "O time" since if we have 40 active encomendas it will have to iterate this loop 40 times
      //this solution is just so stupid
      if(encomendas_ativas.size() == 0){
        code_unique = true;
        break;
      }

      encomendas encomenda;
      for(int i = 0; i < encomendas_ativas.size(); i++){
        encomenda = encomendas_ativas.get(i);

        if(codigo == encomenda.get_codigo()){
          encomendas_ativas.remove(i);
          codigo++;
          break;
        }
      }
    }
  }

  public int get_codigo(){
    return codigo; 
  }

  public static void create_encomenda(dados data, Scanner input){
    System.out.println("por favor selecione quais componentes quimicos deseja");
    //Flower
    //TODO: Rodrigo, after you create the Componentes quimicos thing you need to complete this by
    //go to dados and get the current existing vector list of componentes quimicos
    Vector<ChemicalComponent> componentes_selected = new Vector<>();
    Boolean on_selection = true;
    int choice;
    while(on_selection){
      //Flower
      //TODO: display the Vector list
      
      choice = input.nextInt();
      input.nextLine(); //Flower: Rodrigo don't take this line out, or else the rest of the programs inputs will break
      
      if(choice == 9){
        on_selection = false;
        break;
      }

      componentes_selected.add(/*TODO: [the vector containing the ChemicalComponent that we have].get(choice)*/);
    }

    System.out.println("por favor indique qual a quantidade que deseja");
    int quantidade = input.nextInt();

    System.out.println("por favor indique o fornecedor a que pretende fazer a encomenda");
    Vector<fornecedor> fornecedores = data.return_fornecedor();
    fornecedor forn;
    for(int i = 0; i < fornecedores.size(); i++){
      forn = fornecedores.get(i);
      
      System.out.println("fornecedor n" + i + "- nome" + forn.get_nome());
    }
    choice = input.nextInt();
    input.nextLine();

    forn = fornecedores.get(choice);
    
    encomendas encomenda = new encomendas(componentes_selected, quantidade, forn, data);
    data.add_encomenda(encomenda);
  }
}
