/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI_POC;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Anis
 */
public interface ISession extends Remote {

    void logout() throws RemoteException;

    int sum(int... values) throws RemoteException;

    int multiply(int... values) throws RemoteException;

}
