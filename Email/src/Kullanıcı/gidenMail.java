package Kullanýcý;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;



public class gidenMail extends kullanici{
	private String text,alýcý,konu;
    public gidenMail(){}
	public gidenMail(String alýcý,String konu,String text) {
		super();
		this.alýcý = alýcý;
		this.konu=konu;
	    this.text = text;
	}

	public  ArrayList<gidenMail> getGidenKutusu(String mail) throws SQLException, ClassNotFoundException {
		ArrayList<gidenMail> gidenKutusu= new ArrayList<> ();
		 gidenMail gdnMail;
		  DBConnection conn= new DBConnection();
			 		Statement st=conn.connDb().createStatement();
			 	 	ResultSet rs;
			 	 	rs=st.executeQuery("SELECT * FROM gidenmail WHERE userMailAdres="+"'"+mail+"'");
			 	 	while(rs.next()){
						gdnMail=new gidenMail(rs.getString("name"),rs.getString("konu"),rs.getString("mail"));
						gidenKutusu.add(gdnMail);
						}
			     return gidenKutusu;
				}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAlýcý() {
		return alýcý;
	}
	public void setAlýcý(String alýcý) {
		this.alýcý = alýcý;
	}
	public String getKonu() {
		return konu;
	}
	public void setKonu(String konu) {
		this.konu = konu;
	}
	
}

