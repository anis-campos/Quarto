/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author Anis
 */
public class JPanelCase extends JPanel {

    private JLabelPiece piece;
    private final Runnable CaseClickAction;

    public JLabelPiece getPiece() {
        return piece;
    }

    public void setPiece(JLabelPiece piece) {
        this.piece = piece;
        this.add(piece);
    }
    
    public void removePiece(){
        if (!isEmpty()){
            this.piece=null;
            this.removeAll();
        }
    }

    public JPanelCase(Dimension tailleCase, Runnable CaseClickAction) {
        super();

        this.setPreferredSize(tailleCase);
        this.setMaximumSize(tailleCase);
        this.setMinimumSize(tailleCase);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridBagLayout());
        this.piece = null;
        this.addMouseListener(new CaseMouseAdapter());
        this.CaseClickAction = CaseClickAction;
    }
    
    public boolean isEmpty(){
        return this.piece == null;
    }

    private class CaseMouseAdapter extends MouseAdapter {

        public CaseMouseAdapter() {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
           if(isEnabled()&& CaseClickAction!=null)
               CaseClickAction.run();
        }
    }

}
