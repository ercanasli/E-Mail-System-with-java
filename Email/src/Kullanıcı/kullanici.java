package Kullanýcý;

public  class kullanici {
	
    private String ad,soyad,mailAdres,sifre;
  
	
    public  kullanici(){}
 
    public kullanici(String ad, String soyad, String mailAdres, String sifre) {
	
	this.ad = ad;
	this.soyad = soyad;
	this.mailAdres = mailAdres;
	this.sifre = sifre;
}
public String getAd() {
	return ad;
}
 public void setAd(String ad) {
	this.ad = ad;
}
 public String getSoyad() {
	return soyad;
}
 public void setSoyad(String soyad) {
	this.soyad = soyad;
}
 public String getMailAdres() {
	return mailAdres;
}
 public void setMailAdres(String mailAdres) {
	this.mailAdres = mailAdres;
}
 public String getSifre() {
	return sifre;
}
 public void setSifre(String sifre) {
	this.sifre = sifre;
}
}
