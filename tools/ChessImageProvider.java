package tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import model.Coord;
import model.Couleur;

/**
 * @author francoise.perrin Inspiration Jacques SARAYDARYAN, Adrien GUENARD
 *
 * Cette classe s'appuie sur ChessPieceImage pour fournir les noms des images
 * des pi�ces qui sont utilis�es dans l'IHM par rapport � des indices exprim�s
 * par rapport aux lignes (i), colonnes (j) du damier de l'IHM graphique *
 */
public class ChessImageProvider {

    private static final Map<String, String> mapImage;
    private static final Map<Coord, NomCouleurPiece> mapCoordPiece;

    static {

        mapImage = new HashMap<>();
        for (ChessPieceImage value : ChessPieceImage.values()) {
            mapImage.put(value.nom, value.imageFile);
        }

        mapCoordPiece = new HashMap<>();
        for (ChessPiecePos value : ChessPiecePos.values()) {
            for (int j = 0; j < (value.coords).length; j++) {
                String nomPiece = value.nom;
                Couleur couleurPiece = value.couleur;
                Coord pieceCoord = value.coords[j];
                mapCoordPiece.put(pieceCoord, new NomCouleurPiece(nomPiece, couleurPiece));
            }
        }
    }

    /**
     * private pour ne pas instancier d'objets
     */
    private ChessImageProvider() {

    }

    /**
     * @param i
     * @param j
     * @return le type de la pi�ce aux coordonn�es i, j
     */
    public static String getType(int i, int j) {
        String type = null;
        Coord coord;
        NomCouleurPiece nomCouleurPiece;

        coord = findMapCoordPiece(i, j);
        if (coord != null) {
            nomCouleurPiece = mapCoordPiece.get(coord);
            type = nomCouleurPiece.nom;
        }
        return type;
    }

    /**
     * @param i
     * @param j
     * @return couleur de la pi�ce aux coordonn�es i, j
     */
    public static Couleur getCouleur(int i, int j) {
        Couleur couleur = null;
        Coord coord;
        NomCouleurPiece nomCouleurPiece;

        coord = findMapCoordPiece(i, j);
        if (coord != null) {
            nomCouleurPiece = mapCoordPiece.get(coord);
            couleur = nomCouleurPiece.couleur;
        }
        return couleur;
    }

    /**
     * @param pieceType
     * @param pieceCouleur
     * @return nom fichier contenant image de la pi�ce
     */
    public static BufferedImage getImageFile(String pieceType, Couleur pieceCouleur) {
        

        try {
            String FileName = mapImage.get( pieceType + pieceCouleur.name());
            return ImageIO.read(ChessImageProvider.class.getResourceAsStream("/images/"+FileName));
        } catch (IOException ex) {
            Logger.getLogger(ChessImageProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * @param i
     * @param j
     * @return true s'il existe une pi�ce sur cette ligne et cette colonne
     */
    public static boolean isCoordOK(int i, int j) {
        return findMapCoordPiece(i, j) != null;
    }

    /**
     * @param i
     * @param j
     * @return un objet de type Coord connaissant i et j
     */
    private static Coord findMapCoordPiece(int i, int j) {
        Coord ret = null;
        Set<Coord> keys = mapCoordPiece.keySet();
        for (Coord coord : keys) {
            if (j == coord.x && i == coord.y) {
                ret = coord;
            }
        }
        return ret;
    }

    /**
     * Test unitaires
     *
     * @param args
     */
    public static void main(String[] args) {

        System.out.println(ChessImageProvider.getImageFile("Cavalier", Couleur.BLANC));
    }

}
