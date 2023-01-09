package buissnesslayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataAccessLayer.DbFillTable;
import dataAccessLayer.DbSearchWord;
import presentationLayer.ViewTable;

public class BlFillTable {
	public ArrayList<String> ID = new ArrayList<String>();
	public ArrayList<String> words = new ArrayList<String>();
	public ArrayList<String> diacritics = new ArrayList<String>();
	public ArrayList<String> meaning = new ArrayList<String>();
	public ArrayList<String> grammar = new ArrayList<String>();
	public ArrayList<String> origin = new ArrayList<String>();
	public ArrayList<String> gender = new ArrayList<String>();
	public ArrayList<String> number = new ArrayList<String>();
	public ArrayList<String> stem = new ArrayList<String>();
	DbFillTable fillObject = new DbFillTable();
	public int fillTable() {
			int increment=0;
			ResultSet result = fillObject.importTable();
			try {
					while (result.next()) {
						   this.ID.add(result.getString("wordlist.ID"));
							this.words.add(result.getString("wordlist.word"));
							this.diacritics.add(result.getString("wordlist.diacritics"));
							this.meaning.add(result.getString("wordlist.meaning"));
							this.grammar.add(result.getString("wordlist.grammar"));
							this.origin.add(result.getString("wordlist.origin"));
							this.gender.add(result.getString("wordlist.gender"));
							this.number.add(result.getString("wordlist.number"));
							this.stem.add(result.getString("stemmerlist.StemWord"));
							increment++;
					 }
				} catch (SQLException e) {
					e.printStackTrace();
				}	

			return increment;
				 }
			 }
	
	
	

