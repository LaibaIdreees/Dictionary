package application;

import java.awt.EventQueue;

import buissnesslayer.BlStemmer;
import dataAccessLayer.DbImportSQL;
import dataAccessLayer.ImportData;
import presentationLayer.ViewTable;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewTable frame = new ViewTable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
