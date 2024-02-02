class Modell {
    private GUI gui;
    private int tellerverdi = 0;

    Modell (GUI g) {
        gui = g;
    }

    void oekTeller() {
        ++tellerverdi;
        gui.visTeller(tellerverdi);
    }

    void nullstillTeller () {
        tellerverdi = 0;
        gui.visTeller(tellerverdi);
    }
}

class Kontroll {
    private GUI gui;
    private Modell modell;

    Kontroll() {
        gui = new GUI(this);
        modell = new Modell(gui);
    }

    void oekTeller() {
        modell.oekTeller();
    }

    void nullstillTeller() {
        modell.nullstillTeller();
    }

    void avslutt() {
        System.exit(0);
    }
}

class GUI {
    private Kontroll kontroll;
    private JFrame vindu;
    private JPanel panel;
    private JLabel antall;
    private JButton tell, resett, slutt;
    try {
        UIManager.setLookAndFeel(
            UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception e) {System.exit(1)}

    vindu = new JFrame("Teller");
    vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    panel = new JPanel();
    vind.add(panel);
    antall = new JLabel(" 0 ");

    tell = new JButton(" +1 ");
    class OekTeller implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            kontroll.oekTeller();
        }
    }
    tell.addActionListener(new OekTeller());

    resett = new JButton(" =0 ");
    class Nuller implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            kontroll.nullstillTeller();
        }
    }
    resett.addActionListener(new Nuller());

    slutt = new JButton("Exit");
    class Stopper implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            kontroll.avslutt();
        }
    }
    slutt.addActionListener(new Stopper());

    panel.add(antall);  panel.add(tell);
    panel.add(resett);  panel.add(slutt);

    vindu.pack();   vindu.setVisible(true);

}
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TTT {
    public static void main(String[] args) {
        Kontroll kontroll = new Kontroll();
    }
}
