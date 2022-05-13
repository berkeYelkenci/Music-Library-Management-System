package pack1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.JComboBox;

public class SeeArtists extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnGet_table;
	private JLabel lblNewLabel;
	private JButton btnGet_table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeeArtists frame = new SeeArtists();
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
	public SeeArtists() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 318, 252);
		contentPane.add(scrollPane);
		DefaultTableModel artistsModel = new DefaultTableModel();
		
		lblNewLabel = new JLabel(".");
		lblNewLabel.setBounds(446, 28, 300, 300);
		contentPane.add(lblNewLabel);
		
		table = new JTable(artistsModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int selectedRow = table.getSelectedRow();
				Object value = table.getModel().getValueAt(selectedRow, 4);
				int selectedCol = table.getSelectedColumn();
				
				try {
					Image img;
					img = ImageIO.read(new URL((String) value));
					ImageIcon icon = new ImageIcon(img);
					icon.setImage(img);
					lblNewLabel.setIcon(icon);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
			}
		});
		scrollPane.setViewportView(table);
		
		btnGet_table = new JButton("Get Table");
		btnGet_table.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					DBConnection DB = new DBConnection();
					ResultSet rs = DB.getTable("artists");
					artistsModel.setRowCount(0);
					int col = rs.getMetaData().getColumnCount();
					
					while (rs.next()) {
						
						Object [] row = new Object[col];
						
						for(int i=1 ; i<=col;i++) {
							
							row[i-1] = rs.getObject(i);
							
						}
						artistsModel.addRow(row);
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		btnGet_table.setBounds(338, 10, 98, 21);
		contentPane.add(btnGet_table);
		
		btnGet_table_1 = new JButton("Delete");
		btnGet_table_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(artistsModel.getRowCount()!=0) {
				int selected = table.getSelectedRow();
				Object value = table.getModel().getValueAt(selected, 0);
				DBConnection db = new DBConnection();
				try {
					String sql = "delete from artists where  idartists ="+value;
					db.deleteArtists(value);
					ResultSet rs = db.getTable("artists");
					artistsModel.setRowCount(0);
					int col = rs.getMetaData().getColumnCount();
					
					while (rs.next()) {
						
						Object [] row = new Object[col];
						
						for(int i=1 ; i<=col;i++) {
							
							row[i-1] = rs.getObject(i);
							
						}
						artistsModel.addRow(row);
					
				} 	}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
			}
				else
					JOptionPane.showMessageDialog(null, "Error");
			}
		});
		btnGet_table_1.setBounds(338, 40, 98, 21);
		contentPane.add(btnGet_table_1);
		
		JButton btnBack = new JButton("Back to Main Page");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPage mp = new MainPage();
				mp.setVisible(true);
				dispose();
				
			}
		});
		btnBack.setBounds(10, 275, 170, 21);
		contentPane.add(btnBack);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(219, 275, 85, 21);
		contentPane.add(btnExit);
		
		JButton btnNewButton = new JButton("Update ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBConnection db = new DBConnection();
				int selected,row,col;
				row = table.getSelectedRow();
			
				selected = (int) table.getValueAt(row, 0);
				
				String name      = 	(String) table.getValueAt(row, 1);
				String surname   =  (String) table.getValueAt(row, 2);
				int age 		 =  Integer.parseInt(table.getValueAt(row, 3).toString());
				String img   	 =  (String) table.getValueAt(row, 4);
			
				try {
					db.updateArtists(name,surname,age,img,selected);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
			
		
		});
		btnNewButton.setBounds(338, 71, 98, 21);
		contentPane.add(btnNewButton);
		artistsModel.setColumnIdentifiers(new String[] { "ID","Name","Surname","Age","link"});
	}
}
