/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Parametre;

/**
 *
 * @author Anis
 */
public class ParametreDAO extends DAO<Parametre> {

    public ParametreDAO() {
        super(Parametre.class);
    }

    @Override
    public void createTable() {
        try {
            Statement stmt = connect.createStatement();
            String sql = "CREATE TABLE if not exists " + this.nomTable
                    + "("
                    + "name TEXT RIMARY KEY NOT NULL,"
                    + "forme BOOLEAN NOT NULL CHECK (forme IN (0,1)),"
                    + "hauteur BOOLEAN NOT NULL CHECK (hauteur IN (0,1)),"
                    + "couleur BOOLEAN NOT NULL CHECK (couleur IN (0,1)),"
                    + "creux BOOLEAN NOT NULL CHECK (creux IN (0,1)),"
                    + "quartoCarre BOOLEAN NOT NULL CHECK (quartoCarre IN (0,1)),"
                    + "validationAuto BOOLEAN NOT NULL CHECK (validationAuto IN (0,1)),"
                    + "joueurRandom BOOLEAN NOT NULL CHECK (joueurRandom IN (0,1)),"
                    + "bot BOOLEAN NOT NULL CHECK (bot IN (0,1))"
                    + ") ";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ex.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Parametre find(Object name) {
         try {
            Statement stmt = connect.createStatement();
            String sql = "SELECT * FROM " + this.nomTable
                    + "WHERE name = "+name;
             ResultSet executeQuery = stmt.executeQuery(sql);
             Parametre p = new Parametre(
                     executeQuery.getBoolean("forme"), 
                     executeQuery.getBoolean("hauteur"),
                     executeQuery.getBoolean("couleur"),
                     executeQuery.getBoolean("creux"),
                     executeQuery.getBoolean("quartoCarre"),
                     executeQuery.getBoolean("validationAuto"),
                     executeQuery.getBoolean("joueurRandom"),
                     executeQuery.getBoolean("bot"),
                     executeQuery.getBoolean("torus"),
                     executeQuery.getInt("botLevel")
                    );
             return p;
        } catch (SQLException ex) {
            Logger.getLogger(ex.getClass().getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }

    @Override
    public Parametre create(Parametre obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Parametre update(Parametre obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Parametre obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
