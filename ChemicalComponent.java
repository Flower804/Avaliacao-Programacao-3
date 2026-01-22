package trabalhoprog;

public class ChemicalComponent {
    private String designation;
    private String alphaValue;
    private String betaValue;
    private int code;
    private int stockQuantity; // in RF32 says "respectiva quantidade em stock em armazém deve ser incrementada."

    public ChemicalComponent(String aDesignation, String aAlphaValue, String aBetaValue, int aCode){
        designation = aDesignation;
        alphaValue = aAlphaValue;
        betaValue = aBetaValue;
        code = aCode;
        stockQuantity = 0;
    }

    public boolean buyComponents(int aNumComponents){
        if(aNumComponents < 0){
            return false;
        }else{
            stockQuantity += aNumComponents;
            return true;
        }
        
    }
}    
