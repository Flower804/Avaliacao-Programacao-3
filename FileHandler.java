import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandler {
    String user;
    String password;

    Path s = Paths.get("");
    String user_path = s.toString() ;

    //private void ReadFileList(){
    //    try{
    //        
    //    }
    //}
    private static List<String> return_user(){
        //TODO: make the File path user the working directory so we dont waste a variable plus so we make it stable
        //YK error prone stuff
        //ass: Flower
        File myObj = new File("users.txt");
        List<String> users = new ArrayList<>();

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
        List<String> users = return_user();

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

    public static void saveUser(Users user){
        try(FileWriter writer = new FileWriter("users.txt", true)){
            writer.write(user.toFileString() + "\n");
        } catch(IOException e){
            System.out.println("!FileHandler!: Error saving user: " + e.getMessage());
        }
    }
}