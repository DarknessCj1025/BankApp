/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.Transaction;
import core.models.TransactionType;
import core.models.storage.Storage;
import java.util.ArrayList;

/**
 *
 * @author camil
 */
public class Transactioncontroller {
    public static Response Deposit(TransactionType type, String sourceid, String destinationid, String amount){
        try{
            double amount1;
            
            try{
                amount1=Double.parseDouble(amount);
                if(amount1<0){
                    return new Response("Amount has to be positive", Status.BAD_REQUEST);
                }
            }catch(NumberFormatException ex){
                return new Response("Amount has to be numeric", Status .BAD_REQUEST);
            }
            
            Storage storage = Storage.getInstance();
            
            Account destino = null;

            for (Account account : storage.getAccounts()) {
                if (account.getId().equals(destinationid)) {
                    destino = account;
                }
            }
            if(!sourceid.equals("")){
                return new Response("Source account has to be empty",Status.BAD_REQUEST);
            }
            if(destinationid==null){
                return new Response("Destination account doesn't exists",Status.BAD_REQUEST);
            }
            
            destino.setBalance(destino.getBalance()+amount1);
            Transaction transaction = new Transaction(type,null,destino,amount1);
            storage.addTransaction(transaction);
            return new Response ("Deposit Done",Status.OK);
            
        }catch (Exception ex){
            return new Response("",Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response Withdraw(TransactionType type, String sourceid, String destinationid, String amount){
        try{
            double amount1;
            
            if(amount.equals("")){
                return new Response("Amount can not be empty",Status.BAD_REQUEST);
            }
            
            try{
                amount1=Double.parseDouble(amount);
                if(amount1<0){
                    return new Response("Amount has to be positive", Status.BAD_REQUEST);
                }
            }catch(NumberFormatException ex){
                return new Response("Amount has to be numeric", Status .BAD_REQUEST);
            }
            
            if(sourceid.equals("")){
                return new Response("Source account can not be empty",Status.BAD_REQUEST);
            }
            
            
            Storage storage = Storage.getInstance();
            
            Account source = null;

            for (Account account : storage.getAccounts()) {
                if (account.getId().equals(sourceid)) {
                    source = account;
                }
            }
            
            if(!destinationid.equals("")){
                return new Response("Destination account has to be empty",Status.BAD_REQUEST);
            }
            if(source==null){
                return new Response("Source account doesn't exists",Status.BAD_REQUEST);
            }
            
            if(source.getBalance()-amount1<0){
                return new Response("Money in source account isn't enough",Status.BAD_REQUEST);
            }
            
            source.setBalance(source.getBalance()-amount1);
            
            
            Transaction transaction = new Transaction(type,null,source,amount1);
            storage.addTransaction(transaction);
            return new Response ("Withdraw Done",Status.OK);
            
            
            
        }catch (Exception ex){
            return new Response("",Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response Transfer(TransactionType type, String sourceid, String destinationid, String amount){
        try{
            double amount1;
            
            if(amount.equals("")){
                return new Response("Amount can not be empty",Status.BAD_REQUEST);
            }
            
            try{
                amount1=Double.parseDouble(amount);
                if(amount1<0){
                    return new Response("Amount has to be positive", Status.BAD_REQUEST);
                }
            }catch(NumberFormatException ex){
                return new Response("Amount has to be numeric", Status .BAD_REQUEST);
            }
            
            if(sourceid.equals("")){
                return new Response("Source account can not be empty",Status.BAD_REQUEST);
            }
            if(destinationid.equals("")){
                return new Response("Destination account can not be empty",Status.BAD_REQUEST);
            }
            
            
            Storage storage = Storage.getInstance();
            
            Account source = null;
            Account destino = null;

            for (Account account : storage.getAccounts()) {
                if (account.getId().equals(sourceid)) {
                    source = account;
                }else if (account.getId().equals(destinationid)) {
                    destino = account;
                }
            }
            
            
            if(source==null){
                return new Response("Source account doesn't exists",Status.BAD_REQUEST);
            }
            if(destino==null){
                return new Response("Destination account doesn't exists",Status.BAD_REQUEST);
            }
            if(source.getBalance()-amount1<0){
                return new Response("Money in source account isn't enough",Status.BAD_REQUEST);
            }
            
            source.setBalance(source.getBalance()-amount1);
            destino.setBalance(destino.getBalance()+amount1);
            
            Transaction transaction = new Transaction(type,source,destino,amount1);
            storage.addTransaction(transaction);
            
            return new Response ("Transfer Done",Status.OK);
            
        }catch (Exception ex){
            return new Response("",Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static ArrayList<Transaction> refreshT(){
        Storage storage = Storage.getInstance();
        ArrayList<Transaction> transaction = storage.getTransactions();
        return transaction;
    }
}
