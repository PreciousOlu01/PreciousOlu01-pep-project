package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDao {

    /**get account using username and password */
    public Account getUserNameAndPassword(Account account){
        Connection conn= ConnectionUtil.getConnection();
        try{
            String sqlString = "SELECT * FROM account where username=? and password =?";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
            //get preparedStatement
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next()){
                Account accounts= new Account(rs.getInt("account_id"), rs.getString("username"),rs.getString("password"));
                return accounts;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**the method allows users to insert account in an existing table*/

    public Account insertAccount(Account account){
        //create a connection
        Connection conn = ConnectionUtil.getConnection();
        try{
            String sqlString= "INSERT INTO account(username, password) VALUES (?,?);";
            PreparedStatement preparedStatement= conn.prepareStatement(sqlString, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2,account.getPassword());   

            preparedStatement.executeUpdate();

            ResultSet pKey=preparedStatement.getGeneratedKeys();
            if(pKey.next()){
                int generateId =(int)pKey.getLong(1);
                return new Account(generateId, account.getUsername(),account.getPassword());
            }       
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    //getAccount by userName.
    public Account getAccountByUserName(String username){
        Connection conn = ConnectionUtil.getConnection();
        try{
            String strSql= "SELECT * FROM account WHERE username=?;";
            PreparedStatement ps = conn.prepareStatement(strSql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
                return account;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    //getAccount by id
    public Account getAccountById(int id){
        Connection conn = ConnectionUtil.getConnection();
        try{
            String strSql= "SELECT * FROM account WHERE account_id=?;";
            PreparedStatement ps = conn.prepareStatement(strSql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
                return account;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
