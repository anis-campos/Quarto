/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Databse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  Interface DAO
 * @author Anis
 * @param <T> La classe a interfacer
 */
public abstract class DAO<T> {

    public final Connection connect = ConnectionSqlite.getInstance();
    
    public final String nomTable;

  

    public DAO(Class<T> type) {
         this.nomTable = type.getName();
    }

    /**
     * Creation de la table si elle n'existe pas
     */
    public abstract void createTable();

    /**
     * Suppression de la table et des données qu'elle contient
     */
    public void dropTable(){
        try {
            PreparedStatement ps = connect.prepareStatement("drop table if exists ?");
            ps.setString(1, nomTable);
            ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Permet de récupérer un objet via son ID
     *
     * @param id
     * @return
     */
    public abstract T find(Object id);

    /**
     * Permet de créer une entrée dans la base de données par rapport à un objet
     *
     * @param obj
     */
    public abstract T create(T obj);

    /**
     * Permet de mettre à jour les données d'une entrée dans la base
     *
     * @param obj
     */
    public abstract T update(T obj);

    /**
     * Permet la suppression d'une entrée de la base
     *
     * @param obj
     */
    public abstract void delete(T obj);
}
