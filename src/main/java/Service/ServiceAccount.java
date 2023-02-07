package Service;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Authentication;
import org.eclipse.jetty.util.security.Password;

import DAO.AccountDao;
import Model.Account;

public class ServiceAccount{
    private AccountDao accountDao;        //change here

    public ServiceAccount(){
        accountDao = new AccountDao();     
    }

    public ServiceAccount(AccountDao accountDao){
        this.accountDao = accountDao;
    }

    public List<Account>allAccounts(){
        //returns all info in the account;
        return this.accountDao.getAllAccounts();
    }

    // public Account addAccount(Account account){
    //     Account accounts= accountDao.getAccountByUserName(account.getUsername());

    //     /*checks if user is blank, if user is not blank and password is greater than or equal to 4,
    //      * then check if the account is blank, if it is blank it means the username and info
    //      * does not exist, so we can persist our data in d db.
    //     */
    //     if(account.getUsername() != null && account.getPassword().length()>=4){
    //             if(accounts==null){
    //                 return accountDao.insertAccount(account);  
    //             }      
    //     }
    //     return null;
    // }
        

    public Account addAccount(Account account){
        Account accounts= accountDao.getAccountByUserName(account.getUsername());
        
        String userName = account.getUsername();
        if(userName != "" && account.getPassword().length()>=4 && accounts==null){
            return accountDao.insertAccount(account);
        }
        return null;   
    }

    public Account userLogin(Account account){
        // Account accounts = accountDao.getAccountById(account.getAccount_id());
        Account matchUsername = accountDao.getUserNameAndPassword(account.getUsername(), account.getPassword());
        // Account userName = accountDao.getAccountByUserName(account.getUsername());
        // Account userPassword = accountDao.getAccountByPassword(account.getPassword());
        
        
        if(matchUsername != null){
            return accountDao.getAccountById(account.getAccount_id());
        }
        return null;
    }

   

    // public Account userLogin(Account account){
    //     // Account accounts = accountDao.getAccountById(account.getAccount_id());
    //    // Account matchUsername = accountDao.getUserNameAndPassword(account.getUsername(), account.getPassword());
    //     String userName1 = account.getUsername();
    //     String userPassword1 = account.getPassword();
        

    //     if(userName1 != "" && userPassword1 != ""){
    //         return accountDao.getAccountById(account.getAccount_id());
    //     }
    //     return null;
    // }

}