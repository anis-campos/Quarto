/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Antoine
 */
public class ChessGameMessage {
    private String messageBox="";
    
    public ChessGameMessage(){
    }
    
    public void addMessage(String message){
        this.messageBox += message + "\n";
    }
    
    public String getMesageBox(){
        return this.messageBox;
    }
}
