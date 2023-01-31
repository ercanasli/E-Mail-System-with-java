import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import Helper.DBConnection;
import Helper.*;
import Kullanýcý.kullanici;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.UIManager;


public class singIn extends JFrame {
	private JPanel wpane;
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldMail;
	private JPasswordField passwordFieldPassword;
	DBConnection conn= new DBConnection();
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					singIn frame = new singIn();
					frame.setVisible(true);
					} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public singIn() {
		setTitle("Kayýt Ekraný");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400,500);
		wpane = new JPanel();
		wpane.setBackground(UIManager.getColor("menu"));
		wpane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(wpane);
		wpane.setLayout(null);
		
		JLabel lblName = new JLabel("Ad :");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblName.setBounds(15, 16, 69, 29);
		wpane.add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setBackground(UIManager.getColor("text"));
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldName.setBounds(15, 50, 341, 43);
		wpane.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblSurname = new JLabel("Soyad :");
		lblSurname.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSurname.setBounds(15, 94, 100, 29);
		wpane.add(lblSurname);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setBackground(UIManager.getColor("text"));
		textFieldSurname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldSurname.setBounds(15, 125, 341, 43);
		wpane.add(textFieldSurname);
		textFieldSurname.setColumns(10);
		
		JLabel lblEposta = new JLabel("E-posta :");
		lblEposta.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEposta.setBounds(15, 172, 115, 29);
		wpane.add(lblEposta);
		
		textFieldMail = new JTextField();
		textFieldMail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldMail.setBounds(15, 208, 341, 43);
		wpane.add(textFieldMail);
		textFieldMail.setColumns(10);
		
		JLabel lblSifre = new JLabel("Sifre :");
		lblSifre.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSifre.setBounds(15, 250, 69, 29);
		wpane.add(lblSifre);
		
		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordFieldPassword.setBounds(15, 279, 341, 43);
		wpane.add(passwordFieldPassword);
		
		JButton btnKayýt = new JButton("Kayýt Ol");
		btnKayýt.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnKayýt.addActionListener( new ActionListener(){
			   public void actionPerformed(ActionEvent e) {
				   String sqlSorgu;
				   if(textFieldName.getText().length()==0 || textFieldSurname.getText().length()==0||textFieldMail.getText().length()==0||passwordFieldPassword.getText().length()==0){
						Helper.showMsg("fill");
					}
				   else if( !Helper.controlMail(textFieldMail.getText())){
					   Helper.showMsg("gecersiz mail");
				   }
				   else if (!Helper.controlPassword(passwordFieldPassword.getText())){
					   Helper.showMsg("gecersiz sifre");
				   }
				   else{
				    kullanici user1= new kullanici(textFieldName.getText(),textFieldSurname.getText(),textFieldMail.getText(),passwordFieldPassword.getText());
				   sqlSorgu="INSERT INTO user (name,surname,mail,password) VALUES ("+"'"+user1.getAd()+"',"+"'"+user1.getSoyad()+"',"+"'"+user1.getMailAdres()+"',"+"'"+user1.getSifre()+"')";
				   try {
				     	Statement st=conn.connDb().createStatement();
						st.executeUpdate(sqlSorgu);
						Helper.showMsg("basarili");
						 textFieldName.setText(null);
					     textFieldSurname.setText(null);
						 textFieldMail.setText(null);
						 passwordFieldPassword.setText("");
						System.out.println("KAYIT OLDU!");
				   }
				   catch (SQLException e1) {
						e1.printStackTrace();
					}
				   catch (ClassNotFoundException e1) {
						
						e1.printStackTrace();
					}
				      }}});
		btnKayýt.setBounds(15, 338, 138, 58);
		wpane.add(btnKayýt);
		JButton btnBack = new JButton("Geri Dön");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		         loginPage lgn=new loginPage();
		         lgn.setVisible(true);
		         dispose();
			}});
		
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBack.setBounds(218, 338, 138, 58);
		wpane.add(btnBack);
	
		
		}
}
