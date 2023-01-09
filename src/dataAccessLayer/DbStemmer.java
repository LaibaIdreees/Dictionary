package dataAccessLayer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class DbStemmer {
		
	DbConnection conn = DbConnection.getInstance();
	Connection con = (Connection) conn.getConnection();
	
	public ResultSet returnMashkool() {
		String querySql = "SELECT ID, word FROM wordlist";
		Statement statement;
		ResultSet result = null;
		try { 
			statement = (Statement) con.createStatement();
		} catch (SQLException e1) { 
			e1.printStackTrace();
		}
		 try {
			statement = (Statement) con.createStatement();
			 result = (ResultSet) statement.executeQuery(querySql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void inputStemmer(int id, String stem) {
		 PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement("INSERT INTO stemmerlist (ID, stemWord)  VALUES (?,?)");
			pstmt.setInt(1, id);
			pstmt.setString(2, stem);
			int rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
        	
	}
	
	public void inputStemmerToWord(int id, int wordid, int stemid) {
		 PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement("INSERT INTO word_stemmer (ID, word_ID,stem_ID)  VALUES (?,?,?)");
			pstmt.setInt(1, id);
			pstmt.setInt(2, wordid);
			pstmt.setInt(3, stemid);
			int rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
       	
	}
	
	public int getID(String word) {
		String querySql = "SELECT ID FROM wordlist WHERE word LIKE '%" + word+ "%'";
		Statement statement;
		ResultSet result = null;
		int id=0;
		try { 
			statement = (Statement) con.createStatement();

			statement = (Statement) con.createStatement();
			 result = (ResultSet) statement.executeQuery(querySql);
			
		} catch (SQLException e1) { 
			e1.printStackTrace();
		}
		
		try {
			while (result.next()) {
				id = result.getInt("ID");
				        
	}
		}catch(SQLException e1) { 
			e1.printStackTrace();
			}
	
		return id;
		}
}

