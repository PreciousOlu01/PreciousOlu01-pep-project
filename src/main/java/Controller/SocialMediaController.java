package Controller;

import java.util.List;

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
        
        app.get("/register",this::getAllRegisteredHandler);
        app.post("/register",this::postRegisterHandler);
        // app.post("/login", this::postLoginHandler);
        app.post("/messages", this::postMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.patch("/messages/{message_id}", this::patchUpdateMessage);
        // app.get("/login", this::getLoginHandler);

        return app;
    }
    
    private void getAllRegisteredHandler(Context ctx) throws JsonProcessingException{
        List<Account>accounts= accountService.allAccounts();
        ctx.json(accounts);
    }

    private void postRegisterHandler(Context ctx)throws JsonProcessingException{
        ObjectMapper mapObj = new ObjectMapper();
        Account account = mapObj.readValue(ctx.body(), Account.class);
        Account createdAcc = accountService.addAccount(account);
        // ctx.json(mapObj.writeValueAsString(createdAcc));

        if(createdAcc==null){
            ctx.status(400);
            
        }
        else{
            ctx.json(mapObj.writeValueAsString(createdAcc));
            
        }
    }

    // private void postLoginHandler(Context ctx) throws JsonMappingException, JsonProcessingException{
    //     ObjectMapper mapObj = new ObjectMapper();
    //     Account readlogin = mapObj.readValue(ctx.body(),Account.class);
    //     // String conUser = ctx.pathParam("username");
    //     // String conPass = ctx.pathParam("password");
    //     // Account combo = new Account(conUser,conPass);
    //     Account readServiceAcc = accountService.userLogin(readlogin.getUsername(), readlogin.getPassword());

    //     if(readServiceAcc== null){
    //         ctx.json(mapObj.writeValueAsString(readServiceAcc));
    //     }
    //     else{
    //         ctx.status(401);
    //     }
    // }

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
    
    private void getAllMessagesHandler(Context ctx) throws JsonMappingException{
        List<Message>messages = messageService.getMessages();
        ctx.json(messages);
    }

    private void getMessageByIdHandler(Context ctx) throws JsonProcessingException{
        int messId = Integer.parseInt(ctx.pathParam("message_id"));
        ctx.json(messageService.getMessageById(messId));
    }
    
    //if message existed the response body should contain now deleted message. the response 
    //status should be 200
    //if the message did not exist the response should be 200, but the response body should be empty.
    private void deleteMessageHandler(Context ctx) throws JsonProcessingException{
        // int deleteId = Integer.parseInt(ctx.pathParam("message_id"));
        // ctx.json(deleteId);
        ObjectMapper obj = new ObjectMapper(); 
        Message message = obj.readValue(ctx.body(), Message.class);
        int deleteId = Integer.parseInt(ctx.pathParam("message_id"));
        Message delet = messageService.del(deleteId,message);

        if(delet == null){
            ctx.status(200);
        }
        else{
            ctx.json(obj.writeValueAsString(delet));
        }
    }

    private void patchUpdateMessage(Context ctx) throws JsonProcessingException{
        ObjectMapper objMapper = new ObjectMapper();
        Message message = objMapper.readValue(ctx.body(), Message.class);

        ctx.contentType("application/Json");

        int messId = Integer.parseInt(ctx.pathParam("message_id"));
        Message updatedMessages = messageService.updateMessage(messId, message);
        
        if(updatedMessages == null){
            // ctx.json(objMapper.writeValueAsString(updatedMessages));
            ctx.status(400);
        }
        else{
            ctx.json(objMapper.writeValueAsString(updatedMessages));

        }
    }

}