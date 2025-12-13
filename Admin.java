public class Admin extends Users{
    public Admin(String aUsername, String aPassword, String aName, Boolean aState, String aType) {
        super(aUsername, aPassword, aName, aState, aType);
     }
     public boolean setType(String aType, String aUsername){
            if(aUsername...){//Tem que se ver a melhor opção a usar, supostamente só os admins podem alterar o tipo e tem que se pesquisar o username da pessoa a quem ele quer alterar
                type = aType;
                return true;
            }
        return false;
     }
}