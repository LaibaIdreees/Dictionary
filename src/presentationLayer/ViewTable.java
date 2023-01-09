package presentationLayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import buissnesslayer.BlFillTable;
import buissnesslayer.BlImportData;
import buissnesslayer.BlSearchByText;
import buissnesslayer.BlSearchRoot;
import buissnesslayer.BlSearchWord;
import buissnesslayer.BlStemmer;
import dataAccessLayer.DbConnection;
import dataAccessLayer.DbImportSQL;
import dataAccessLayer.DbSearchRoot;

@SuppressWarnings("serial")
public class ViewTable extends JFrame {
	DbConnection conn = DbConnection.getInstance();
	Connection con = conn.getConnection();
	private JPanel contentPane;
	private JTable table, table2;
	private JTextField textField;
	DefaultTableModel model;
	BlSearchWord s = new BlSearchWord();
	BlSearchRoot s1 = new BlSearchRoot();
	DbSearchRoot d =new DbSearchRoot(); 
	BlFillTable f = new BlFillTable();
	BlImportData i = new BlImportData();
	boolean flag=true, flag2=true;
	/**
	 * Launch the application.
	 */

	

	/*
	 * @Author Saim Anjum 20F-0326
	 * Isn't it obvious?
	 */
	@SuppressWarnings("serial")
	public ViewTable() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 800);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(20, 24, 33));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Arabic Dictionary");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 50));
		lblNewLabel.setBounds(10, 15, 864, 70);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("SAVE TABLE");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(46, 52, 66));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        Statement st;
		        DefaultTableModel model = (DefaultTableModel) table.getModel();
		        try {
		            st = con.createStatement();
		            for(int i = 0; i < model.getRowCount(); i++){
		                int id = Integer.valueOf(model.getValueAt(i, 0).toString());
		                String word = model.getValueAt(i,1).toString();
		                String diacritics = model.getValueAt(i,2).toString();
		                String meaning = model.getValueAt(i,3).toString();
		                String grammar = model.getValueAt(i,4).toString();
		                String origin = model.getValueAt(i,5).toString();
		                String gender = model.getValueAt(i,6).toString();
		                String number = model.getValueAt(i,7).toString();
		                String updateQuery = "UPDATE `wordlist` SET `ID`='"+id+"', `word`='"+word+"',`diacritics`='"+diacritics+"',`meaning`='"+meaning+"',`grammar`='"+grammar+"',`origin`='"+origin+"',`gender`='"+gender+"',`number`='"+number+"' WHERE `ID`='"+id+"'";
		                st.addBatch(updateQuery);
		                
		            }
		            
		            int[] updatedRow = st.executeBatch();
		            System.out.println("Updated rows: "+updatedRow.length);
		        } catch (SQLException ex) {
		        }
			}
		});
		btnNewButton.setBounds(633, 199, 241, 45);
		contentPane.add(btnNewButton);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setForeground(new Color(255, 255, 255));
		scrollPane.setBackground(new Color(46, 52, 66));
		scrollPane.setBounds(10, 86, 611, 270);
		contentPane.add(scrollPane);
		table = new JTable();
		table.setBackground(new Color(46, 52, 66));
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setForeground(new Color(255, 255, 255));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Word", "Diacritics", "Meaning", "Grammar", "Origin", "Gender", "Number", "Stem"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(7).setResizable(false);
		table.getColumnModel().getColumn(8).setResizable(false);

		JButton btnNewButton_1 = new JButton("LOAD TABLE");
		btnNewButton_1.setBackground(new Color(46, 52, 66));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					fillTable();
					
			}});
		btnNewButton_1.setBounds(633, 255, 241, 45);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("SEARCH BY WORD");
		btnNewButton_2.setBackground(new Color(46, 52, 66));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String word = textField.getText();
					int j = s.searchWord(word);
					DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
					 Object[] row = new Object[9];
					
					for (int i = 0; i < j; i++)
					{
						    row[0] = s.ID.get(i);
			                row[1] = s.words.get(i);
			                row[2] = s.diacritics.get(i);
			                row[3] = s.meaning.get(i);
			                row[4] = s.grammar.get(i); 
			                row[5] = s.origin.get(i);
			                row[6] = s.gender.get(i);
			                row[7] = s.number.get(i);
			                row[8] = s.stem.get(i);
					    model2.addRow(row);
					}
		}});
		
		
		btnNewButton_2.setBounds(633, 537, 241, 45); 
		contentPane.add(btnNewButton_2);
		
		textField = new JTextField();
		textField.setBackground(new Color(46, 52, 66));
		textField.setForeground(new Color(255, 255, 255));
		textField.setBounds(10, 423, 864, 45);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(new Color(46, 52, 66));
		scrollPane_1.setForeground(new Color(255, 255, 255));
		scrollPane_1.setBounds(10, 480, 611, 270);
		contentPane.add(scrollPane_1);
		 table2 = new JTable();
		 table2.setForeground(new Color(255, 255, 255));
		 table2.setBackground(new Color(46, 52, 66));
		scrollPane_1.setViewportView(table2);
		table2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Word", "Diacritics", "Meaning", "Grammar", "Origin", "Gender", "Number", "Stem"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table2.getColumnModel().getColumn(0).setResizable(false);
		table2.getColumnModel().getColumn(1).setResizable(false);
		table2.getColumnModel().getColumn(2).setResizable(false);
		table2.getColumnModel().getColumn(3).setResizable(false);
		table2.getColumnModel().getColumn(4).setResizable(false);
		table2.getColumnModel().getColumn(5).setResizable(false);
		table2.getColumnModel().getColumn(6).setResizable(false);
		table2.getColumnModel().getColumn(7).setResizable(false);
		table2.getColumnModel().getColumn(8).setResizable(false);
		
		JButton btnNewButton_3 = new JButton("INITIALZE DATABASE");
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBackground(new Color(46, 52, 66));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel panel = new JPanel();
		        panel.add(new JLabel("Enter file path:"));
		        panel.add(new JTextField(10));
		      
		        String file = null;
		        int result = JOptionPane.showConfirmDialog(null, panel, "Enter File Locations", JOptionPane.OK_CANCEL_OPTION);
		        if (result == JOptionPane.OK_OPTION) {
		             file = ((JTextField) panel.getComponent(1)).getText();
		        
		        }
			
				BlImportData importData = new BlImportData();
				importData.importData(file);
			}
		});
		btnNewButton_3.setBounds(633, 85, 241, 45);
		contentPane.add(btnNewButton_3);
		
		
		//@Author
		//Haris 20F-0279
		//Added functionality: "Search by word"		
		/*update log*/
		//@Author		
		//Saim Anjum 20F-0326
		//Added functionality: "Search by root"
		
		
		JButton btnNewButton_4 = new JButton("SEARCH BY ROOT");
		btnNewButton_4.setBackground(new Color(46, 52, 66));
		btnNewButton_4.setForeground(new Color(255, 255, 255));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String word = textField.getText();
				int j = s1.searchRoot(word);
				DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
				 Object[] row = new Object[9];
				
				for (int i = 0; i < j; i++)
				{
					    row[0] = s1.ID.get(i);
		                row[1] = s1.word.get(i);
		                row[2] = s1.diacritics.get(i);
		                row[3] = s1.meaning.get(i);
		                row[4] = s1.grammar.get(i); 
		                row[5] = s1.origin.get(i);
		                row[6] = s1.gender.get(i);
		                row[7] = s1.number.get(i);	
		                row[8] = s1.stem.get(i);	
				    model2.addRow(row);
				}
			}
		});
		btnNewButton_4.setBounds(633, 593, 241, 45);
		contentPane.add(btnNewButton_4);
		
		//@Author
				//Saim Anjum 20F-0326
				//Added functionality: "Update"		
				/*update log*/
				//@Author		
				//Haris 20F-0279 
				//Added functionality: "Update by root"
		
		
		JButton btnNewButton_5 = new JButton("SAVE TABLE");
		btnNewButton_5.setBackground(new Color(46, 52, 66));
		btnNewButton_5.setForeground(new Color(255, 255, 255));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        Statement st;
		        DefaultTableModel model = (DefaultTableModel) table2.getModel();
		        try {
		            st = con.createStatement();
		            for(int i = 0; i < model.getRowCount(); i++){
		                int id = Integer.valueOf(model.getValueAt(i, 0).toString());
		                String word = model.getValueAt(i,1).toString();
		                String diacritics = model.getValueAt(i,2).toString();
		                String meaning = model.getValueAt(i,3).toString();
		                String grammar = model.getValueAt(i,4).toString();
		                String origin = model.getValueAt(i,5).toString();
		                String gender = model.getValueAt(i,6).toString();
		                String number = model.getValueAt(i,7).toString();
		                String updateQuery = "UPDATE `wordlist` SET `ID`='"+id+"', `word`='"+word+"',`diacritics`='"+diacritics+"',`meaning`='"+meaning+"',`grammar`='"+grammar+"',`origin`='"+origin+"',`gender`='"+gender+"',`number`='"+number+"' WHERE `ID`='"+id+"'";
		                st.addBatch(updateQuery);
		                
		            }
		            
		            int[] updatedRow = st.executeBatch();
		            System.out.println("Updated rows: "+updatedRow.length);
		        } catch (SQLException ex) {
		        }
				}
		});
		btnNewButton_5.setBounds(633, 649, 241, 45);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_1_1 = new JButton("REFRESH");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				Statement ps;
		        ResultSet rs;
		        try {
		            ps = con.createStatement();
		    		String querySql = "SELECT wordlist.ID, wordlist.word, wordlist.diacritics, wordlist.meaning, wordlist.grammar, wordlist.origin, wordlist.gender,wordlist.number, stemmerlist.StemWord FROM wordlist LEFT OUTER JOIN word_stemmer   ON wordlist.ID = word_stemmer.word_ID LEFT OUTER JOIN stemmerlist   ON stemmerlist.ID = word_stemmer.Stem_ID";

		            rs = ps.executeQuery(querySql);

		            while(rs.next()){
		                Object[] row = new Object[9];
		                row[0] = rs.getInt("wordlist.ID");
		                row[1] = rs.getString("wordlist.Word");
		                row[2] = rs.getString("wordlist.Diacritics");
		                row[3] = rs.getString("wordlist.Meaning");
		                row[4] = rs.getString("wordlist.Grammar");
		                row[5] = rs.getString("wordlist.Origin");
		                row[6] = rs.getString("wordlist.Gender");
		                row[7] = rs.getString("wordlist.Number");
		                row[8] = rs.getString("stemmerlist.StemWord");
		                model.addRow(row);
		            }
		        } catch (SQLException ex) {
		            System.out.println(ex.getMessage());
		        }
			}
		});
		btnNewButton_1_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1_1.setBackground(new Color(46, 52, 66));
		btnNewButton_1_1.setBounds(633, 311, 241, 45);
		contentPane.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setBackground(new Color(46, 52, 66));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 30));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(10, 367, 864, 45);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_5_1 = new JButton("CLEAR");
		btnNewButton_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) table2.getModel();
				model.setRowCount(0);
			}
		});
		btnNewButton_5_1.setForeground(Color.WHITE);
		btnNewButton_5_1.setBackground(new Color(46, 52, 66));
		btnNewButton_5_1.setBounds(633, 705, 241, 45);
		contentPane.add(btnNewButton_5_1);
		
		JButton btnNewButton_2_1 = new JButton("SEARCH BY SENTENCE");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BlSearchByText search = new BlSearchByText();
				String text = textField.getText();
				int j = search.textToWord(text);
				BlSearchWord se=new BlSearchWord();
				se=search.returnList();
				System.out.println(j);
					DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
					 Object[] row = new Object[8];
					
					for (int i = 0; i < j; i++)
					{
						    row[0] = se.ID.get(i);
			                row[1] = se.words.get(i);
			                row[2] = se.diacritics.get(i);
			                row[3] =se.meaning.get(i);
			                row[4] =se.grammar.get(i); 
			                row[5] =se.origin.get(i);
			                row[6] = se.gender.get(i);
			                row[7] = se.number.get(i);
			                
					    model2.addRow(row);
					}
			}});
		btnNewButton_2_1.setForeground(Color.WHITE);
		btnNewButton_2_1.setBackground(new Color(46, 52, 66));
		btnNewButton_2_1.setBounds(633, 480, 241, 45);
		contentPane.add(btnNewButton_2_1);
		
		JButton btnNewButton_3_1 = new JButton("STEMMERS");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BlStemmer bl=new BlStemmer();
				bl.stemWords();
			}
		});
		btnNewButton_3_1.setForeground(Color.WHITE);
		btnNewButton_3_1.setBackground(new Color(46, 52, 66));
		btnNewButton_3_1.setBounds(633, 142, 241, 45);
		contentPane.add(btnNewButton_3_1);
	}
	
	
	
	public void fillTable(){
        Statement ps;
        ResultSet rs;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        try {
            ps = con.createStatement();
    		String querySql = "SELECT wordlist.ID, wordlist.word, wordlist.diacritics, wordlist.meaning, wordlist.grammar, wordlist.origin, wordlist.gender,wordlist.number, stemmerlist.StemWord FROM wordlist LEFT OUTER JOIN word_stemmer   ON wordlist.ID = word_stemmer.word_ID LEFT OUTER JOIN stemmerlist   ON stemmerlist.ID = word_stemmer.Stem_ID";

            rs = ps.executeQuery(querySql);

            while(rs.next()){
                Object[] row = new Object[9];
                row[0] = rs.getInt("wordlist.ID");
                row[1] = rs.getString("wordlist.Word");
                row[2] = rs.getString("wordlist.Diacritics");
                row[3] = rs.getString("wordlist.Meaning");
                row[4] = rs.getString("wordlist.Grammar");
                row[5] = rs.getString("wordlist.Origin");
                row[6] = rs.getString("wordlist.Gender");
                row[7] = rs.getString("wordlist.Number");
                row[8] = rs.getString("stemmerlist.StemWord");
                model.addRow(row);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
	
}