package dataAccessLayer;

import java.sql.* ;
import java.sql.Statement;
import java.io.* ;

public class ImportData {
	
	DbConnection conn = DbConnection.getInstance();
	Connection con;
	public ImportData() {

	}
	
	int i=0;
	public ImportData(int i) {
	}
	
	 /*
		 * @Author Laiba Idrees 20F-0281
		 */
	
	public void insertingData(String[] temp, String s) {
		 PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement("INSERT INTO wordlist (ID, word, diacritics, meaning, grammar, origin, gender, number) VALUES (?,?, ? , ? , ? , ? ,?,?)");
		
		pstmt.setInt(1, i); 
		pstmt.setString(2, temp[0]); 
        pstmt.setString(3, s);
        pstmt.setString(4, " ");
        pstmt.setString(5, temp[1]);
        pstmt.setString(6, temp[2]);
        pstmt.setString(7, temp[3]);
        pstmt.setString(8, temp[4]);
        int rs = pstmt.executeUpdate();
        System.out.println(rs+ " Successfully connected to MySQL server...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}
	
    public void fileInitialize(String f) {
  		String line = "";  
  		String splitBy = ",";
  		BufferedReader br = null;
  		try {
  				Reader reader = new InputStreamReader(new FileInputStream(f), "utf-8");
  				 br = new BufferedReader(reader);  
  			while ((line = br.readLine()) != null)  
  			{ 	String[] temp = line.split(splitBy);
  			Tashkeel d=new Tashkeel(temp[0]);
  			String s = d.getOutput();
  			insertingData(temp, s);
  		}	}	
  		
  		catch (IOException e) {
  			System.out.println(e.getMessage() );
  		}
  		
  	}
    
    public void importData(String file) {
    	con = conn.getConnection();
    	createTable(con);
       
    		 fileInitialize(file);
    	
        System.out.println("Complete!!!");
    }
    
    public void createTable(Connection con) {
		 try {
			   Statement stmt = con.createStatement();
			   String sql= "CREATE TABLE `wordlist` (  `ID` int(20) NOT NULL AUTO_INCREMENT ,  `word` varchar(30) NOT NULL UNIQUE,  `diacritics` varchar(30) NOT NULL,  `meaning` varchar(30) NOT NULL, `grammar` varchar(30) NOT NULL, `origin` varchar(30) NOT NULL, `gender` varchar(30) NOT NULL, `number` varchar(30) NOT NULL,  PRIMARY KEY (ID)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				stmt.executeUpdate(sql);
				System.out.println("Table created!!!");
				sql= "CREATE TABLE `stemmerlist` (  `ID` int(20) NOT NULL AUTO_INCREMENT ,  `StemWord` varchar(30) NOT NULL, PRIMARY KEY (ID)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
				stmt.executeUpdate(sql);
				System.out.println("Table created!!!");
				sql= "CREATE TABLE `word_stemmer` ( `ID` int(20) NOT NULL AUTO_INCREMENT, `word_ID` int(30) NOT NULL, `Stem_ID` int(30) NOT NULL, PRIMARY KEY (ID)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
				stmt.executeUpdate(sql);
				sql= "ALTER TABLE `word_stemmer` ADD CONSTRAINT `wordstemmer_ibfk_1` FOREIGN KEY (`Stem_ID`) REFERENCES `stemmerlist` (`ID`),ADD CONSTRAINT `wordstemmer_ibfk_2` FOREIGN KEY (`word_ID`) REFERENCES `wordlist` (`ID`);";
				stmt.executeUpdate(sql);
				System.out.println("Table created!!!");
		 } catch (SQLException e) {
			e.printStackTrace();
		
    }
    }

    
}