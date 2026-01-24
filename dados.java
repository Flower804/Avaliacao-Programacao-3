package trabalhoprog;

import java.io.Serializable;
import java.util.Vector;
import java.util.Iterator;

public class dados implements Serializable{
  private Vector<Certificacao> certificacao = new Vector<>(); 
  //private Vector<ChemicalComponent> checmical = new Vector<>();
  private Vector<teste> teste = new Vector<>();
  private Vector<Users> user = new Vector<>();
  private Vector<Users> user_requests = new Vector<>(); 
  private Vector<Services> services = new Vector<>();
  private Vector<Services> service_requests = new Vector<>(); //made by a client 
  private Vector<fornecedor> fornecedores = new Vector<>();
  private Vector<area_medica> areas_medicas = new Vector<>();
  private Vector<encomendas> encomendas_existentes = new Vector<>();

  //Flower
  //TODO
  //current plan, so basically what it wants us to do is in the end to write all the data, what we can do is when we read the data we automatically load it to the respective Vector of its object, so when we shut down, we just need to add that data + the new data
  //that's going to save us the headache to go to each object created in the start and add it one by one to the Vector.
  //So the old data is already saved, so when we create a new user on the new run we just need to automatically add it to the Vector.
  //TODO
  //The new problem is that just if during the run we edit an already existing object, how will we find it and edit it's respective instance on the vector?
  //we can manually search it on the Vector's list and remove it, then add it with the updated data, that could be nice  
  
  public Users return_user(String ussername, String password){
    Iterator<Users> it = user.iterator();
    while(it.hasNext()){
      Users usr = it.next();

      //debugging==================================================
      System.out.println(usr.return_user() + usr.return_password());
      //===========================================================
      if(usr.return_user().equals(ussername)){  //&& (usr.return_password().equals(password))){
        return usr;
      } 
    }
    //TODO: finish this return Users user("no_name")
    System.out.println("Nao foi encontrado nenhum utilizador com o nome e a password referidos");
    return null;
  }
  
  public Vector<encomendas> return_encomendas(){
    return encomendas_existentes;
  }

  public int return_user_n(){
    return user.size();
  }

  public Vector<Users> return_user_requests(){
    return user_requests;
  }
  
  public Vector<Services> return_service_request(){
    return service_requests;
  }
  
  public Vector<fornecedor> return_fornecedor(){
    return fornecedores;
  }

  public Vector<Technical> return_tecnicos(){
    Vector<Technical> tecnicos = new Vector<>();

    Iterator<Users> it = user.iterator();
    while(it.hasNext()){
      Users usr = it.next();

      if(usr.type.equals("tecnico")){
        Technical tecnico = (Technical) usr;
        tecnicos.add(tecnico);
      }
    }

    return tecnicos;
  }

  public Vector<Services> return_services(){
    return services;
  }
  
  public void add_encomenda(encomendas encomenda){
    encomendas_existentes.add(encomenda);
    System.out.println("encomenda adicionada com sucesso");
  }

  public void add_area_medica(area_medica area_to_add){
    areas_medicas.add(area_to_add);
  }

  public void add_User(Users usertoadd){
    user.add(usertoadd);
    System.out.println("utilizador guardado com sucesso");
  }

  public void add_service(Services servicetoadd){
    services.add(servicetoadd);
  }
  
  public void add_fornecedor(fornecedor fornecedortoadd){
    fornecedores.add(fornecedortoadd);
    System.out.println("o fornecedor foi guardado com sucesso");
  }

  public void make_request(Users add_user_request){
    user_requests.add(add_user_request);
  }

  public void request_service(Services service){
    service_requests.add(service);
  }


  public void remove_request(int index){
    user_requests.remove(index);
  }
  
  public void remove_service_request(int index){
    service_requests.remove(index);
  }

  public void remove_service(int index){
    services.remove(index);
  }
}
