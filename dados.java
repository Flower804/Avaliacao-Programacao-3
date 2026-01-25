package trabalhoprog;

import java.io.Serializable;
import java.util.Vector;
import java.util.Iterator;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import java.io.PrintWriter;

//TODO: verify if this is permited
import java.io.File;

//Made entirely by Flower and ONLY FLOWER

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
  private Vector<Analyses> analises_existentes = new Vector<>();

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
  
  public Vector<Services> return_service_by_tecnico(int NIF){
    //using NIF because it's going to always be constant and unique so (it would be if the guy responsible for making it unique actually did it)
    Vector<Services> service_by_tecnico = new Vector<>();

    Iterator<Services> it = services.iterator();
    while(it.hasNext()){
      Services it_service = it.next();
      Technical current_tecnico_inservice = it_service.return_tecnico();
      if(current_tecnico_inservice.get_NIF() == NIF){
        service_by_tecnico.add(it_service);
      }
    }

    return service_by_tecnico;
  }
  
  public Vector<Services> return_services_by_client(String username){
    Vector<Services> services_by_user = new Vector<>();
    
    //Flower
    //not sure if they can manage the services they requested (as in the ones that were already accepted) or the services they requested (as in the ones they requested and havent been accepteed)
    //but it makes more sense if it is the latter
    //Iterator<Services> it = services.iterator();
    Iterator<Services> it = service_requests.iterator();
    while(it.hasNext()){
      Services it_service = it.next();
      
      if((it_service.return_username()).equals(username)){
        services_by_user.add(it_service);
      }
    }

    return services_by_user;
  }

  public Vector<Analyses> return_analises(){
    return analises_existentes;
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
  
  public void create_csv_output(String csv_dir) throws IOException{
    File csvOutputFile = new File(csv_dir);
    
    List<String[]> dataLines = convert_csv();
    Iterator<String[]> it = dataLines.iterator();

    try(PrintWriter pw = new PrintWriter(csvOutputFile)){
      while(it.hasNext()){
        String[] line = it.next();
        pw.println(String.join(",", line));
      }
    }
  }

  public List<String[]> convert_csv(){
    List<String[]> dataLines = new ArrayList<>();
    String pattern = "dd/MM/yyyy";
    
    DateFormat df = new SimpleDateFormat(pattern);

    Iterator<Services> it = services.iterator();
    while(it.hasNext()){
      Services service = it.next();
      
      //<data>m <valor>, <client>, <analises>
      String date = df.format(service.get_date());
      String value = "" + service.get_totalServiceValue();
      String client = service.return_username();
      String analises_list = "";
      
      Vector<Analyses> analises = service.return_analises();

      Iterator<Analyses> itt = analises.iterator();
      while(itt.hasNext()){
        Analyses service_analise = itt.next();
        analises_list = analises_list.concat("; " + service_analise.get_designacao());
      }

      //<data>, <valor>, <cliente>, <analises>
      dataLines.add(new String[]
          {date, value, client, analises_list});
    }

    return dataLines;
  }

  public void make_request(Users add_user_request){
    user_requests.add(add_user_request);
  }

  public void request_service(Services service){
    service_requests.add(service);
  }

  public void save_updated_service(Services service, int entrance){
    int searched_index = -1;
    Vector<Services> to_search;

    if(entrance == 1){ //entrance came from tecnico 
      to_search = services; 
    } else { //entrance came from client 
      to_search = service_requests; 
    } 
    
    Iterator<Services> it = to_search.iterator();
    while(it.hasNext()){ 
      Services searched = it.next(); 
      int searched_code = searched.get_code();

      if(searched_code == service.get_code()){
        searched_index = to_search.indexOf(searched);  
        break;
      }
    }

    if(searched_index != -1){
      to_search.remove(searched_index);
      to_search.add(service);
      System.out.println("Servico atualizado com sucesso");
    } else {
      System.out.println("Aconeceu um erro a atualizar os dados do servico");
    }
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
