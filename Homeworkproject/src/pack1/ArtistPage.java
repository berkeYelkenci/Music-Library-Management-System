package pack1;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ArtistPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtImage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArtistPage frame = new ArtistPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 

	/**
	 * Create the frame.
	 */
	public ArtistPage() {
		setTitle("Artist Page"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 53, 80, 26);
		contentPane.add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtName.setBounds(115, 53, 105, 26);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblArtistsInfos = new JLabel("Artist's Infos:");
		lblArtistsInfos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblArtistsInfos.setBounds(10, 10, 105, 26);
		contentPane.add(lblArtistsInfos);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSurname.setBounds(10, 103, 80, 26);
		contentPane.add(lblSurname);
		
		txtSurname = new JTextField();
		txtSurname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSurname.setColumns(10);
		txtSurname.setBounds(115, 103, 105, 26);
		contentPane.add(txtSurname);
		
		DefaultComboBoxModel  ageModel = new DefaultComboBoxModel();
		JComboBox cbAge = new JComboBox(ageModel);
		cbAge.setBounds(115, 154, 105, 26);
		contentPane.add(cbAge);
		
		for(int i=18;i<=80;i++) {
			
			ageModel.addElement(i+"");
		}
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAge.setBounds(10, 154, 80, 26);
		contentPane.add(lblAge);
		
		txtImage = new JTextField();
		txtImage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtImage.setColumns(10);
		txtImage.setBounds(115, 208, 418, 26);
		contentPane.add(txtImage);
		
		JButton btnNewButton = new JButton("Add into Database");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Artist art = new Artist();
				
				art.name = txtName.getText(); 
				art.surname = txtSurname.getText();
				art.age =Integer.parseInt(cbAge.getSelectedItem().toString());
				art.img = txtImage.getText();
				
				DBConnection db = new DBConnection();
				try {
					db.insertArtist(art);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(385, 283, 148, 26);
		contentPane.add(btnNewButton);
		
		JButton btnArtistManagment = new JButton("Edit Artists");
		btnArtistManagment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeeArtists sa;
				try {
					sa = new SeeArtists();
					sa.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnArtistManagment.setBounds(385, 319, 148, 26);
		contentPane.add(btnArtistManagment);
		
		JButton btnBackToMain = new JButton("Back to Main Menu");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPage mp = new MainPage();
				mp.setVisible(true);
				dispose();
			}
		});
		btnBackToMain.setBounds(385, 355, 148, 26);
		contentPane.add(btnBackToMain);
		
		JLabel lbl_Img = new JLabel("Img address");
		lbl_Img.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_Img.setBounds(10, 195, 90, 50);
		contentPane.add(lbl_Img);
		
		
	}
}
