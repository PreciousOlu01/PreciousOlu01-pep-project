package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
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

    public SocialMediaController(){
        accountService = new ServiceAccount();
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
        app.post("/login", this::postLoginHandler);

        return app;
    }
    
    private void getAllRegisteredHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapObj = new ObjectMapper();
        Account readAcc = mapObj.readValue(ctx.body(), Account.class);
        Account getAccount = accountService.allAccounts(readAcc);
        String strAccObj = mapObj.writeValueAsString(readAcc);
        ctx.result(strAccObj);
    }

    private void postRegisterHandler(Context ctx)throws JsonProcessingException{
        ObjectMapper mapObj = new ObjectMapper();
        Account account = mapObj.readValue(ctx.body(), Account.class);
        Account createdAcc = accountService.addAccount(account);
        ctx.json(mapObj.writeValueAsString(createdAcc));

        // if(createdAcc==null){
        //     ctx.json(mapObj.writeValueAsString(createdAcc));
        // }
        // else{
        //     ctx.status(400);
            
        // }
    }

    // private void postLoginHandler(Context ctx) throws JsonMappingException, JsonProcessingException{
    //     ObjectMapper mapObj = new ObjectMapper();
    //     Account readlogin = mapObj.readValue(ctx.body(),Account.class);
    //     String conUser = ctx.pathParam("username");
    //     String conPass = ctx.pathParam("password");
    //     Account readServiceAcc = accountService.userNameAndPassWordLogin(conUser, conPass);

    // }

}