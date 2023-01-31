package Helper;

import javax.swing.JOptionPane;

import Kullanýcý.kullanici;



public class Helper {
 public static void showMsg(String str){
	 String msg;
	 switch (str){
	 case "fill":
		 msg= "Lütfen tüm alanlarý doldurunuz !";
		 break;
	 case "gönderildi":
		 msg="Ýleti Gönderildi !";
		 break;
	 case "basarili":
		 msg="Hesabýnýz oluþturuldu :)";
		 break;
	 case "hatali":
		 msg="E-posta adresiniz veya þifreniz yanlýþ !";
		 break;
	 case "gecersiz sifre":
		 msg="Sifreniz -büyük harf,küçük harf,rakam,-içermeli ve en az 8 karakterden oluþmalý !";
		 break;
	 case "gecersiz mail":
		 msg="Geçersiz e-posta adresi ! ";
		 break;
	 default:
		 msg=str;
	 }
	 JOptionPane.showMessageDialog(null,msg,"Mesaj",JOptionPane.INFORMATION_MESSAGE); 
 }
 public static boolean controlPassword(String password){
		boolean boyut=false;
		boolean bykHarf=false;
		boolean kckHarf=false;
		boolean rakam=false;

	 for (int i =0;i<password.length();i++){
		char c=password.charAt(i);
		if(password.length()>=8){
			boyut=true;
		}
		if(Character.isUpperCase(c)){
		    bykHarf=true;
		}
		if(Character.isLowerCase(c)){
			kckHarf=true;
		}
		if(Character.isDigit(c)){
			rakam=true;
		}}
	if(boyut&&bykHarf&&kckHarf&&rakam){
			return true;
		}
	else 
			return false;
 
 }
 public static boolean controlMail(String mail){
		if(mail.contains("@")&&mail.endsWith(".com")){
			return true;
		}
		else 
			return false;
}
}
 