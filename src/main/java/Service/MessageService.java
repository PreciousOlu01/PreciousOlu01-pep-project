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

    // public List<Message>getMessages(){
    //     List<Message> listMessages = new ArrayList<>();
    //     listMessages = messageDao.getAlltMessage();
    //     // Message message = messageDao.getMessageById(get)
    //     for(Message msg : listMessages){
    //         if (msg == null){
    //             return null;
    //         }
    //     }
    //     return messageDao.getAlltMessage();
    // }

    public List<Message>getMessages(){
        return messageDao.getAlltMessage();
    }

    //get all messages
    // public List<Message>getMessages(){
    //     List<Message>listmessages = new ArrayList<>();
    //     listmessages = messageDao.getAlltMessage();

    //     for(Message list : listmessages){
    //         if(list == null){
    //             return null;
    //         }
    //         // listmessages = messageDao.getAlltMessage();
    //     }
    //     return listmessages;
    // }

    //add message
    public Message addMessage(Message message){

        // Message messages = messageDao.getMessageById(message.getMessage_id());
        // Message match = messageDao.getMessageByAccountId(message.getPosted_by());
        // Message match = messageDao.getMessagesByGivenId(message.posted_by);
        List<Message> match = messageDao.getMessagesByGivenId(message.posted_by);
    
        
        if(message.getMessage_text() != null && message.getMessage_text().length() <= 255 && match == null){
           return messageDao.createMessage(message);
        }
        return null;
    }

    //get message by id
    public Message getMessageById(int id) {
        
        return messageDao.getMessageById(id);
    }

    //deleted message
    public Message del(int id){
        messageDao.deleteMessage(id);
        return messageDao.getMessageById(id);
        
    }

    public Message updateMessage(int id, Message message){
        Message messageId = messageDao.getMessageById(id);
        if(messageId == null && message.getMessage_text() != null && message.getMessage_text().length()<= 255){
            messageDao.getMessageUpdate(id, message);
            return messageDao.getMessageById(id);
           
        }
        return null;
    }

    public List<Message> getAllByAccountId(int account_id){
        // return messageDao.getMessagesByGivenId(account_id);
        List<Message> list = new ArrayList<>();
        list = messageDao.getMessagesByGivenId(account_id);
        if(list == null){
            return null;
        }
        return messageDao.getMessagesByGivenId(account_id);
       
    }
}