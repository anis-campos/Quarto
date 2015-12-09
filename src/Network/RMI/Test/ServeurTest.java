/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.Test;

import static Network.RMI.Constantes.PORT_RMI;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author Anis
 */
public class ServeurTest {

    public static void main(String[] args) throws Exception {

        try {
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            Registry registre = LocateRegistry.createRegistry(PORT_RMI);

        } catch (RemoteException ex) {
            System.out.println("Une instance de serveur est déja en cours ... ");
            System.exit(-1);
        }

        try {

            //RMISocketFactory.setSocketFactory(new MyRMISocketFactory());
            Test test = new TestImpl();

            String url = "rmi://127.0.0.1/test";
            System.out.println("Enregistrement de l'objet avec l'url : " + url);
            Naming.rebind(url, test);
            Scanner sc = new Scanner(System.in);
            System.out.println("Serveur lancé");

            System.out.println("Pour arreter le serveur appuyer sur Entrée ");
            sc.nextLine();
        } catch (RemoteException | MalformedURLException ex) {
            System.out.println("Impossible de lancer le serveur ... ");
            System.exit(-2);
        }

    }
}
