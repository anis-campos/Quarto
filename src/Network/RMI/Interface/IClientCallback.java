/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.Interface;

import controlleur.observables.Notification;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Anis
 */
public interface IClientCallback extends Remote,Serializable {
 
    public void notifyMe( Notification notif) throws RemoteException;
    
}
