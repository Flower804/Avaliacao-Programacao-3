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

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandler {
    private static String fileUsername;
    private static String filePassword;
    private static String fileuser_type;
    private static Vector<String> tecnicos = new Vector<>(); 
    //Flower: see if this is really necessary to be here, need to test on other devices after
    //paths suck
//=========================================================
    private static String path_credencias = "trabalhoprog/Documents/credencias_acesso.txt";

    private static String path_data = "trabalhoprog/Documents/dados_apl.data";
//========================================================

    public static dados load_data_file(){
      try{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(path_data));

        dados data = (dados) in.readObject();
        in.close();

        return data;
      } catch (FileNotFoundException flf) {
        System.out.println("ficheiro de dados nao conseguiu ser carregado");
      } catch (IOException ioe) {
        System.out.println("ficheiro de dados nao conseguiu ser carregado");
      } catch (ClassNotFoundException cln){
        System.out.println("ficheiro de dados nao conseguiu ser carregado");
      } 
      /*catch (EOFException eof) {
        //TODO: create an admin 
      }*/
      return new dados();
    }

    public static void save_data(dados data){
      try{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path_data));

        out.writeObject(data);
        out.close();
      } catch (IOException ioe){
        System.out.println("ups");
      }
    }
    

    private static Vector<String> return_user(){
        File myObj = new File(path_credencias);
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

    public static boolean login(String username, String password) {
        Vector<String> users = return_user();

        for(String userLine : users){
            String[] data = userLine.split(",");

            fileUsername = data[0];
            filePassword = data[1];

            if(fileUsername.equals(username) && filePassword.equals(password)) {  
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

    public void saveCredentials(String username, String password){
      try(FileWriter writer = new FileWriter(path_credencias, true)){
        writer.writer(username + "," + password + "\n");
      }catch(IOException e){
        System.out.println("error writing credentials to the cretentials file");
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
    
    public static void addService(Services service){
      try(FileWriter writer = new FileWriter("trabalhoprog/Documents/services.txt", true)){
        writer.write(service.toFileString() + "\n");
      } catch(IOException e){
		    System.out.println("Working directory: " + System.getProperty("user.dir"));
        System.out.println("!FileHandler!: Error saving user: " + e.getMessage());
      }
    }

    public static void remove_service(int index){
      try{
        Vector<String> services = return_services();
        services.remove(index);
        
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
    
}
