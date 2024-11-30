package Service;
import Model.Message;
import DAO.MessageDAO;
import DAO.AccountDAO;
import java.util.List;
public class MessageService {
    
    public MessageDAO messagedao;
    public MessageService(){
         messagedao = new MessageDAO();
    }
    public MessageService(MessageDAO messagedao){
        this.messagedao = messagedao;
    }

    public Message postMessage(Message message){
        AccountDAO accountdao = new AccountDAO();
        try{
            
            if(message.getMessage_text().length() <=255 && accountdao.checkAccountById(message.getPosted_by()) != null && message.getMessage_text() != ""){
               return messagedao.insertMessage(message); 
            }

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public List<Message> getAllMessages(){
            return messagedao.getAllMessages();
    }
    public Message getMessageById(int id){
            return messagedao.getMessageById(id);
    }
    public Message deleteMessageById(int id){
        return messagedao.deleteMessageById(id);
    }
    public void updateMessageById(int id, Message message){
        if(message.getMessage_text().length() <= 255 && messagedao.getMessageById(id) != null )
        messagedao.updateMessageById(id, message);
    }
    public List<Message> getMesssagesByAccountId(int id){
        return messagedao.getMessagesByAccountId(id);
    }



}
