/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Account;
import core.models.Transaction;
import core.models.User;
import java.util.ArrayList;

/**
 *
 * @author camil
 */
public class Storage {
    private static Storage instance;
    
    private ArrayList<User> users;
    private ArrayList<Account> accounts;
    private ArrayList<Transaction> transactions;
    
    private Storage() {
        this.users = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }
    
    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }
    
    public boolean addUser(User user){
        for (User user1 : this.users) {
            if (user.getId() == user1.getId()) {
                return false;
            }
        }
        this.users.add(user); 
        return true;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
    
    public boolean addAccount(Account account ){      
        this.accounts.add(account); 
        return true;
    }
    
    public ArrayList<Account> getAccounts(){
        return accounts;
    }

    
    public boolean addTransaction(Transaction transaction){
        this.transactions.add(transaction);
        return true;
    }
    
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
