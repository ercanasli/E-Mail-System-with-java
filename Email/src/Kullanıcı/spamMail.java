package Kullanýcý;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class spamMail extends kullanici {
	
	private String text,gönderen;
	private String konu;
	
	public spamMail(){}
	public spamMail(String text, String gönderen, String konu) {
		super();
		this.text = text;
		this.gönderen = gönderen;
		this.konu = konu;
	}
	
	public  ArrayList<spamMail> getSpamKutusu(String mail) throws SQLException, ClassNotFoundException {
		ArrayList<spamMail> spamKutusu= new ArrayList<> ();
		spamMail spmMail;
		  DBConnection conn= new DBConnection();
			 		Statement st=conn.connDb().createStatement();
			 	 	ResultSet rs;
			 	 	rs=st.executeQuery("SELECT * FROM spammail WHERE name="+"'"+mail+"'");
			 	 	while(rs.next()){
			 	 		spmMail=new spamMail(rs.getString("userMailAdres"),rs.getString("konu"),rs.getString("mail"));
						spamKutusu.add(spmMail);
						}
			     return spamKutusu;
				}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getGönderen() {
		return gönderen;
	}
	public void setGönderen(String gönderen) {
		this.gönderen = gönderen;
	}
	public String getKonu() {
		return konu;
	}
	public void setKonu(String konu) {
		this.konu = konu;
	}
	

}
