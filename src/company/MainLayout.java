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
    public MainLayout(Patient patient) {
        p = patient;

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
        JPanel top = new JPanel(new GridLayout(4, 1));
        JPanel gandb = new JPanel(new GridLayout(1,2));
        JPanel gender = new JPanel(new GridLayout(1,3));

        gender.add(new JLabel("Male"));

        JToggleButton toggle = new JToggleButton("Male");
        toggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.setMale(!p.getMale());
            }
        });
        gender.add(toggle);

        gender.add(new JLabel("Female"));




        JPanel birth = new JPanel(new BorderLayout());
        birth.add(new JLabel("     Birth Year: "), BorderLayout.WEST);
        JTextField year = new JTextField();
        birth.add(year, BorderLayout.CENTER);



        gandb.add(gender);
        gandb.add(birth);
        top.add(gandb);
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
                try {
                    p.setAge(Integer.parseInt(year.getText()));
                }
                catch(Exception ex) {
                    year.setText("Invalid Number Format, Try Again");
                }
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
