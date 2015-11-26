/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.POC;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Anis
 */
public class ServiceImp extends UnicastRemoteObject implements IService {

    protected ServiceImp() throws RemoteException {
        super();
    }

    @Override
    public boolean connexion(String login, String password) throws RemoteException {
        return login.equals("scott") && password.length() == 6;
    }

}
