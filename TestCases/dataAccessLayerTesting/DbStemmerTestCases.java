package dataAccessLayerTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dataAccessLayer.DbStemmer;

class DbStemmerTestCases {

	DbStemmer DbStemmer_obj =new DbStemmer();
	
	@Test
	@DisplayName("Word returns 0 if not found in database")
	void idCheckTestCaseNotFound() {
		assertSame(0,DbStemmer_obj.getID("qwerty"));
	}
	
	@Test
	@DisplayName("ID returned is correct")
	void idCheckTestCase() {
		
		assertEquals(3,DbStemmer_obj.getID("تَأْسِيسٌ"));
	}

}
