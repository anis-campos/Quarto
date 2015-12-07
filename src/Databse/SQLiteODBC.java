/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Databse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import model.Parametre;

/**
 *
 * @author Joa
 */
public class SQLiteODBC {

    public static void connexion() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:PARAMETRE.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public static void createTable() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE PARAMETRE "
                    + "("
                    + "ID INT PRIMARY KEY NOT NULL,"
                    + "name TEXT NOT NULL,"
                    + "hauteur BOOLEAN NOT NULL CHECK (mycolumn IN (0,1)),"
                    + "creux BOOLEAN NOT NULL CHECK (mycolumn IN (0,1)),"
                    + "forme BOOLEAN NOT NULL CHECK (mycolumn IN (0,1)),"
                    + "quartoCarre BOOLEAN NOT NULL CHECK (mycolumn IN (0,1)),"
                    + "validationAuto BOOLEAN NOT NULL CHECK (mycolumn IN (0,1))"
                    + ") ";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println(" database create successfully");
    }

    public static void initTable() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            String sql = "INSERT INTO PARAMETRE (name,hauteur,creux,forme,quartoCarre,validationAuto) "
                    + "VALUES (?,?,?,?,?,?);";

            PreparedStatement stmt = c.prepareStatement(sql);

            stmt.setString(1, "Default");

            for (int i = 1; i < 5; i++) {
                stmt.setBoolean(i, true);
            }

            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static void insert(String name, Parametre p) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            //String sql = "INSERT INTO PARAMETRE (ID,NAME) "
            //        + "VALUES (1, 'CARRE' );";
            //stmt.executeUpdate(sql);

            String sql = "INSERT INTO PARAMETRE (NAME) "
                    + "VALUES (2, 'FONCE');";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    public static void select() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PARAMETRE;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("ID = " + id);
                System.out.println("NAME = " + name);
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation select successfully");
    }

    public static void update() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "UPDATE PARAMETRE set NAME = 'ROND' where ID=1;";
            stmt.executeUpdate(sql);
            c.commit();

            ResultSet rs = stmt.executeQuery("SELECT * FROM PARAMETRE;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("ID = " + id);
                System.out.println("NAME = " + name);
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation update successfully");
    }

    public static void delete() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "DELETE from PARAMETRE where ID=2;";
            stmt.executeUpdate(sql);
            c.commit();

            ResultSet rs = stmt.executeQuery("SELECT * FROM PARAMETRE;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                System.out.println("ID = " + id);
                System.out.println("NAME = " + name);

                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation delete successfully");
    }

    public static void main(String args[]) {
        //connexion();
        //createTable();
        //insert();
        select();
        //update();
        delete();
    }
}
