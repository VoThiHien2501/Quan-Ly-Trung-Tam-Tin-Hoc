/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.*;
/**
 *
 * @author ASUS
 */
public class KetNoiCSDL {
     public static Connection openConnection() throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        System.out.println("loaded...");
        
         String url="jdbc:sqlserver://localhost:1433;databaseName=QL_TRUNGTAMTINHOC;encrypt=true;trustServerCertificate=true";
         String user="sa";
         String password="123";
         
         Connection con = DriverManager.getConnection(url, user, password);            
         System.out.println("Connecting...");
         return con; 
    }
}
