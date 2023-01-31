import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;

import Helper.DBConnection;
import Helper.Helper;
import Kullanýcý.gelenMail;
import Kullanýcý.gidenMail;
import Kullanýcý.kullanici;
import Kullanýcý.spamMail;

import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Statement;


public class homePage extends JFrame {
    static kullanici user= new kullanici();
    static gelenMail g=new gelenMail();
	static gidenMail g1=new gidenMail();
    static spamMail s=new spamMail();

	private JPanel contentPane;
	private JTextField textFieldAlici;
	private JTextField textFieldKonu;
	private DefaultTableModel gelenModel=null;
	private DefaultTableModel gidenModel=null;
	private DefaultTableModel spamModel=null;

	private Object[] gData=null;
	private JTable tableGiden;
	private JTable tableGelen;
	DBConnection conn= new DBConnection();
	private JTextField textFieldSpamMail;
	private JTable tableSpam;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					homePage frame = new homePage(user,g,g1,s);
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
	 * @throws ClassNotFoundException 
	 */

	public homePage(kullanici user,gelenMail g,gidenMail g1,spamMail s) throws ClassNotFoundException, SQLException {
		 gelenModel=new DefaultTableModel();
		 String[] colName= new String[3];
		 colName[0]="Ýsim";
		 colName[1]="Konu";
		 colName[2]="E-Posta";
		 gelenModel.setColumnIdentifiers(colName);
		 gData=new Object[3];
		 for(int i=0;i<g.getGelenKutusu(user.getMailAdres()).size();i++){
			 gData[0]=g.getGelenKutusu(user.getMailAdres()).get(i).getGönderen();
			 gData[1]=g.getGelenKutusu(user.getMailAdres()).get(i).getKonu();
			 gData[2]=g.getGelenKutusu(user.getMailAdres()).get(i).getText();
			 gelenModel.addRow(gData); }
		 gidenModel=new DefaultTableModel();
		 colName[0]="Ýsim";
		 colName[1]="Konu";
		 colName[2]="E-Posta";
		 gidenModel.setColumnIdentifiers(colName);
		 for(int i=0;i<g1.getGidenKutusu(user.getMailAdres()).size();i++){
			 gData[0]=g1.getGidenKutusu(user.getMailAdres()).get(i).getAlýcý();
			 gData[1]=g1.getGidenKutusu(user.getMailAdres()).get(i).getKonu();
			 gData[2]=g1.getGidenKutusu(user.getMailAdres()).get(i).getText();
			 gidenModel.addRow(gData); }
		 spamModel=new DefaultTableModel();
		 colName[0]="Ýsim";
		 colName[1]="Konu";
		 colName[2]="E-Posta";
		 spamModel.setColumnIdentifiers(colName);
		 for(int i=0;i<s.getSpamKutusu(user.getMailAdres()).size();i++){
			 gData[0]=s.getSpamKutusu(user.getMailAdres()).get(i).getGönderen();
			 gData[1]=s.getSpamKutusu(user.getMailAdres()).get(i).getKonu();
			 gData[2]=s.getSpamKutusu(user.getMailAdres()).get(i).getText();
			 spamModel.addRow(gData); }
		setTitle("Mail");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 16));
		tabbedPane.setToolTipText("");
		tabbedPane.setBounds(15, 61, 526, 421);
		contentPane.add(tabbedPane);
		
		JPanel panelGelen = new JPanel();
		tabbedPane.addTab("Gelen Kutusu", null, panelGelen, null);
		panelGelen.setLayout(null);
		
		JScrollPane scrollPaneGelen = new JScrollPane();
		scrollPaneGelen.setBounds(0, 0, 521, 387);
		panelGelen.add(scrollPaneGelen);
		
		tableGelen = new JTable(gelenModel);
		tableGelen.setEnabled(false);
		scrollPaneGelen.setViewportView(tableGelen);
		
		JPanel panelGiden = new JPanel();
		tabbedPane.addTab("Giden Kutusu", null, panelGiden, null);
		panelGiden.setLayout(null);
		
		JScrollPane scrollPaneGiden = new JScrollPane();
		scrollPaneGiden.setBounds(0, 0, 521, 387);
		panelGiden.add(scrollPaneGiden);
		
		tableGiden = new JTable(gidenModel);
		tableGiden.setEnabled(false);
		scrollPaneGiden.setViewportView(tableGiden);
		
		JPanel panel_iletiGönder = new JPanel();
		tabbedPane.addTab("Ýleti Gönder", null, panel_iletiGönder, null);
		panel_iletiGönder.setLayout(null);
		
		JLabel lblAlc = new JLabel("Alýcý :");
		lblAlc.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAlc.setBounds(15, 16, 83, 20);
		panel_iletiGönder.add(lblAlc);
		
		JLabel lblKonu = new JLabel("Konu :");
		lblKonu.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblKonu.setBounds(10, 52, 61, 20);
		panel_iletiGönder.add(lblKonu);
		
		JTextArea textAreaMail = new JTextArea();
		textAreaMail.setFont(new Font("Tahoma", Font.ITALIC, 16));
		textAreaMail.setBounds(63, 91, 360, 184);
		panel_iletiGönder.add(textAreaMail);
		
		textFieldAlici = new JTextField();
		textFieldAlici.setFont(new Font("Tahoma", Font.ITALIC, 20));
		textFieldAlici.setBounds(63, 13, 360, 26);
		panel_iletiGönder.add(textFieldAlici);
		textFieldAlici.setColumns(10);
		
		textFieldKonu = new JTextField();
		textFieldKonu.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		textFieldKonu.setBounds(63, 49, 360, 26);
		panel_iletiGönder.add(textFieldKonu);
		textFieldKonu.setColumns(10);
		
		JButton btnGnder = new JButton("Gönder");
		btnGnder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldAlici.getText().length()==0 || textFieldKonu.getText().length()==0||textAreaMail.getText().length()==0){
					Helper.showMsg("fill");
				}
				else if(!Helper.controlMail(textFieldAlici.getText())){
					Helper.showMsg("gecersiz mail");
				}
				else{
				String sqlSorgu,sqlSorgu1; 
				sqlSorgu="INSERT INTO gelenMail (userMailAdres,name,konu,mail) VALUES ("+"'"+textFieldAlici.getText()+"',"+"'"+user.getMailAdres()+"',"+"'"+textFieldKonu.getText()+"',"+"'"+textAreaMail.getText()+"')";
				sqlSorgu1="INSERT INTO gidenMail (userMailAdres,name,konu,mail) VALUES ("+"'"+user.getMailAdres()+"',"+"'"+textFieldAlici.getText()+"',"+"'"+textFieldKonu.getText()+"',"+"'"+textAreaMail.getText()+"')";	
				try {
				     	Statement st=conn.connDb().createStatement();
						st.executeUpdate(sqlSorgu);
						System.out.println("Gönderildi");
						st.executeUpdate(sqlSorgu1);
						System.out.println("Kaydedildi");
						Helper.showMsg("gönderildi");
						updateGidenModel(user);
						textFieldAlici.setText(null);
						textFieldKonu.setText(null);
						textAreaMail.setText(null);
						   }
				   catch (SQLException e1) {
						e1.printStackTrace();
					}
				   catch (ClassNotFoundException e1) {
						
						e1.printStackTrace();
					}
				
			}}
		});
		btnGnder.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGnder.setBounds(309, 276, 119, 27);
		panel_iletiGönder.add(btnGnder);
		
		JLabel lblIleti = new JLabel("Ýleti :");
		lblIleti.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIleti.setBounds(15, 88, 69, 20);
		panel_iletiGönder.add(lblIleti);
		
		JPanel panelSpam = new JPanel();
		panelSpam.setLayout(null);
		tabbedPane.addTab("Spam Kutusu", null, panelSpam, null);
		
		JLabel lblGnderen = new JLabel("Gönderen :");
		lblGnderen.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGnderen.setBounds(15, 16, 90, 20);
		panelSpam.add(lblGnderen);
		
		textFieldSpamMail = new JTextField();
		textFieldSpamMail.setFont(new Font("Tahoma", Font.ITALIC, 20));
		textFieldSpamMail.setColumns(10);
		textFieldSpamMail.setBounds(120, 13, 386, 26);
		panelSpam.add(textFieldSpamMail);
		
		JButton buttonEkle = new JButton("Ekle");
		buttonEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {if(textFieldSpamMail.getText().length()==0 ){
				Helper.showMsg("fill");
			}
			else if(!Helper.controlMail(textFieldSpamMail.getText())){
				Helper.showMsg("gecersiz mail");
			}
			else{
			String sqlSorgu,sqlSorgu1; 
			sqlSorgu="INSERT INTO spamMail (userMailAdres,name,konu,mail) VALUES ("+"'"+textFieldSpamMail.getText()+"',"+"'"+user.getMailAdres()+"',"+"'"+textFieldKonu.getText()+"',"+"'"+textAreaMail.getText()+"')";
			sqlSorgu1="INSERT INTO gelenMail (userMailAdres,name,konu,mail) VALUES ("+"'"+user.getMailAdres()+"',"+"'"+textFieldAlici.getText()+"',"+"'"+textFieldKonu.getText()+"',"+"'"+textAreaMail.getText()+"')";	
			try {
			     	Statement st=conn.connDb().createStatement();
					st.executeUpdate(sqlSorgu);
					System.out.println("Gönderildi");
					st.executeUpdate(sqlSorgu1);
					System.out.println("Kaydedildi");
					Helper.showMsg("gönderildi");
					updateGidenModel(user);
					textFieldSpamMail.setText(null);
					
					   }
			   catch (SQLException e1) {
					e1.printStackTrace();
				}
			   catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
			
		}
				
			}
		});
		buttonEkle.setFont(new Font("Tahoma", Font.BOLD, 16));
		buttonEkle.setBounds(342, 59, 119, 27);
		panelSpam.add(buttonEkle);
		
		tableSpam = new JTable();
		tableSpam.setBounds(0, 118, 521, 269);
		panelSpam.add(tableSpam	);
		
		
		
		JLabel lblHosgeldin = new JLabel("Hoþgeldin,"+ user.getAd());
		lblHosgeldin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblHosgeldin.setBounds(15, 5, 244, 40);
		contentPane.add(lblHosgeldin);
		
		JButton btncýkýs = new JButton("Çýkýþ Yap");
		btncýkýs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginPage logn=new loginPage();
		         logn.setVisible(true);
		         dispose();
			}
		});
		btncýkýs.setFont(new Font("Tahoma", Font.BOLD, 18));
		btncýkýs.setBounds(335, 16, 128, 24);
		contentPane.add(btncýkýs);}
	public void updateGidenModel(kullanici user) throws ClassNotFoundException, SQLException{
		DefaultTableModel clearModel=(DefaultTableModel) tableGiden.getModel();
		clearModel.setRowCount(0);
		 for(int i=0;i<g1.getGidenKutusu(user.getMailAdres()).size();i++){
			 gData[0]=g1.getGidenKutusu(user.getMailAdres()).get(i).getAlýcý();
			 gData[1]=g1.getGidenKutusu(user.getMailAdres()).get(i).getKonu();
			 gData[2]=g1.getGidenKutusu(user.getMailAdres()).get(i).getText();
			 gidenModel.addRow(gData); }
	}
	public void updateGelenModel(kullanici user) throws ClassNotFoundException, SQLException{
		DefaultTableModel clearModel=(DefaultTableModel) tableGelen.getModel();
		clearModel.setRowCount(0);
		 for(int i=0;i<g.getGelenKutusu(user.getMailAdres()).size();i++){
			 gData[0]=g.getGelenKutusu(user.getMailAdres()).get(i).getGönderen();
			 gData[1]=g.getGelenKutusu(user.getMailAdres()).get(i).getKonu();
			 gData[2]=g.getGelenKutusu(user.getMailAdres()).get(i).getText();
			 gelenModel.addRow(gData); }
	}
	}

