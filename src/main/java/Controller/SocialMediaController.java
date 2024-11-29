package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;



/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountservice;
    MessageService messageservice;
    public SocialMediaController(){
        accountservice = new AccountService();
        messageservice = new MessageService();

    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postRegisterHandler);
        app.post("/login",this::postLoginHandler);
        app.post("/messages",this::postMessagesHandler);
        app.get("/messages",this::getMessagesHandler);
        app.get("/messages/{id}", this::getMessagesByIdHandler);
        app.delete("/messages/{id}", this::deleteMessagesByIdHandler);
        app.patch("/messages/{id}", this::patchMessagesByIdHandler);
        app.patch("/accounts/{account_id}/messages",this::getMessagesByAccountIdHandler);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }
    private void postRegisterHandler(Context ctx) throws JsonProcessingException{
            ObjectMapper mapper = new ObjectMapper();
            Account account = mapper.readValue(ctx.body(), Account.class);
            Account addedaccount = accountservice.registerAccount(account);
            if(addedaccount==null){
                ctx.status(400);
            }else{
                ctx.json(mapper.writeValueAsString(addedaccount));
            }
        
    }
    private void postLoginHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account logaccount = accountservice.loginAccount(account);
        if(logaccount==null){
            ctx.status(401);
        }else{
            ctx.json(mapper.writeValueAsString(logaccount));
        }
    }
    private void postMessagesHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message addedmessage = messageservice.postMessage(message);
        if(addedmessage==null){
            ctx.status(400);
        }else{
            ctx.json(mapper.writeValueAsString(addedmessage));
        }
    }
    private void getMessagesHandler(Context ctx){
        ctx.json(messageservice.getAllMessages());
    }
    private void getMessagesByIdHandler(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        Message message = messageservice.getMessageById(id);
        if (message != null) {
            ctx.json(message);
            ctx.status(200);
        } else {
            ctx.status(200);
            ctx.json("");
        }


    }
    private void deleteMessagesByIdHandler(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        if(messageservice.getMessageById(id) == null){
            ctx.status(200);
        }
        else{
            messageservice.deleteMessageById(id);
        }
    }
    private void patchMessagesByIdHandler(Context ctx){

    }
    private void getMessagesByAccountIdHandler(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        ctx.json(messageservice.getMesssagesByAccountId(id));
        ctx.status(200);

    }
    


}