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
    private Boolean hauteur;
    private Boolean creux;
    private Boolean couleur;
    private Boolean forme;

    public Parametre(Boolean hauteur,  Boolean creux,  Boolean couleur,  Boolean forme) {
        this.hauteur = true;
        this.creux = true;
        this.couleur = true;
        this.forme = true;
    }

    public Boolean getHauteur() {
        return hauteur;
    }

    public Boolean getCreux() {
        return creux;
    }

    public Boolean getCouleur() {
        return couleur;
    }

    public Boolean getForme() {
        return forme;
    }
    
    
}
