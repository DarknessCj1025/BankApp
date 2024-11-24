/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.User;
import core.models.storage.Storage;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author camil
 */
public class AccountController {
     public static Response createA(String userId, String initialBalance){
        try{
            int userId1;
            double initialBalance1;
            
            if(userId.equals("")){
                return new Response("Id has to be empty", Status.BAD_REQUEST);
            }
            
            try{
                userId1 = Integer.parseInt(userId);
                if(userId1 < 0){
                    return new Response("Id has to be positive", Status.BAD_REQUEST);
                }
                if(999999999< userId1){
                    return new Response("Id must have 9 numbers", Status.BAD_REQUEST);
                }      
                
            }catch(NumberFormatException ex){
                return new Response("Id has to be numeric", Status.BAD_REQUEST);
            }
            
            
            if(initialBalance.equals("")){
                return new Response("Initial balance can not be empty", Status.BAD_REQUEST);
            }
            
            try{
                initialBalance1 = Double.parseDouble(initialBalance);
                if(initialBalance1 < 0){
                    return new Response("Initial balance has to be positive", Status.BAD_REQUEST);
                }
                if (initialBalance1 == 0) {
                    return new Response("Initial balance has to be more than 0", Status.BAD_REQUEST);
                }
            }catch(NumberFormatException ex){
                return new Response("Initial balance must be numeric", Status.BAD_REQUEST);
            }
            
            Storage storage = Storage.getInstance();
            
            Random random = new Random();
            int first = random.nextInt(1000);
            int second = random.nextInt(1000000);
            int third = random.nextInt(100);
            String accountId = String.format("%03d", first) + "-" + String.format("%06d", second) + "-" + String.format("%02d", third);
            
            User accountUser = null;
            System.out.println(userId1);
            for(User user : storage.getUsers()){
                if(user.getId()==userId1){
                    accountUser = user;
                }
            }
            
            if(accountUser==null){
                return new Response("The user does not exist", Status.BAD_REQUEST);
            }
            Account account = new Account(accountId, accountUser,initialBalance1);
            storage.addAccount(account);
            return new Response("Account created succesfully", Status.CREATED);
            
        }catch(Exception ex){
            return new Response("", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static ArrayList<Account> refreshA(){
        Storage storage = Storage.getInstance();
        ArrayList<Account> accounts = storage.getAccounts();
        return accounts;
    }
}
