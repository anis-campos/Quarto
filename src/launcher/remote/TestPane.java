/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.remote;

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
public class TestPane extends JPanel {

        private JPanel mainPane;
        private JPanel navPane;
        private List<String> pages;
        private int currentPage;

        public TestPane() {
            pages = new ArrayList<>(25);
            
            setLayout(new BorderLayout());

            mainPane = new JPanel(new CardLayout());
            navPane = new JPanel();

            addPageTo("Page" + 0, new Connexion());
            for (int index = 1; index < 10; index++) {
                addPageTo( "Page" + index, new JLabel("Page " + index, JLabel.CENTER));
            }

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

        protected void setCurrentPage(int page) {
            if (pages.size() > 0) {
                if (page < 0) {
                    page = pages.size() - 1;
                } else if (page >= pages.size()) {
                    page = 0;
                }

                currentPage = page;
                CardLayout layout = (CardLayout)mainPane.getLayout();
                layout.show(mainPane, pages.get(currentPage));
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }

        protected void addPageTo( String name, Component comp) {
            pages.add(name);
            mainPane.add(comp, name);
        }
    }
