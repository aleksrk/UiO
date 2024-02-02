import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class GUI {

    private Kontroll kontroll;
    private JFrame vindu;
    private JPanel panel, konsoll, rutenett;
    private JButton v, h, o, n;
    private JLabel antall;
    private JButton stoppknapp;
    JLabel[][] brettet = new JLabel[12][12];

    GUI (Kontroll k) {
        this.kontroll = k;
    }

    void byggGUI() {
        try {
            UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName()
            );
        } catch (Exception e) {System.exit(1);}

        vindu = new JFrame("Snake");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        vindu.add(panel);

        konsoll = new JPanel();
        konsoll.setLayout(new BorderLayout());
        panel.add(konsoll, BorderLayout.NORTH);

        stoppknapp = new JButton("Exit");
	       class Stoppbehandler implements ActionListener {
	            @Override
	            public void actionPerformed (ActionEvent e) {
	                kontroll.avsluttSpillet();
	            }
    	    }
	    stoppknapp.addActionListener(new Stoppbehandler());
	    panel.add(stoppknapp, BorderLayout.SOUTH);

        rutenett = new JPanel();
        rutenett.setLayout(new GridLayout(12,12));
        panel.add(rutenett, BorderLayout.CENTER);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                String hent = kontroll.hentVerdi(i, j);
                brettet[i][j] = new JLabel(hent);
                brettet[i][j].setBorder(border);
                rutenett.add(brettet[i][j]);
            }
        }

        v = new JButton("Venstre");
        class gaaVenstre implements ActionListener {
            @Override
            public void actionPerformed (ActionEvent e) {
                kontroll.settRetning(Retning.VEST);
            }
        }
        v.addActionListener(new gaaVenstre());
        h = new JButton("Høyre");
        class gaaHoyre implements ActionListener {
            @Override
            public void actionPerformed (ActionEvent e) {
                kontroll.settRetning(Retning.OST);
            }
        }
        h.addActionListener(new gaaHoyre());
        o = new JButton("Opp");
        class gaaOpp implements ActionListener {
            @Override
            public void actionPerformed (ActionEvent e) {
                kontroll.settRetning(Retning.NORD);
            }
        }
        o.addActionListener(new gaaOpp());
        n = new JButton("Ned");
        class gaaNed implements ActionListener {
            @Override
            public void actionPerformed (ActionEvent e) {
                kontroll.settRetning(Retning.SOR);
            }
        }
        n.addActionListener(new gaaNed());

        konsoll.add(v, BorderLayout.WEST);
        konsoll.add(h, BorderLayout.EAST);
        konsoll.add(o, BorderLayout.NORTH);
        konsoll.add(n, BorderLayout.SOUTH);

        vindu.pack();   vindu.setVisible(true);
    }

    void markerRute(int a, int b, String s) {
        brettet[a][b].setText(s);
    }

}
