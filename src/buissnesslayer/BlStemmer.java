package buissnesslayer;

import java.util.HashMap;
import java.util.Set;

import com.github.msarhan.lucene.ArabicRootExtractorStemmer;

import dataAccessLayer.DbStemmer;

public class BlStemmer {
	HashMap<Integer, String> stemming = new HashMap<Integer, String>();
	Set<String> tempWord;
	ArabicRootExtractorStemmer stemmer = new ArabicRootExtractorStemmer();
	DbStemmer stemmerObject = new DbStemmer();
	BlFillTable fillTableObject = new BlFillTable();
	 
	public void stemWords() {
        int index=0, index2=0;
        fillTableObject.fillTable();
		 for(int i=0; i<fillTableObject.ID.size(); i++ ) {
	     tempWord = stemmer.stem((fillTableObject.words.get(i)));
         int wordid = stemmerObject.getID(fillTableObject.words.get(i));
         for (String str : tempWord) {
			 index++;
			 stemmerObject.inputStemmer(index, str);
			 index2++;
			 stemmerObject.inputStemmerToWord(index2, wordid, index);
				System.out.println("dope!!!");
		 }
			 }
			System.out.println("hugia bro!");

		 }


	public String getStemWord(String word) {
	   Set<String> steming = stemmer.stem((word));
	   String stem=null; 
	   for (String str : steming) 
		    stem=str;
	   System.out.println(stem);
		return stem;
	}
	
}

