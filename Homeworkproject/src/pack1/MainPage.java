package pack1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MainPage extends JFrame {
	JLabel lblNewLabel;
	private JPanel contentPane;
	
	public void setlabel(String label) {
		lblNewLabel.setText("Hi "+label);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setLabel(String name) {
		lblNewLabel.setText("Hi,"+name);
	}

	/**
	 * Create the frame.
	 */
	public MainPage() {
		setTitle("Main Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(10, 10, 74, 13);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Artist Page");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArtistPage ap = new ArtistPage();
				ap.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(10, 45, 166, 54);
		contentPane.add(btnNewButton);
		
		JButton btnArtistManagment = new JButton("Artist Management");
		btnArtistManagment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					SeeArtists seeArt = new SeeArtists();
					seeArt.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnArtistManagment.setBounds(260, 45, 166, 54);
		contentPane.add(btnArtistManagment);
		
		JButton btnSongPage = new JButton("Song Page");
		btnSongPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SongPage sp;
				try {
					sp = new SongPage();
					sp.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dispose();
				
			}
		});
		btnSongPage.setBounds(10, 155, 166, 54);
		contentPane.add(btnSongPage);
		
		JButton btnNewButton_2_1 = new JButton("Song Managment");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeeSongs ss;
				try {
					ss = new SeeSongs();
					ss.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dispose();
			}
		});
		btnNewButton_2_1.setBounds(260, 155, 166, 54);
		contentPane.add(btnNewButton_2_1);
		
		JButton btnNewButton_3 = new JButton("Exit");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_3.setBounds(351, 242, 85, 21);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("LogOut ");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame lf = new LoginFrame();
				lf.setVisible(true);
				dispose();
			}
		});
		btnNewButton_4.setBounds(260, 242, 85, 21);
		contentPane.add(btnNewButton_4);
	}
}
