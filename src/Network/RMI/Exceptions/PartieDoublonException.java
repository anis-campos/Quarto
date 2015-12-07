/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.Exceptions;

import Databse.Compte;

/**
 *
 * @author Anis.DASILVACAMPOS
 */
public class PartieDoublonException extends Exception{

    public PartieDoublonException(Compte adversaire){
        super("Vous avez déjà créé une partie avec cette adversaire !\n"
                + "\tPseudo : "+adversaire.pseudo+"\n"
                + "\tNom    : "+adversaire.Nom+"\n"
                + "\tPrénom : "+adversaire.Prenom);
    }
    
}
