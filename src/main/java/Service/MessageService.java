package Service;

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

    public List<Message>getMessages(){
        if(messageDao.getAlltMessage()==null){
            return null;
        }
        return messageDao.getAlltMessage();
    }


    public Message addMessage(Message message){

        // Message messages = messageDao.getMessageById(message.getMessage_id());
        Message match = messageDao.getMessageByAccountId(message.getPosted_by());
        
        if(message.getMessage_text()==null && message.getMessage_text().length() <= 255 && match == null){
           return messageDao.createMessage(message);
        }
        return null;
    }

    public Message getMessageById(int id) {
        
        return messageDao.getMessageById(id);
    }

    public Message del(int id, Message message){
        message = messageDao.getMessageById(id);
        if(message == null){
            messageDao.deleteMessage(id);
        }
        System.out.println("now-deleted");
        return messageDao.getMessageById(id);
    }

    public Message updateMessage(int id, Message message){
        Message messageId = messageDao.getMessageById(id);
        if(messageId == null && message.getMessage_text()==null && message.getMessage_text().length()<= 255){
            return messageDao.getMessageUpdate(id, message);
            // return messageDao.getMessageByAccountId(id);
        }
        return null;

    }
}