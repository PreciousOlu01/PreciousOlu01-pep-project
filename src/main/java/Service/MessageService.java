package Service;

import java.util.ArrayList;
import java.util.List;

import DAO.AccountDao;
import DAO.MessageDao;
import Model.Message;

public class MessageService{
    MessageDao messageDao;

    public MessageService(){
        messageDao = new MessageDao();
    }

    public MessageService(MessageDao messageDao){
        this.messageDao = messageDao;
    }

    //get all messages
    public List<Message>getMessages(){
        return messageDao.getAlltMessage();
    }

    //add message

     public Message addMessage(Message message){
        List<Message>match = messageDao.getMessagesByGivenId(message.getPosted_by());

        String str= message.getMessage_text();
        
        // if(message.getMessage_text() != "" && message.getMessage_text().length() <= 255){
        //    return messageDao.createMessage(message);   
        // }
        // return null;

        if(str != "" && message.getMessage_text().length() <= 255 && match!=null){
            return messageDao.createMessage(message);   
         }
         return null;
    }

    //get message by id
    public Message getOneMessageById(int message_id) {
        
        return messageDao.getOneMessageById(message_id);
    }

    //deleted message
    public Message del(int id){
        Message msg = messageDao.getOneMessageById(id);
        
        if(msg != null){
           // System.out.println("now-deleted");
           messageDao.deleteMessage(id);
            return msg;
        }
        
        return null;
    }

    public Message updateMessage(int id, Message message){
        Message messageId = messageDao.getOneMessageById(id);
        String msg_text = message.getMessage_text();
        if(messageId != null && msg_text!= "" & message.getMessage_text().length()<=255){
            messageDao.getMessageUpdate(id, message);
            return this.messageDao.getOneMessageById(id);
        }
        return null;
    }

    public List<Message> getAllByAccountId(int account_id){
        return messageDao.getMessagesByGivenId(account_id);   
    }

}