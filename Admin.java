package trabalhoprog;

import java.util.Scanner;
import java.util.Vector;

public class Admin extends Users{
    public Admin(Users user){
        super(user.username, user.password, user.name, user.state, user.type);
    }
    
    public boolean setType(String aType, String aUsername){
        if(aType.equals("Admin")){//Tem que se ver a melhor opção a usar, supostamente só os admins podem alterar o tipo e tem que se pesquisar o username da pessoa a quem ele quer alterar
            type = aType;
            return true;
        }
        return false;
    }

    public static void create_admin(){
        Users admin = new Users("no_ussername", "no_password", "no_name", false, "adm");
        Scanner input = new Scanner(System.in);
    
        System.out.println("Please insert the username of the admin: ");
        String username = input.nextLine();

        System.out.println("Please insert the password for the admin: ");
        String password = input.nextLine();

        admin.setUsername(username);
        admin.setPassword(password);
        FileHandler.saveAdm(admin);

        input.close();
    }
  
  public static void seeSignUpRequests(){
    //read file of SIgnUpRequests
    //numerate them and send them
    Vector<String> current_requests = FileHandler.return_userrequests();
    for(int i = 0; i < current_requests.size(); i++){
      String request = current_requests.get(i); 
      String[] data = request.split(",");
      System.out.println("request " + i + "- name: " + data[0] + " password: " + data[1]);
    }
    
    Scanner reader = new Scanner(System.in);
    System.out.println("que utilizador quer aprovar");
    int choice = reader.nextInt();
    
    String request_to_accept = current_requests.get(choice);
    String data[] = request_to_accept.split(",");

    Users user_to_accept = new Users(data[0], data[1], "no_name", false, data[2]);
    FileHandler.saveUser(user_to_accept);
    FileHandler.removeUser(choice);
  } 
}
