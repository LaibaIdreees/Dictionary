package dataAccessLayer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import org.apache.ibatis.jdbc.ScriptRunner;

import com.mysql.jdbc.Connection;

public class DbImportSQL {
		DbConnection conn = DbConnection.getInstance();
		Connection con = (Connection) conn.getConnection();
		
		public void runScript() {
		
			 ScriptRunner sr = new ScriptRunner(con);
		      Reader reader = null;
			try {
				reader = new BufferedReader(new FileReader("dictionary.sql"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      //Running the script
		      sr.runScript(reader);
		}
}
