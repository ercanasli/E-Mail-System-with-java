import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import Helper.DBConnection;
import Helper.Helper;
import Kullanýcý.gelenMail;
import Kullanýcý.gidenMail;
import Kullanýcý.kullanici;
import Kullanýcý.spamMail;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JCheckBox;


public class loginPage extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldMail;
	private JPasswordField passwordField;
	 DBConnection conn= new DBConnection();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginPage frame = new loginPage();
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
	public loginPage() {
		setTitle("Giriþ Ekraný");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGiris = new JLabel("Sisteme Giriþ");
		lblGiris.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblGiris.setBounds(122, 0, 146, 47);
		contentPane.add(lblGiris);
		
		JLabel lblEposta = new JLabel("E-Posta :");
		lblEposta.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEposta.setBounds(15, 54, 107, 26);
		contentPane.add(lblEposta);
		
		textFieldMail = new JTextField();
		textFieldMail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textFieldMail.setBounds(15, 84, 200, 29);
		contentPane.add(textFieldMail);
		textFieldMail.setColumns(10);
		
		JLabel lblsifre = new JLabel("Þifre :");
		lblsifre.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblsifre.setBounds(15, 126, 107, 29);
		contentPane.add(lblsifre);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		passwordField.setBounds(15, 159, 200, 29);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Oturum Aç");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldMail.getText().length()==0 || passwordField.getText().length()==0){
					Helper.showMsg("fill");
				}
				else{	
					boolean success=true;
					try{
					    Statement st=conn.connDb().createStatement();
						ResultSet rs=st.executeQuery("SELECT * FROM user");
						while(rs.next()){
							if(textFieldMail.getText().equals(rs.getString("mail"))&&passwordField.getText().equals(rs.getString("password"))){
								success=false;
								kullanici kullanici1=new kullanici();
								gelenMail g=new gelenMail();
								gidenMail g1=new gidenMail();
							    spamMail s=new spamMail();
								kullanici1.setAd(rs.getString("name"));
								kullanici1.setSoyad(rs.getString("surname"));
								kullanici1.setMailAdres(rs.getString("mail"));
								kullanici1.setSifre(rs.getString("password"));
								homePage hmPage= new homePage(kullanici1,g,g1,s);
								hmPage.setVisible(true);
								dispose();
							    System.out.println("giriþ yapýldý");
							    System.out.println(kullanici1.getAd());
							    break;}
								}
						  if(success){
							Helper.showMsg("hatali");
							}}
						
					
					catch (ClassNotFoundException e1) {
						
						e1.printStackTrace();
					}
					catch (SQLException e1) {
						e1.printStackTrace();
					}
					}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLogin.setBounds(37, 199, 146, 45);
		contentPane.add(btnLogin);
		
		JButton btnKayýtOl = new JButton("Kayýt Ol");
		btnKayýtOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 singIn newuser= new singIn();
				   newuser.setVisible(true);
				   dispose();
			}	
		});
		btnKayýtOl.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnKayýtOl.setBounds(265, 199, 136, 45);
		contentPane.add(btnKayýtOl);
		
		JCheckBox chckBxSifreShow = new JCheckBox("Þifreyi Göster");
		chckBxSifreShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckBxSifreShow.isSelected()){
					passwordField.setEchoChar((char)0);
				}
				else{
					passwordField.setEchoChar('*');

				}
			}
		});
		chckBxSifreShow.setBounds(226, 159, 139, 29);
		contentPane.add(chckBxSifreShow);
		
		
	}
}
