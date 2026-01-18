package trabalhoprog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Vector;

public class FileHandler {
    private static String fileUsername;
    private static String filePassword;
    private static String fileuser_type;
    private static Vector<String> tecnicos = new Vector<>(); 
    //Flower: see if this is really necessary to be here, need to test on other devices after
    //paths suck
//=========================================================
    private static Path s = Paths.get("").toAbsolutePath();
    private static String user_path = s.toString() ;
    
    //Flower: how the hell is this diferent from like 6 lines bellow????????
    //private String path_users = "trabalhoprog/Documents/users.txt";

//========================================================
    private static Vector<String> return_user(){
        //YK error prone stuff
        //ass: Flower
	//System.out.println("!DEBUG!: " +  path_user);
        File myObj = new File("trabalhoprog/Documents/users.txt");
        Vector<String> users = new Vector<>();

        try (Scanner myReader = new Scanner(myObj)){
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                users.add(data);
            }

        } catch(FileNotFoundException fnf){
            System.out.print("!FileHandler!: an error has occured: ");
            System.out.print(fnf);
        }

        return users;
    }
  
    public static void loadUser(Users user){
      user.setUsername(fileUsername);
      user.setPassword(filePassword);
      user.setType(fileuser_type);
    }

    public static boolean login(String username, String password) {
        Vector<String> users = return_user();

        for(String userLine : users){
            String[] data = userLine.split(",");

            fileUsername = data[0];
            filePassword = data[1];
            fileuser_type = data[2];

            if(fileUsername.equals(username) && filePassword.equals(password)) {
              //Users user = new Users(username, password, username, true, "cliente");  
              return true;
            }
        }

        return false;
    }

    public static void doUserRequest(Users user){
      try(FileWriter writer = new FileWriter("trabalhoprog/Documents/userrequests.txt", true)){
        writer.write(user.toFileString() + "\n");
      } catch(IOException e){
		    System.out.println("Working directory: " + System.getProperty("user.dir"));
        System.out.println("!FileHandler!: Error saving user: " + e.getMessage());
      }
    }

    public static Vector<String> return_userrequests(){
      File myObj = new File("trabalhoprog/Documents/userrequests.txt");
      Vector<String> users = new Vector<>();

      try (Scanner myReader = new Scanner(myObj)){
        while(myReader.hasNextLine()){
          String data = myReader.nextLine();
          users.add(data);
        }
      } catch(FileNotFoundException fnf){
          System.out.print("!FileHandler!: an error has occured: ");
          System.out.print(fnf);
      }

      return users;
    }

    public static void removeUser(int index){
      try{
        Vector<String> users = return_userrequests();
        users.remove(index);
        
        FileWriter writer = new FileWriter("trabalhoprog/Documents/userrequests.txt");
        for(String line : users){
          writer.write(users + "\n");
        }
        writer.close();
      } catch(FileNotFoundException fnf){
        System.out.println("!FileNotFouind!");
        System.out.println(fnf);
      } catch(IOException io){
        System.out.println(io);
      }
    }

    public static Vector<String> return_tecnicos(){
      Vector<String> users = return_user();
      int index = 0;

      for(int i = 0; i < users.size(); i++){
        String userLine = users.get(i);
        String[] data = userLine.split(",");

        if(data[2].equals("Tecnico")){
          tecnicos.add(userLine);
          System.out.println("0- " + "nome: " + data[0]);
        }
      }
      return tecnicos;
    }

    public static Vector<String> return_services(){
     File myObj = new File("trabalhoprog/Documents/services.txt");
      Vector<String> services = new Vector<>();

      try (Scanner myReader = new Scanner(myObj)){
        while(myReader.hasNextLine()){
          String data = myReader.nextLine();
          services.add(data);
        }
      } catch(FileNotFoundException fnf){
          System.out.print("!FileHandler!: an error has occured: ");
          System.out.print(fnf);
      }

      return services;
    }

    public static void remove_service(int index){
      try{
        Vector<String> services = return_services();
        service.remove(index);
        
        FileWriter writer = new FileWriter("trabalhoprog/Documents/services.txt");
        for(String line : services){
          writer.write(line + "\n");
        }
        writer.close();
      } catch(FileNotFoundException fnf){
        System.out.println("!FileNotFouind!");
        System.out.println(fnf);
      } catch(IOException io){
        System.out.println(io);
      }
    }
/* Flower
 * %TEMP FIX%
 *  TODO: can turn this two functions into one
 *  will probably do this more to the end of the work
 *	TODO: fix the file location finding thingy	
===============================================================================================*/

    public static void saveUser(Users user){
        try(FileWriter writer = new FileWriter("trabalhoprog/Documents/users.txt", true)){
            writer.write(user.toFileString() + "\n");
        } catch(IOException e){
		        System.out.println("Working directory: " + System.getProperty("user.dir"));
            System.out.println("!FileHandler!: Error saving user: " + e.getMessage());
        }
        System.out.println("utilizador aprovado com sucesso");
    }

    public static void saveAdm(Users user){
      //try(FileWriter writer = new FileWriter(user_path + "/Documents/admins.txt", true)){
	    try(FileWriter writer = new FileWriter("trabalhoprog/Documents/users.txt", true)){
    		System.out.println(user.toFileString());

		    writer.write(user.toFileString() + "\n"); 	
	    } catch(IOException e){
        System.out.println("!FileHandler!: Error saving admin: " + e.getMessage());
      }
    }
    
//=============================================================================================

    public static boolean checkadm(){
      Vector<String> users = return_user();
      for(String userLine : users){
        String[] data = userLine.split(",");
        
        System.out.println(data);

        if(data[2].equals("adm")){
          return false;
        }
      }
      return true; //either the file is empty or theres no adm
    }
}
