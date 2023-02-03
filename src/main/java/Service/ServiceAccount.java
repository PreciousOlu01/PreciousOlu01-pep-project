package Service;

import java.util.List;

import org.eclipse.jetty.util.security.Password;

import DAO.AccountDao;
import Model.Account;

public class ServiceAccount{
    AccountDao accountDao;

    public ServiceAccount(){
        accountDao = new AccountDao();     
    }

    ServiceAccount(AccountDao accountDao){
        this.accountDao = accountDao;
    }

    public List<Account>allAccounts(){
        //returns all info in the account;
        return this.accountDao.getAllAccounts();
    }

    public Account addAccount(Account account){
        Account accounts= accountDao.getAccountByUserName(account.getUsername());
        /*checks if user is blank, if user is not blank and password is greater than or equal to 4,
         * then check if the account is blank, if it is blank it means the username and info
         * does not exist, so we can persist our data in d db.
        */
        if(account.getUsername() != null && account.getPassword().length() >= 4){
            if(accounts == null){
                return accountDao.createAccount(account);
            }
        }
        return null;
        
    }

    // public Account userLogin(Account account){
    //     Account accounts = accountDao.getAccountByUserName(account.getUsername());
    //     String userName= account.getUsername();
    //     String userPassword = account.getPassword();
    //     if(accounts.equals(userName) && accounts.equals(userPassword)){
    //         return accountDao.getUserNameAndPassword(userName, userPassword);
    //     }
    // }

    // public Account userLogin(String username, String password){
    //    Account accountExist = accountDao.getUserNameAndPassword(username, password);
    //    if(username == null && password == null){
    //         if(accountExist != null){
    //             return accountDao.getAccountByUserName(username);
    //         }
    //    }
    //    return null;     
    // }
    
    // public Account userLogin(Account account){
    //     Account match = null;
    //     Account accountExist = accountDao.getAccountByUserName(account.getUsername());
        
    //      if(accountExist.getUsername().equals(account.username) && accountExist.getPassword().equals(account.password)){
    //          match = accountExist;
    //      }
    //      return match;
    //  }

   

}