package dataAccessLayerTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dataAccessLayer.ImportData;

class ImportDataTestCases {

	ImportData s = new ImportData(0);

	@Test 
	public void testSQLExceptionFalse() {
	  boolean thrown = true;
	  try {
	    s.importData("Masdar.csv", "Mafoul.csv", "Faeel.csv");
	  } catch (Exception e) {
	    thrown = false;
	  }
	  assertTrue(thrown);
	}

	@Test
	public void testSQLExceptionTrue() {
	  boolean thrown = false;
	  try {
	    s.importData("Masdar.csv", "Mafoul.csv", "Faeel.csv");
	  } catch (Exception e) {
	    thrown = true;
	  }
	  assertFalse(thrown);
	}
}
