/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.User;
import core.models.storage.Storage;
import java.util.ArrayList;

/**
 *
 * @author camil
 */
public class Usercontroller {
    public static Response registerU(String id, String firstName, String lastName, String age){
        int id1, age1;
        
        try{
            try{
                id1=Integer.parseInt(id);
                if(id1<0 && id1>999999999){
                    return new Response("Id has to be positive and no longer than 9 numbers", Status.BAD_REQUEST);
                }
            }catch(NumberFormatException ex){
                return new Response("Id has to be numeric", Status .BAD_REQUEST);
            }
            
            if(firstName.equals("")){
                return new Response("Firstname can not be empty",Status.BAD_REQUEST);
            }else if(lastName.equals("")){
                return new Response("Lastname can not be empty",Status.BAD_REQUEST);
            }
            
            try{
                age1=Integer.parseInt(age);
                if(age1<18){
                    return new Response("User hast to be older than 18 years old", Status.BAD_REQUEST);
                }
            }catch(NumberFormatException ex){
                return new Response("Age hast to be be numeric", Status .BAD_REQUEST);
            }
            
            Storage storage = Storage.getInstance();
            if(!storage.addUser(new User(id1,firstName,lastName,age1))){
                return new Response("A user with that id already exists",Status.BAD_REQUEST);
                
            }
            return new Response ("A user has been registered",Status.OK);
        }catch (Exception ex){
            return new Response("",Status.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    public static ArrayList<User> refreshU(){
        Storage storage = Storage.getInstance();
        ArrayList<User> users = storage.getUsers();
        return users;
    }
}
