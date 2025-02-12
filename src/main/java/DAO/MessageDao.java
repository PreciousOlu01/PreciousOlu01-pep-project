package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;


public class MessageDao {

    //create user message
    public Message createMessage(Message message){
        Connection conn= ConnectionUtil.getConnection();
        try{
            String sql = "insert into message (posted_by, message_text, time_posted_epoch) values (?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //setInt and SetString for preparedStatement
            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next()){
                int aut_id = (int)rs.getLong(1);
                return new Message(aut_id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    //read
    public List<Message> getAlltMessage(){
        Connection conn = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try{
            String sql = "SELECT * FROM message;";
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();

            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), 
                rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }


    //update message
    public void getMessageUpdate(int id, Message message){
        Connection conn = ConnectionUtil.getConnection();
        try{
            String sql= "UPDATE message SET message_text=? WHERE message_id=?;";
            PreparedStatement ps= conn.prepareStatement(sql);

            ps.setString(1, message.getMessage_text());
            ps.setInt(2, id);

            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }


    //get one message given message id
    public Message getOneMessageById(int message_id){
        Connection conn = ConnectionUtil.getConnection();
        try{
            String sql = "SELECT * FROM message WHERE message_id=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, message_id);

            ResultSet rs= ps.executeQuery();
            
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), 
                rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                return message;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    //delete message

    public Message deleteMessage(int id){
        Connection conn = ConnectionUtil.getConnection();
        try{
            String sql= "DELETE FROM message WHERE message_id=?;";    //change made-> added from clause
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
        
    }

    //get messages by given account_id

   public List<Message> getMessagesByGivenId(int account_id){
        Connection conn = ConnectionUtil.getConnection();
        List <Message> accountId = new ArrayList<>();
       
        try{
            String sql = "SELECT * FROM message WHERE posted_by=?;";    //Change made
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, account_id);

            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Message msg = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), 
                rs.getString("message_text"), rs.getLong("time_posted_epoch"));
                accountId.add(msg);
                // return accountId;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return accountId;
   }
}

