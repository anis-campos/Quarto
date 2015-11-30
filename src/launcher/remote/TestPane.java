/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.remote;

import Network.RMI.Interface.ILogin;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Anis
 */
public final class TestPane extends JPanel {

    private final JPanel mainPane;
    private final JPanel navPane;
    private final List<String> pages;
    private int currentPage;

    public TestPane() {
        pages = new ArrayList<>(25);

        setLayout(new BorderLayout());

        mainPane = new JPanel(new CardLayout());
        navPane = new JPanel();

        JButton btnPrev = new JButton("<<");
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
        });

        add(mainPane);
        add(navPane, BorderLayout.SOUTH);

        setCurrentPage(0);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int page) {
        if (pages.size() > 0) {
            currentPage = page;
            CardLayout layout = (CardLayout) mainPane.getLayout();
            layout.show(mainPane, pages.get(currentPage));
        }
    }
    
    public void toggleNavBar(){
        navPane.setVisible(!navPane.isVisible());
    }

 
    public void addPage(String name, Component comp) {
        pages.add(name);
        mainPane.add(comp, name);
    }

}
