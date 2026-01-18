/*
RF19:
Rodrigo Gonçalves
Comments: ---
*/
import java.util.*;

public class Services{
    private int code;
    private Vector <Analyses> analysesList;
    private float totalServiceValue;
    private String state; //RG: Awaits approval/ Aproved / Concluded 
    private Date dateBeggin; //RG:  This date is the date when service beggin
    private Date conclusionDate; //RG: This date is the service completion date

    public Services(int aCode, float aTotal, String aState){
        code = aCode;
        totalServiceValue = aTotal;
        state = aState;
        dateBeggin = null;
        conclusionDate = null;
        analysesList = new Vector<>();
    }
    
    public void setState(String aState){
        state = aState;
    }
    public void orderAnalyses(){
        
    }
}
