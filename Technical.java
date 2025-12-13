public class Technical extends Users{
    private int NIF;
    private int cellPhone;
    private String household;

    public Technical(String aUsername, String aPassword, String aName, Boolean aState, String aType, int aNIF, int aCellPhone, String aHousehold) {
        super(aUsername, aPassword, aName, aState, aType);
        
        NIF = aNIF;
        cellPhone = aCellPhone;
        household = aHousehold;
     }
    }