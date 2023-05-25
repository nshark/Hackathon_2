package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeSet;

public class MainLayout {
    JFrame mf = new JFrame();
    JTextField in = new JTextField();
    JLabel symptomlabel = new JLabel(" Symptom Searcher: ");
    JButton add = new JButton("Add Symptom");
    JTextArea out;
    JButton submit = new JButton("Call Your AI Doctor With These Symptoms!");
    Patient p;
    JPanel main = new JPanel(new GridLayout(3,1));
    JPanel displaysymptoms = new JPanel(new GridLayout(2, 5));
    TreeSet<String> symptoms = new TreeSet<>();
    public MainLayout(Patient p) {


        JPanel split = new JPanel();
        split.add(new JLabel(" Click to Remove: ", SwingConstants.CENTER), BorderLayout.WEST);
        split.add(displaysymptoms, BorderLayout.CENTER);
        main.add(split);
        this.p = p;
        mf.setLayout(new BorderLayout());
        mf.setSize(1200, 800);
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
//                boolean valid = p.input(in.getText());
                boolean valid = true;
                if(valid) {
                    symptoms.add(in.getText());
                    updateSymptomLayout();
                }
                else {
                    in.setText("Invalid Input");
                }
            }
        });
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                ArrayList<String> questions = p.getQuestions();
                /*
                add layout stuff
                */
            }
        });
        mf.add(top, BorderLayout.NORTH);
        mf.add(main, BorderLayout.CENTER);
        mf.setVisible(true);
    }

    public void updateSymptomLayout() {
        System.out.println(symptoms.size());
        System.out.println("ran");
        displaysymptoms.removeAll();
        for(String s: symptoms) {
            JButton button = new JButton(s);
            button.addActionListener(new ActionListener() {
                final String remove = s;
                @Override
                public void actionPerformed(ActionEvent e) {
                    symptoms.remove(remove);
                    updateSymptomLayout();
                }
            });
            displaysymptoms.add(button);
        }
        mf.pack();
        mf.setSize(1200, 800);
        mf.setVisible(true);

    }

}
