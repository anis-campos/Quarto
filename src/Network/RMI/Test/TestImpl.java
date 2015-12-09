/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.Test;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TestImpl extends UnicastRemoteObject implements Test {

    public TestImpl() throws RemoteException {

    }

    @Override
    public String HelloWorld() throws RemoteException {
        return "HELLO WORLD !";
    }

}
