/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.Test;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Anis
 */
public interface Test extends Serializable,Remote{
    
    
    String HelloWorld() throws RemoteException;
    
}
