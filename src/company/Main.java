package company;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main implements Runnable, KeyListener {
    static MainLayout layout;
    static public Patient p;
    public static void main(String[] args) {
        p = new Patient();
        p.setMale(true);
        layout = new MainLayout(p);
        Diagonistic.setup();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        layout.updateSymptomList();
        System.out.println("ran");
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        System.out.println(p.getMale());
        MainLayout layout = new MainLayout(p);

    }
}
