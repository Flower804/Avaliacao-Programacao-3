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
    private String user;
    private String password;
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

    public static boolean login(String username, String password) {
        Vector<String> users = return_user();

        for(String userLine : users){
            String[] data = userLine.split(",");

            String fileUsername = data[0];
            String filePassword = data[1];
            
            if(fileUsername.equals(username) && filePassword.equals(password)) {
              //Users user = new Users(username, password, username, true, "cliente");  
              return true;
            }
        }

        return false;
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
    }

    public static void saveAdm(Users user){
      //try(FileWriter writer = new FileWriter(user_path + "/Documents/admins.txt", true)){
	    try(FileWriter writer = new FileWriter("trabalhoprog/Documents/admins.txt", true)){
    		System.out.println(user.toFileString());

		    writer.write(user.toFileString() + "\n"); 	
	    } catch(IOException e){
        System.out.println("!FileHandler!: Error saving admin: " + e.getMessage());
      }
    }
    
//=============================================================================================

    public static boolean checkadm(){
      /*Flower
          TODO: pretty much everything of this stuff was taken from the read user func
          maybe we can do smth about it
      Todo: fix the file location finder thing here
      */
      File myObj = new File("trabalhoprog/Documents/admins.txt");
      Vector<String> admin = new Vector<>();
        
      try(Scanner myReader = new Scanner(myObj)){
        while(myReader.hasNextLine()){
          String data = myReader.nextLine();
          admin.add(data);
        }
      }catch (FileNotFoundException flf){
        System.out.println("!FileHandler!: Error reading file adm: " + flf.getMessage());
      }

      if(admin.isEmpty()){
        return true; //the vector is empty
      } else {
        return false;
      }
    }
}
