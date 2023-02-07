package Controller;

import java.util.List;

import org.eclipse.jetty.util.security.Password;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.MessageService;
import Service.ServiceAccount;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    ServiceAccount accountService;
    MessageService messageService;

    public SocialMediaController(){
        accountService = new ServiceAccount();
        messageService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        
        // app.get("/register",this::getAllRegisteredHandler);
        app.post("/register",this::postRegisterHandler);
        app.post("/login", this::postLoginHandler);
        app.post("/messages", this::postMessageHandler);
        app.patch("/messages/{message_id}", this::patchUpdateMessage);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        // app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByGivenId);
        
        // app.get("/login", this::getLoginHandler);

        return app;
    }
    
    // private void getAllRegisteredHandler(Context ctx) throws JsonProcessingException{
    //     List<Account>accounts= accountService.allAccounts();
    //     ctx.json(accounts);
    // }

    private void postRegisterHandler(Context ctx)throws JsonProcessingException{
        ObjectMapper mapObj = new ObjectMapper();
        Account account = mapObj.readValue(ctx.body(), Account.class);
        Account createdAcc = accountService.addAccount(account);

        if(createdAcc!=null){
            
            ctx.json(mapObj.writeValueAsString(createdAcc));
            ctx.status(200);
        }
        else{
            ctx.status(400);
        }
    }

    private void postLoginHandler(Context ctx) throws JsonMappingException, JsonProcessingException{
        ObjectMapper mapObj = new ObjectMapper();
        Account readlogin = mapObj.readValue(ctx.body(),Account.class);
        // String username = 
        Account readServiceAcc = accountService.userLogin(readlogin);

        if(readServiceAcc== null){
            ctx.status(401);
        }
        else{
            ctx.json(mapObj.writeValueAsString(readServiceAcc));
            ctx.status(200);
        }
    }

    // private void getLoginHandler(Context ctx) throws JsonProcessingException{
    //     ctx.json(accountService.userLogin(ctx.pathParam("username"), ctx.pathParam("password")));
    // }

    private void postMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapObj = new ObjectMapper();
        Message message = mapObj.readValue(ctx.body(), Message.class);
        Message readMessage = messageService.addMessage(message);

        if(readMessage == null){
           ctx.status(400);
        }
        else{
            ctx.json(mapObj.writeValueAsString(readMessage));
        }
    }
    
    //get all messages
    private void getAllMessagesHandler(Context ctx){
        List<Message>messages = messageService.getMessages();
        ctx.json(messages); 
    }

    //get message by id
    private void getMessageByIdHandler(Context ctx) {
        int messId = Integer.parseInt(ctx.pathParam("message_id"));
        ctx.json(messageService.getOneMessageById(messId));                 //change made
        ctx.status(200);
    }
    
    //if message existed the response body should contain now deleted message. the response 
    //status should be 200
    //if the message did not exist the response should be 200, but the response body should be empty.

    //delete not working
    // private void deleteMessageHandler(Context ctx) throws JsonProcessingException{
    //     int deleteId = Integer.parseInt(ctx.pathParam("message_id"));
    //     ctx.json(messageService.del(deleteId));

    // }

    private void patchUpdateMessage(Context ctx) throws JsonProcessingException{
        ObjectMapper objMapper = new ObjectMapper();
        Message message = objMapper.readValue(ctx.body(), Message.class);

        // ctx.contentType("application/Json");

        int messId = Integer.parseInt(ctx.pathParam("message_id"));
        Message updatedMessages = messageService.updateMessage(messId, message);
        
        System.out.println(updatedMessages);          //change made
        if(updatedMessages == null){
            // ctx.json(objMapper.writeValueAsString(updatedMessages));
            ctx.status(400);
        }
        else{
            ctx.json(objMapper.writeValueAsString(updatedMessages));    //changes made
            // String convertStr = objMapper.writeValueAsString(updatedMessages);
            // ctx.result(convertStr);

        }
    }

    private void getAllMessagesByGivenId(Context ctx) throws JsonProcessingException{
        int messageId= Integer.parseInt(ctx.pathParam("messageId"));
        List<Message>messages = messageService.getAllByAccountId(messageId);
        ctx.json(messages);
    }
}