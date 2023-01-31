package Kullanýcý;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Helper.DBConnection;



public class gelenMail extends kullanici {
	private String text,gönderen;
	private String konu;
    public gelenMail(){}
	public gelenMail(String gönderen,String konu,String text) {
		super();
		
		this.gönderen = gönderen;
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
