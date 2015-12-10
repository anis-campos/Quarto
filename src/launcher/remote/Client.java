/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.remote;

import static Network.RMI.Constantes.URL_CONNEXION;
import Network.RMI.Interface.ILogin;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JFrame;
import launcher.remote.view.ModeReseau2;

/**
 *
 * @author Anis
 */
public class Client {

    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {

        ILogin service;
        
        
        service = (ILogin) Naming.lookup(URL_CONNEXION);

        JFrame frame = new JFrame();
       

        frame.setContentPane(ModeReseau2.getInstance(service));
        
        frame.setSize(new Dimension(600, 600));

        //frame.pack();
        frame.setVisible(true);

    }

}
