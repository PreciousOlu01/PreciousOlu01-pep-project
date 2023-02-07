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
    // public Message addMessage(Message message){
    //     List<Message> match = messageDao.getMessagesByGivenId(message.posted_by);
        
    //     String message_text= message.getMessage_text();

    //     if(message_text != "" && message.getMessage_text().length() <= 255 && match == null){
    //        return messageDao.createMessage(message);
    //     }
    //     return messageDao.createMessage(message);
    // }

    public Message addMessage(Message message){
        List<Message> match = messageDao.getMessagesByGivenId(message.posted_by);
        
        String message_text= message.getMessage_text();
        
        if(message_text != "" && message.getMessage_text().length() <= 255 && match == null){
           return messageDao.createMessage(message);
        }
        return null;
    }

    //get message by id
    public Message getOneMessageById(int id) {
        
        return messageDao.getOneMessageById(id);
    }

    //deleted message
    // public Message del(int id){
    //     Message message = messageDao.getOneMessageById(id);
    //     if(message == null){
    //         messageDao.deleteMessage(id);
    //         return messageDao.getOneMessageById(id);
    //     }
    //     System.out.println("now-deleted");
    //     return null;
    // }

    // public Message updateMessage(int id, Message message){
    //     Message messageId = messageDao.getOneMessageById(id); //change made
    //     if(messageId == null && message.getMessage_text() != null && message.getMessage_text().length()<= 255){
    //         messageDao.getMessageUpdate(id, message);
    //         return messageDao.getOneMessageById(id);  //change made
    //         // return null;
    //     }
    //     // messageDao.getMessageUpdate(id, message);
    //     // return messageDao.getOneMessageById(id);
    //     return null;
    // }

    // public Message updateMessage(int id, Message message){
    //     Message messageId = messageDao.getOneMessageById(id);
    //     if(messageId == null){
    //         return null;
    //     }
    //     else if( message.getMessage_text() != null && message.getMessage_text().length()<= 255){
    //         messageDao.getMessageUpdate(id, message);   
    //     }
    //     return messageDao.getOneMessageById(id);
    // }

    public Message updateMessage(int id, Message message){
        Message messageId = messageDao.getOneMessageById(id);
        if(messageId == null && message.getMessage_text()!= null & message.getMessage_text().length()<=255){
            messageDao.getMessageUpdate(id, messageId);
            return messageDao.getOneMessageById(id);
        }
        return null;
    }

    public List<Message> getAllByAccountId(int account_id){
        return messageDao.getMessagesByGivenId(account_id);   
    }

}