package pack1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.management.modelmbean.ModelMBean;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SeeSongs extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeeSongs frame = new SeeSongs();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public SeeSongs() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 701, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultTableModel songModel = new DefaultTableModel();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 86, 572, 256);
		contentPane.add(scrollPane);
		table = new JTable(songModel);
		scrollPane.setViewportView(table);
		DefaultComboBoxModel searchModel = new DefaultComboBoxModel();
		JComboBox cbSearch = new JComboBox(searchModel);
		cbSearch.setBounds(204, 55, 94, 21);
		contentPane.add(cbSearch);
		searchModel.addElement("Show All");
		searchModel.addElement("idsongs");
		searchModel.addElement("title");
		searchModel.addElement("artist");
		searchModel.addElement("year");
		searchModel.addElement("genre");
		searchModel.addElement("album");
		
		txtValue = new JTextField();
		txtValue.setBounds(4, 56, 112, 19);
		contentPane.add(txtValue);
		txtValue.setColumns(10);
		
		JButton btnGetTable = new JButton("Get Table");
		btnGetTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DBConnection db = new DBConnection();
				try {
					String table ="songs";
					String value = txtValue.getText();
					String slc=cbSearch.getSelectedItem().toString();
					
					String sql = null;
					
					if(slc=="artist") 			{
						sql = "Select * from songs where artist = '" + value + "'";
					}
					else if(slc == "title")	 	{
						sql = "Select * from songs where title = '" + value + "'";
					}
					else if(slc=="year") 		{
						sql = "Select * from songs where year = '" + value + "'";
					}
					else if(slc=="genre") 		{
						sql = "Select * from songs where genre = '" + value + "'";
					}
					else if(slc=="idsongs") 	{
						sql = "Select * from songs where idsongs = '" + value + "'";
					}
					else if(slc=="album") 		{
						sql = "Select * from songs where album = '" + value + "'";
					}
					else if (slc == "Show All")	{
						sql = "Select * from songs";
					}
					
					ResultSet rs=db.SearchArtist(value,sql);
					songModel.setRowCount(0);
					int col = rs.getMetaData().getColumnCount();
					
					while (rs.next()) {
						
						Object [] row = new Object[col];
						
						for(int i=1 ; i<=col;i++) {
							
							row[i-1] = rs.getObject(i);
							
						}
						songModel.addRow(row);
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
			}
		});
		btnGetTable.setBounds(313, 55, 122, 21);
		contentPane.add(btnGetTable);
		
		JLabel lblNewLabel = new JLabel("Search by:");
		lblNewLabel.setBounds(126, 59, 64, 13);
		contentPane.add(lblNewLabel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPage mn = new MainPage();
				mn.setVisible(true); 
				dispose();
			}
		});
		btnBack.setBounds(482, 408, 85, 21);
		contentPane.add(btnBack);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(592, 408, 85, 21);
		contentPane.add(btnNewButton);
		 
		JButton btnLogOut = new JButton("LogOut");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame lf = new LoginFrame();
				lf.setVisible(true);
				dispose();
			}
		});
		btnLogOut.setBounds(368, 408, 85, 21);
		contentPane.add(btnLogOut);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				
					
					DBConnection db = new DBConnection();
					int [] rows = table.getSelectedRows();
					
					for(int i = 0; i<rows.length;i++) {
						try {
						
						db.deleteSongs(table.getValueAt(rows[i]-i,0));
						songModel.removeRow(rows[i]-i);
						
						
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
					
			}
			
		});
		btnDelete.setBounds(592, 86, 85, 21);
		contentPane.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBConnection db = new DBConnection();
				int selected,row,col;
				row = table.getSelectedRow();
				
			
				selected = (int) table.getValueAt(row, 0);
				
				String title     = 	(String) table.getValueAt(row, 1);
				String artist    =  (String) table.getValueAt(row, 2);
				String genre     =  (String)table.getValueAt(row, 3);
				String album     =  (String) table.getValueAt(row, 4);
				int  year		 =  Integer.parseInt(table.getValueAt(row, 5).toString());
				
				try {   
					db.updateSongs(title,artist,genre,album,year,selected);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		btnUpdate.setBounds(592, 126, 85, 21);
		contentPane.add(btnUpdate);
		songModel.setColumnIdentifiers(new String[] { "ID","Title","Artist","Genre","Album","Year"});
		
		
	}
}
