/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.POC;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anis
 */
public class Calculateur extends UnicastRemoteObject implements ISession, Unreferenced {

    Calculateur() throws RemoteException {

    }

    @Override
    public void logout() throws RemoteException {
        ///TODO: Verifier que cette méthode sadapte au quarto.
        unexportObject(this, true); //Suppression de l'objet du serveur
    }

    @Override
    public void unreferenced() {
        try {
            unexportObject(this, true);
        } catch (NoSuchObjectException ex) {
            Logger.getLogger(Calculateur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int sum(int... values) throws RemoteException {
        int rep = 0;
        for (int value : values) {
            rep += value;
        }
        return rep;
    }

    @Override
    public int multiply(int... values) throws RemoteException {
        int rep = 1;
        for (int value : values) {
            rep *= value;
        }
        return rep;
    }

}
