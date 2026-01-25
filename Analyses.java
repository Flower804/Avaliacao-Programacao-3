package trabalhoprog;
/*
RF19:
Rodrigo Gonçalves
Comments: ---
*/
import java.util.*;

public class Analyses {
    private int internalCode;
    private String designation;
    private Certificacao certification; // A/B/C/D/E
    private String lab; // Here is the Lab who gives the certification
    private Technical technical;
    private Technical superiorTechnical;
    private String [] chemicalComponents; // contains several chemical components
    private Vector <String> tests;
    private Date conclusionDate;
    private String finalResults;
    private Vector <String> suppliersList;
    private Vector <String> medicalAreaList;
    private Vector <teste> testsList;

    

    public Analyses(int internalCode, String aDesignation, Certificacao aCertefication, String aLab, Technical aTechnical, Technical aSuperiorTechnical, int aCompNum){
        designation = aDesignation;
        certification = aCertefication;
        lab = aLab;
        technical = aTechnical;
        superiorTechnical = aSuperiorTechnical;
        chemicalComponents = new String[aCompNum];
        tests = new Vector<String>();
        suppliersList = new Vector <String>(6);
        medicalAreaList = new Vector <String>(4);
        testsList= new Vector <teste>();
    }

    public void finishAnalysis(){
        conclusionDate = new Date();
    }
    public void addSuppliers(String aSupplier){
        if(suppliersList.size() == 6){
            System.out.println("!ERRO!: NÃO PODE ADICIONAR MAIS FORNECEDORES (MAX: 6)");
        }else{
            System.out.println("O Fornecedor " + aSupplier + " foi adicionado com sucesso");
            suppliersList.add(aSupplier);
        }
    }
    public boolean addMedicalArea(String aMedicalArea){
            if(medicalAreaList.size() == 4){
                System.out.println("!ERRO!: NÃO PODE ADICIONAR MAIS ÁREAS MÉDICAS (MAX: 4)");
                return false;
            }
            medicalAreaList.add(aMedicalArea);
            return true;
    }

    //RF23 - RG- Esse Técnico Responsável deve indicar o técnico por cada análise
    public boolean setTechnical(Technical aChoosenTechnical){
        if(superiorTechnical == null){
            System.out.println("!ERRO!: Análise sem Técnico Superior por favor adicione um");
            return false;
        }
        technical = aChoosenTechnical;
        return true;
    }

    //RF24 - RG-
    public boolean addTest(teste aTest){
        return testsList.add(aTest); //returns 0 if fails, or 1 if worked
    }
    //RF34 - RG- 
    public boolean removeTest(teste aTest){
        return testsList.remove(aTest);
    }


  //Flower
  //Im not associated with anything that was writen above, I dont want to be, every author can be checked on the github repo 
  public String get_designacao(){
    return designation;
  }
}
