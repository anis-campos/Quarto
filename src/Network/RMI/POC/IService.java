/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.POC;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Anis
 */
public interface IService extends Remote {
    
    boolean connexion(String login, String password) throws RemoteException;
    
    
    
}
