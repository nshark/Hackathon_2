package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainLayout {
    JFrame mf = new JFrame();
    JTextField in = new JTextField();
    JLabel symptomlabel = new JLabel(" Symptom Searcher: ");
    JButton add = new JButton("Add Symptom");
    JTextArea out;
    JButton submit = new JButton("Call Your AI Doctor With These Symptoms!");
    Patient p;
    public MainLayout(Patient p) {
        this.p = p;
        mf.setLayout(new BorderLayout());
        mf.setSize(800, 600);
        in = new JTextField();
        JPanel sfield = new JPanel(new BorderLayout());
        sfield.add(symptomlabel, BorderLayout.WEST);
        sfield.add(in, BorderLayout.CENTER);
        JPanel top = new JPanel(new GridLayout(3, 1));
        top.add(sfield);
        top.add(add);
        top.add(submit);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.addSymptom(in.getText());
            }
        });
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] questions = p.getQuestions();
            }
        });
        mf.add(top, BorderLayout.NORTH);
        mf.setVisible(true);
    }
}
