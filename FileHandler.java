import java.io.File;
import java.io.FileWriter;
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
    private List<String> return_user(){
        //TODO: make the File path user the working directory so we dont waste a variable plus so we make it stable
        //YK error prone stuff
        //ass: Flower
        File myObj = new File("emails.txt");
        List<String> emails = new ArrayList<>();

        try (Scanner myReader = new Scanner(myObj)){
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                emails.add(data);
            }

            return emails;
        } catch(FileNotFoundException fnf){
            System.out.print("!return_emails!: an error has occured: ");
            System.out.print(fnf);
        }
    }
}