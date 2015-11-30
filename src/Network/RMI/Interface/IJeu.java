/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.Interface;

import controlleur.IControlleur;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Anis
 */
public interface IJeu extends IControlleur,Remote,Serializable{
    
    void registerClientCallback(IClientCallback client)throws RemoteException;
}
