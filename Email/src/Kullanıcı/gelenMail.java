package Kullan�c�;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Helper.DBConnection;



public class gelenMail extends kullanici {
	private String text,g�nderen;
	private String konu;
    public gelenMail(){}
	public gelenMail(String g�nderen,String konu,String text) {
		super();
		
		this.g�nderen = g�nderen;
		this.konu=konu;
		this.text = text;
	}
	public  ArrayList<gelenMail> getGelenKutusu(String mail) throws SQLException, ClassNotFoundException {
		ArrayList<gelenMail> gelenKutusu= new ArrayList<> ();
		 gelenMail glnMail;
		  DBConnection conn= new DBConnection();
			 		Statement st=conn.connDb().createStatement();
			 	 	ResultSet rs;
			 	 	rs=st.executeQuery("SELECT * FROM gelenmail WHERE userMailAdres="+"'"+mail+"'");
			 	 	while(rs.next()){
						glnMail=new gelenMail(rs.getString("name"),rs.getString("konu"),rs.getString("mail"));
						gelenKutusu.add(glnMail);
						}
			     return gelenKutusu;
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
