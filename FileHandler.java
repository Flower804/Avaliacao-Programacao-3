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
    //Flower: see if this is really necessary to be here, need to test on other devices after
    //paths suck
//=========================================================
    private static String path_credencias = "trabalhoprog/Documents/credencias_acesso.txt";

    private static String path_data = "trabalhoprog/Documents/dados_apl.dat";

    private static String path_last_session = "trabalhoprog/Documents/info_sistema.dat";
//========================================================

    public static dados load_data_file(){
      try{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(path_data));

        dados data = (dados) in.readObject();
        in.close();
        
        System.out.println("data was loaded succesfully");
        return data;
      } catch (FileNotFoundException flf) {
        System.out.println("ficheiro de dados nao conseguiu ser carregado");
        //create an empty file
        try{  
          ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path_data));
          out.close();
          return load_data_file();
        } catch(IOException ioe){
          System.out.println("well fuck");
        }
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
    
    public static session_record load_last_session(){
      try{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(path_last_session));
        
        session_record last_session = (session_record) in.readObject();
        in.close();

        return last_session;
      } catch(FileNotFoundException fnf){
        try{
          ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path_last_session));
          out.close();
          return load_last_session();
        } catch(IOException ioe){
          System.out.println("create file falesafe faild");
        }
      }catch(IOException ioe){
        System.out.println("Dados sobre a ultima sessao nao foram carregados");
      } catch(ClassNotFoundException cln){
        System.out.println("Dados sobre a ultima sessao nao foram carregados");
      }
      return new session_record();
    }

    public static void save_data(dados data){
      try{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path_data));

        out.writeObject(data);
        out.close();

        System.out.println("A data foi guardada com sucesso");
      } catch (IOException ioe){
        System.out.println("something went wrong writing to data");
        System.out.println(ioe);
      }
    }

    public static void save_session(session_record session){
      try{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path_last_session));

        out.writeObject(session);
        out.close();

        System.out.println("dados sobre a sessao foram guardados com sucesso");
      } catch(IOException ioe){
        System.out.println("Ocorreu um erro a guardar os dados sobre a sessao");
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

            String fileUsername = data[0];
            String filePassword = data[1];

            if(fileUsername.equals(username) && filePassword.equals(password)) {  
              return true;
            }
        }

        return false;
    }

    public static void saveCredentials(String username, String password){
      try(FileWriter writer = new FileWriter(path_credencias, true)){
        writer.write(username + "," + password + "\n");
      }catch(IOException e){
        System.out.println("error writing credentials to the cretentials file");
      }
    } 
}
