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
public class Parametre {

    private final Boolean hauteur;
    private final Boolean creux;
    private final Boolean couleur;
    private final Boolean forme;
    private final Boolean quartoCarre;
    private final Boolean validationAutomatiqueQuarto;
    private final Boolean JoueurRandom;

    

    public Parametre(Boolean forme, Boolean hauteur, Boolean couleur, Boolean creux, Boolean quartoCarre,Boolean validationAutomatiqueQuarto,Boolean JoueurRandom) {
        this.hauteur = hauteur;
        this.creux = creux;
        this.couleur = couleur;
        this.forme = forme;
        this.quartoCarre = quartoCarre;
        this.validationAutomatiqueQuarto = validationAutomatiqueQuarto;
        this.JoueurRandom = JoueurRandom;
    }

    public Boolean hauteurActif() {
        return hauteur;
    }

    public Boolean creuxActif() {
        return creux;
    }

    public Boolean couleurActif() {
        return couleur;
    }

    public Boolean formeActif() {
        return forme;
    }
    public Boolean quartoCarreActif(){
        return quartoCarre;
    }
    public Boolean validationAutoActif(){
        return validationAutomatiqueQuarto;
    }
    public Boolean joueurRandom() {
        return JoueurRandom;
    }
    

}
