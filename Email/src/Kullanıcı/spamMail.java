package Kullan�c�;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class spamMail extends kullanici {
	
	private String text,g�nderen;
	private String konu;
	
	public spamMail(){}
	public spamMail(String text, String g�nderen, String konu) {
		super();
		this.text = text;
		this.g�nderen = g�nderen;
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
	public String getG�nderen() {
		return g�nderen;
	}
	public void setG�nderen(String g�nderen) {
		this.g�nderen = g�nderen;
	}
	public String getKonu() {
		return konu;
	}
	public void setKonu(String konu) {
		this.konu = konu;
	}
	

}
