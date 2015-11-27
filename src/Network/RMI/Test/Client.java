/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.Test;

import Databse.Compte;
import Network.RMI.Interface.*;
import Network.RMI.PartieItem;
import static RMI_POC.Constantes.CONNEXION;

import java.rmi.Naming;
import java.util.List;
import java.util.Observable;
import model.*;

/**
 *
 * @author Anis
 */
public class Client {

    public static void main(String[] args) throws Exception {

        ILogin service = (ILogin) Naming.lookup("rmi:" + CONNEXION);

        
        
        ISession session = service.connexion("negga", "ytreza", new IClientCallback() {

            @Override
            public void update(Observable o, Object arg) {
                System.out.println(arg);
            }
        });
        
        System.out.println("Client Connecté !!!");

        List<Compte> comptes = session.listeComptes();
        
        
        
        for (Compte compte : comptes) {
            
            System.out.println(compte);
        }
        
        //List<PartieItem> listePartie = session.listePartie();
        IJeu PartiJeu = session.creerPartieAvecAdversaire(
                new Parametre(true, true, true, true, true, true, false, false), 
                comptes.get(0));

        //PartiJeu.getJoueurCourant();

        while (true) {
            Thread.sleep(1000);
        }

    }
    
    class clientImp implements IClientCallback{

        @Override
        public void update(Observable o, Object arg) {
            
        }
        
    }

}
