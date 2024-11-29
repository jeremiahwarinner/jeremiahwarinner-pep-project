package Service;
import Model.Account;
import DAO.AccountDAO;
public class AccountService {
    AccountDAO accountdao;

    public AccountService(){
        accountdao = new AccountDAO();
    }
    public AccountService(AccountDAO accountdao){
        this.accountdao = accountdao;
    }
    public Account registerAccount(Account account){
        try{
            if(account.getPassword().length()>=4 && account.getUsername() != "" && accountdao.checkAccount(account) == null){
                return accountdao.insertAccount(account);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public Account loginAccount(Account account){
        try{
        return accountdao.loginAccount(account);
        }
        catch(Exception E){
            System.out.println(E.getMessage());
        }
        return null;
    }
}
