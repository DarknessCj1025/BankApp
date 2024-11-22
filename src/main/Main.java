/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import core.views.BankFrame;

/**
 *
 * @author Valentina Melo
 */
public class Main {
      public static void main (String arge []){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BankFrame().setVisible(true);
            }
        });
    }
}
