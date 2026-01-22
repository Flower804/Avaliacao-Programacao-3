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

  public encomendas(Vector<ChemicalComponent> componentes, int quantidade, dados data){
    this.compomentes = compomentes;
    this.quantidade = quantidade;
    this.data_pedido = new Date();
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
}
