package Helper;
import java.sql.*;
public class DBConnection {
	String url = "jdbc:mysql://localhost:3306/eposta?";
    String user = "root";
    String password = "asli";
	Connection c= null;
 public DBConnection(){}
 public  Connection connDb() throws ClassNotFoundException, SQLException {
       this.c=DriverManager.getConnection(url,user,password);
		 return c;
	 }}
