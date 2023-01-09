package dataAccessLayer;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class DbFillTable {
	DbConnection conn = DbConnection.getInstance();
	Connection con = conn.getConnection();
	
	public ResultSet importTable(){
		String querySql = "SELECT wordlist.ID, wordlist.word, wordlist.diacritics, wordlist.meaning, wordlist.grammar, wordlist.origin, wordlist.gender, stemmerlist.StemWord FROM wordlist LEFT OUTER JOIN word_stemmer   ON wordlist.ID = word_stemmer.word_ID LEFT OUTER JOIN stemmerlist   ON stemmerlist.ID = word_stemmer.Stem_ID";
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
	}}
	
	


