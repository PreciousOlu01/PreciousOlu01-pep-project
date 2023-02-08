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

        //register user
    public Account addAccount(Account account){
        Account accounts= accountDao.getAccountByUserName(account.getUsername());
        
        String userName = account.getUsername();
        if(userName != "" && account.getPassword().length()>=4 && accounts==null){
            return accountDao.insertAccount(account);
        }
        return null;   
    }

    public Account userLogin(Account account){
        return accountDao.getUserNameAndPassword(account);
    }

}