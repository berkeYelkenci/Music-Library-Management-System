package pack1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class SongPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitle;
	private JTextField txtAlbum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SongPage frame = new SongPage();
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
	public SongPage() throws SQLException {
		setTitle("Song Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitle.setBounds(10, 26, 80, 26);
		contentPane.add(lblTitle);
		
		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTitle.setColumns(10);
		txtTitle.setBounds(115, 26, 105, 26);
		contentPane.add(txtTitle);
		
		JLabel lblAlbum = new JLabel("Album:");
		lblAlbum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlbum.setBounds(10, 76, 80, 26);
		contentPane.add(lblAlbum);
		
		txtAlbum = new JTextField();
		txtAlbum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAlbum.setColumns(10);
		txtAlbum.setBounds(115, 76, 105, 26);
		contentPane.add(txtAlbum);
		
		DefaultComboBoxModel yearModel = new DefaultComboBoxModel ();
		
		JComboBox cbYear = new JComboBox(yearModel);
		cbYear.setBounds(115, 127, 105, 26);
		contentPane.add(cbYear);
		
		for(int y = 1960 ; y<=2025; y++) {
			yearModel.addElement(y);
		}
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYear.setBounds(10, 127, 80, 26);
		contentPane.add(lblYear);
		
		JLabel lblArtist = new JLabel("Artist:");
		lblArtist.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblArtist.setBounds(10, 181, 80, 26);
		contentPane.add(lblArtist);
		
		DefaultComboBoxModel <String> modelArtist = new DefaultComboBoxModel<String> ();
		JComboBox cbArtist = new JComboBox(modelArtist);
		cbArtist.setBounds(115, 181, 105, 26);
		contentPane.add(cbArtist);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(251, 242, 187, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPage mp = new MainPage();
				mp.setVisible(true);
				dispose();
			}
		});
		
		JLabel lblGenre = new JLabel("Genre");
		lblGenre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGenre.setBounds(251, 26, 80, 26);
		contentPane.add(lblGenre);
		
		DefaultComboBoxModel<String> modelGenre = new DefaultComboBoxModel <String> ();
		JComboBox cbGenre = new JComboBox(modelGenre);
		cbGenre.setBounds(304, 31, 105, 21);
		contentPane.add(cbGenre);
		
		modelGenre.addElement("Rock");
		modelGenre.addElement("Pop");
		modelGenre.addElement("EuroBeat");
		modelGenre.addElement("Disco");
		modelGenre.addElement("Jazz");
		modelGenre.addElement("Blues");
		modelGenre.addElement("NightCore");
		modelGenre.addElement("Classic");
		
		
		
		JButton btnNewButton_2 = new JButton("Save to Database");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Song song = new Song();
				song.title = txtTitle.getText();
				song.artist = cbArtist.getSelectedItem().toString();
				song.year = Integer.parseInt(cbYear.getSelectedItem().toString());
				song.genre = cbGenre.getSelectedItem().toString();
				song.album = txtAlbum.getText();
				
				DBConnection db = new DBConnection();
				try {
					db.insertSongs(song);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(251, 180, 187, 21);
		contentPane.add(btnNewButton_2);
		
		JButton btnBackToMain = new JButton("Back to Main Page");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPage mp = new MainPage();
				mp.setVisible(true);
				dispose();
			}
		});
		btnBackToMain.setBounds(251, 211, 187, 21);
		contentPane.add(btnBackToMain);
		
		JButton btnNewButton_3 = new JButton("Go to Song Management");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeeSongs ss;
				try {
					ss = new SeeSongs();
					ss.setVisible(true);
				dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_3.setBounds(251, 149, 187, 21);
		contentPane.add(btnNewButton_3);
		
		
		
		DBConnection db = new DBConnection();
		ResultSet rs = db.getArtistName();
		ResultSet res = db.getArtistSname();
		int col = rs.getMetaData().getColumnCount();
		
		while(rs.next()&&res.next()){
			
			for(int i =1;i<=col ;i++) {
			String name =rs.getString(i);
			String surname = res.getString(i);
			modelArtist.addElement(name+" "+surname);
			}
			
		}
	}
}
