package trabalhoprog;

import java.util.Scanner;

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
        Users admin = new Users("no_ussername", "no_password", "no_name", false, "admin");
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


}
