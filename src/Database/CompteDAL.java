/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anis
 */
public class CompteDAL {

    private static CompteDAL instance;

    private CompteDAL() {
    }

    public static CompteDAL getDAL() {
        if (instance == null) {
            instance = new CompteDAL();
        }
        return instance;
    }

    public List<Compte> getComptes() {
        try {
            List<Compte> aRemplacerParDAO = new ArrayList<>();

            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

            aRemplacerParDAO.add(new Compte("Anis", "adsc", "DA SILVA CAMPOS", "Anis", format.parse("19940627")));
            aRemplacerParDAO.add(new Compte("Timothée", "tt", "TRONCY", "Timothée", format.parse("19940627")));
            aRemplacerParDAO.add(new Compte("Joachim", "jv", "VEROT", "Joachim", format.parse("19940627")));
            aRemplacerParDAO.add(new Compte("Florent", "fd", "DECOSTER", "Florent", format.parse("19940627")));
            aRemplacerParDAO.add(new Compte("Nicolas", "nb", "BRAT", "Nicolas", format.parse("19940627")));

            return aRemplacerParDAO;
        } catch (ParseException ex) {
            Logger.getLogger(Compte.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public boolean checkLogin(String login, String pass) {
        Compte compte = findCompte(login);
        return compte != null && compte.password.equals(pass);
    }

    public Compte findCompte(String pseudo) {
        for (Compte compte : getComptes()) {
            if (compte.pseudo.equals(pseudo)) {
                return compte;
            }
        }
        return null;
    }
}
