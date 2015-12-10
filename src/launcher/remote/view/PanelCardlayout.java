/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.remote.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Anis
 */
public final class PanelCardlayout extends JPanel {

    private final JPanel mainPane;
    private final JPanel navPane;
    private final List<String> pages;
    private int currentPage;

    public PanelCardlayout() {
        pages = new ArrayList<>(25);

        setLayout(new BorderLayout());

        mainPane = new JPanel(new CardLayout());
        navPane = new JPanel();

       /* JButton btnPrev = new JButton("<<");
        JButton btnNext = new JButton(">>");

        navPane.add(btnPrev);
        navPane.add(btnNext);

        btnPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentPage(getCurrentPage() - 1);
            }
        });
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentPage(getCurrentPage() + 1);
            }
        });*/
        
        JButton buttonAfficherMenu = new JButton("Retour au Menu");
        
        buttonAfficherMenu.addActionListener(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentPage("menu");
            }
        });
        
        navPane.add(buttonAfficherMenu);
navPane.setOpaque(false);
        
        add(mainPane);
        add(navPane, BorderLayout.SOUTH);

        setCurrentPage(0);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    private void setCurrentPage(int page) {
        if (pages.size() > 0  && page>-1 && page<pages.size()) {
            currentPage = page;
            CardLayout layout = (CardLayout) mainPane.getLayout();
            layout.show(mainPane, pages.get(currentPage));
        }
    }

    public void setCurrentPage(String pageName) {

        CardLayout layout = (CardLayout) mainPane.getLayout();
        layout.show(mainPane, pageName);

    }

    public void toggleNavBar() {
        navPane.setVisible(!navPane.isVisible());
    }

    public void addPage(String name, Component comp) {
        if(pages.contains(name)) {
          
            for (Component component : mainPane.getComponents()) {
                if(component.getName().equals(name)){
                    mainPane.remove(comp);
                    break;
                }
            }
        }
        else
            pages.add(name);
        
        comp.setName(name);
        mainPane.add(comp, name);
    }

}
