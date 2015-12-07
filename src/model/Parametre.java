/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author timotheetroncy
 */
public class Parametre implements Serializable{

    private final Boolean hauteur;
    private final Boolean creux;
    private final Boolean couleur;
    private final Boolean forme;
    private final Boolean quartoCarre;
    private final Boolean validationAutomatiqueQuarto;
    private final Boolean joueurRamdom;
    private final Boolean contreBot;
    private final Boolean torus;

    public Parametre(Boolean forme, Boolean hauteur, Boolean couleur, Boolean creux, Boolean quartoCarre, Boolean validationAutomatiqueQuarto, Boolean joueurRamdom, Boolean contreBot, Boolean torus) {
        this.hauteur = hauteur;
        this.creux = creux;
        this.couleur = couleur;
        this.forme = forme;
        this.quartoCarre = quartoCarre;
        this.validationAutomatiqueQuarto = validationAutomatiqueQuarto;
        this.joueurRamdom = joueurRamdom;
        this.contreBot = contreBot;
        this.torus = torus;
    }

    @Override
    public String toString() {
        return String.format(
                "[Hauteur: %s, Surface: %s, Couleur: %s, Forme: %s, QuartoCarré: %s, ValidationAuto: %s, PremierJoueurAléatoire: %s, JeuContreBot: %s]",
                hauteur,
                creux,
                couleur,
                forme,
                quartoCarre,
                validationAutomatiqueQuarto,
                joueurRamdom,
                contreBot);
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

    public Boolean quartoCarreActif() {
        return quartoCarre;
    }

    public Boolean validationAutoActif() {
        return validationAutomatiqueQuarto;
    }

    public Boolean joueurRandom() {
        return joueurRamdom;
    }

    public Boolean contreBot() {
        return contreBot;
    }

    public Boolean torusActif() {
        return torus;
    }

}
