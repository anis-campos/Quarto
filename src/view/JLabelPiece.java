package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Cette classe represente une pièce dans l'IHM
 *
 * @author Anis
 */
public class JLabelPiece extends JLabel implements Cloneable {

    private final String nomPiece;

    public String getNomPiece() {
        return nomPiece;
    }
    private final HashMap<Boolean, ImageIcon> imagePieces;
    private final double pourcentagePiece = 0.96;
    private final Dimension tailleCase;
    private final Runnable ActionOnClick;

    /**
     * Constructeur de la pièce
     *
     * @param nomPiece Le nom de la pièce ( correspond au modèle )
     * @param tailleCase Taille d'une case du plateau
     * @param ActionOnClick L'action a executer lors du click
     */
    public JLabelPiece(String nomPiece, Dimension tailleCase, Runnable ActionOnClick) {
        super();
        this.nomPiece = nomPiece;
        this.imagePieces = new HashMap<>();
        this.tailleCase = tailleCase;

        this.initMap();

        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setPreferredSize(tailleCase);
        this.setMaximumSize(tailleCase);

        this.setIcon(imagePieces.get(true));

        this.setHorizontalAlignment(CENTER);

        this.addPropertyChangeListener(new ImagePropertyChangeListener());
        this.addMouseListener(new PieceMouseAdapter());

        this.ActionOnClick = ActionOnClick;
    }

    /**
     * Initialise la map des images de cette pièce
     */
    private void initMap() {

        imagePieces.put(Boolean.TRUE, getScaledImage("/images/" + nomPiece + ".png"));

        imagePieces.put(Boolean.FALSE, getScaledImage("/images/desactivees/" + nomPiece + ".png"));

    }

    /**
     * Recupère l'image de la pièce et la redimensionne a la bonne taille
     *
     * @param ImageFilePath le chemin relatif vers la pièce
     * @return Un ImageIcon de la pièce
     */
    private ImageIcon getScaledImage(String ImageFilePath) {
        Image read = GUIImageTool.getImage(ImageFilePath);
        Image scaledInstance = read.getScaledInstance((int) (tailleCase.height * pourcentagePiece), (int) (tailleCase.width * pourcentagePiece), java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledInstance);
    }

    public JLabelPiece getClone() {
        JLabelPiece jLabelPiece = new JLabelPiece(nomPiece, tailleCase, null);
        jLabelPiece.setBorder(null);
        return jLabelPiece;
    }

    /**
     * Ce Listener permet d'afficher la bonne image de la piece selon sont etat
     */
    private class ImagePropertyChangeListener implements PropertyChangeListener {

        public ImagePropertyChangeListener() {
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("enabled")) {
                JLabelPiece source = (JLabelPiece) evt.getSource();
                source.setIcon(imagePieces.get((Boolean) evt.getNewValue()));
            }
        }
    }

    private class PieceMouseAdapter extends MouseAdapter {

        /**
         * execute l'action si la pièce est active
         *
         * @param evt
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabelPiece Piece = (JLabelPiece) e.getSource();
            if (Piece.isEnabled() && ActionOnClick != null) {
                ActionOnClick.run();
            }
        }

    }

}
