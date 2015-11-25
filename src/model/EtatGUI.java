/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Anis
 */
public enum EtatGUI {
    EtatNonDefinit,//pas de transition
    J1DoitDonner,
    J2DoitDonner,
    J1DoitChoisir,
    J2DoitChoisir,
    J1DoitPlacer,
    J2DoitPlacer,
   
    J1DernierTour,
    J2DernierTour,
    
    J1AAnnonceQuarto,
    J2AAnnonceQuarto,  
    
    J1ATrouveUnQuarto,
    J2ATrouveUnQuarto,
    
    J2PeutConfirmerMatchNull,
    J1PeutConfirmerMatchNull,


    J1EtJ2OntAnnonceMatchNull,
    
}
