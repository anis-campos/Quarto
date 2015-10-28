/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelquarto;

/**
 *
 * @author timotheetroncy
 */
class Parametre {

    private final Boolean hauteur;
    private final Boolean creux;
    private final Boolean couleur;
    private final Boolean forme;
    private final Boolean quartoCarre;

    public Parametre(Boolean forme, Boolean hauteur, Boolean couleur, Boolean creux, Boolean quartoCarre) {
        this.hauteur = hauteur;
        this.creux = creux;
        this.couleur = couleur;
        this.forme = forme;
        this.quartoCarre = quartoCarre;
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

}
