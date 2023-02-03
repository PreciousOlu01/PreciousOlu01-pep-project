import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.eclipse.jetty.util.Scanner;

import Controller.SocialMediaController;
import DAO.AccountDao;
import Model.Account;
import Service.ServiceAccount;
import Util.ConnectionUtil;
import io.javalin.Javalin;

/**
 * This class is provided with a main method to allow you to manually run and test your application. This class will not
 * affect your program in any way and you may write whatever code you like here.
 */
public class Main {
    public static void main(String[] args) {
        // databaseSetup();
        SocialMediaController controller = new SocialMediaController();
        Javalin app = controller.startAPI();
        app.start(8080);

   }

//    public static void databaseSetup(){
//     try (java.util.Scanner scan = new java.util.Scanner(System.in)) {
//         System.out.println("Enter username: ");
//         String username = scan.nextLine();
//         System.out.println("Enter password: ");
//         String password = scan.nextLine();
//         // Account userAndPass = new Account(username,password);

//         Connection conn = ConnectionUtil.getConnection();
//         PreparedStatement ps1 = conn.prepareStatement("drop table if exists account");

//         ps1.executeUpdate();
//         PreparedStatement ps2 = conn.prepareStatement("create table account("+
//         "account_id int primary key auto_increment, " +
//         "username varchar(255), " + 
//         "password varchar(255);");

//         ps2.executeUpdate();

//         PreparedStatement ps3 = conn.prepareStatement("insert into account "+
//         "(username, password) values " + 
//         "(?,?);", Statement.RETURN_GENERATED_KEYS);
//         ps3.setString(1, username);
//         ps3.setString(2, password);

//         ps3.executeUpdate();

//     } catch(SQLException e){
//        e.printStackTrace();
//     }    
//     }
    
}  