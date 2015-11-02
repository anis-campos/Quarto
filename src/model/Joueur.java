/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author timotheetroncy
 */
public class Joueur {
    private String nom;
    private Boolean isBot;
    private NumeroJoueur numeroJoueur;

    public Joueur(String nom, Boolean isBot, NumeroJoueur numeroJoueur) {
        this.nom = nom;
        this.isBot = isBot;
        this.numeroJoueur = numeroJoueur;
    }

    public NumeroJoueur getNumeroJoueur() {
        return numeroJoueur;
     }
    
    
}
