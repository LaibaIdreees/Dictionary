package buissnesslayer;

import dataAccessLayer.ImportData;

public class BlImportData {

	 public void importData(String file) {
		ImportData importObject= new ImportData();
		importObject.importData(file);
	}
}
