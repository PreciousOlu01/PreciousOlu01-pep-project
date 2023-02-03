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
        return messageDao.getAlltMessage();
    }

    // public Message addMessage(Message message){

    //     Message messages = messageDao.getMessageById(message.getMessage_id());
    //     // Message match = null;
        
    //     if(message==null && message.getMessage_text().length() <= 255){
    //         if(messages == messageDao.getMessageByAccountId(message.getPosted_by())){
    //             return messageDao.createMessage(message);
    //         }
    //     }
    //     return null;
    // }

    public Message addMessage(Message message){

        // Message messages = messageDao.getMessageById(message.getMessage_id());
        // Message match = messageDao.getMessageByAccountId(message.getPosted_by());
        
        if(message.getMessage_text()==null && message.getMessage_text().length() <= 255 && message.getPosted_by()!=0){
           return messageDao.createMessage(message);
        }
        return null;
    }

    public Message getMessageById(int id) {
        return messageDao.getMessageById(id);
    }

    public void del(int id){
        messageDao.deleteMessage(id);
    }

    public Message updateMessage(int id, Message message){
        Message messageId = messageDao.getMessageById(id);
        if(message.getMessage_text() != null && message.getMessage_text().length() <= 255 && messageId!=null){
            messageDao.getMessageUpdate(id, messageId);
            return messageDao.getMessageById(id);
        }
        return null;

    }
}