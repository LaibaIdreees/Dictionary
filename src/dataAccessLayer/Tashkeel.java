package dataAccessLayer;

public class Tashkeel {

    private String a;
    private final String b;
   
    /*
	 * @Author Laiba Idrees 20F-0281
	 * Parameter String a
	 */
    
    public Tashkeel(String a){
        this.a=a;
        this.b=removeTashkeel();
    }
  
    public String removeTashkeel(){
        
        a=a.replaceAll("\u0613", "");
        a=a.replaceAll("\u0614", "");

        a=a.replaceAll("\u0615", "");
        a=a.replaceAll("\u0616", "");
        a=a.replaceAll("\u0617", "");
        a=a.replaceAll("\u0610", "");
        a=a.replaceAll("\u0611", "");
        a=a.replaceAll("\u0612", "");
        a=a.replaceAll("\u0618", "");
        a=a.replaceAll("\u0619", "");
        a=a.replaceAll("\u061A", "");
        a=a.replaceAll("\u0654", "");
        a=a.replaceAll("\u0655", "");
        a=a.replaceAll("\u0656", "");
        a=a.replaceAll("\u0657", "");
        a=a.replaceAll("\u0658", "");
        a=a.replaceAll("\u0659", "");
        a=a.replaceAll("\u065A", "");
        a=a.replaceAll("\u065B", ""); 
        a=a.replaceAll("\u065C", "");
        a=a.replaceAll("\u065D", "");
        a=a.replaceAll("\u065E", "");
        a=a.replaceAll("\u065F", "");
        a=a.replaceAll("\u0670", "");

        a=a.replaceAll("\u06DA", "");
        a=a.replaceAll("\u06DB", "");
        a=a.replaceAll("\u06DC", "");
        a=a.replaceAll("\u06DD", "");
        a=a.replaceAll("\u06DE", "");
        a=a.replaceAll("\u06D6", "");
        a=a.replaceAll("\u06D7", "");
        a=a.replaceAll("\u06D8", "");
        a=a.replaceAll("\u06D9", "");
       
        a=a.replaceAll("\u06DF", "");
        a=a.replaceAll("\u06E0", "");
        a=a.replaceAll("\u06E1", "");
        a=a.replaceAll("\u06E2", "");
        a=a.replaceAll("\u06E3", "");
        a=a.replaceAll("\u06E4", "");
        a=a.replaceAll("\u06E5", "");
        a=a.replaceAll("\u06E6", "");
        a=a.replaceAll("\u06E7", "");
        a=a.replaceAll("\u06E8", "");
        a=a.replaceAll("\u06E9", "");
        a=a.replaceAll("\u06EA", "");
        a=a.replaceAll("\u06EB", "");
        a=a.replaceAll("\u06EC", "");
        a=a.replaceAll("\u06ED", "");

        a=a.replaceAll("\u064B", "");
        a=a.replaceAll("\u064C", "");
        a=a.replaceAll("\u064D", "");
        a=a.replaceAll("\u064E", "");
        a=a.replaceAll("\u064F", "");
        a=a.replaceAll("\u0650", "");
        a=a.replaceAll("\u0651", "");
        a=a.replaceAll("\u0652", "");
        a=a.replaceAll("\u0653", "");
    
        return a;
    }

   
    public String getOutput() {
        return b;
    }

}
