package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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

    private final int id;

    public String getNomPiece() {
        return nomPiece;
    }
    private final ImageIcon imagePiece;
    private final double pourcentagePiece = 0.96;
    private final Dimension tailleCase;
    private final Runnable ActionOnClick;

    /**
     * Constructeur de la pièce
     *
     * @param id l'Id de la pièce (unique)
     * @param nomPiece Le nom de la pièce ( correspond au modèle )
     * @param tailleCase Taille d'une case du plateau
     * @param ActionOnClick L'action a executer lors du click
     */
    public JLabelPiece(int id, String nomPiece, Dimension tailleCase, Runnable ActionOnClick) {
        super();

        this.id = id;

        this.nomPiece = nomPiece;

        this.tailleCase = tailleCase;

        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setPreferredSize(tailleCase);
        this.setMaximumSize(tailleCase);

        this.imagePiece = getScaledImage("/images/" + nomPiece + ".png");
        this.setIcon(imagePiece);

        this.setHorizontalAlignment(CENTER);

        this.addPropertyChangeListener(new ImagePropertyChangeListener());
        this.addMouseListener(new PieceMouseAdapter());

        this.ActionOnClick = ActionOnClick;
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
        JLabelPiece jLabelPiece = new JLabelPiece(id, nomPiece, tailleCase, null);
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
                source.setDisabledIcon(imagePiece);
                if ((boolean) evt.getNewValue()) {
                    source.setBorder(BorderFactory.createLineBorder(Color.black));
                } else {
                    source.setBorder(null);
                }
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

    public int getId() {
        return id;
    }

}
