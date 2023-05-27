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
    Patient p = new Patient();
    JPanel main = new JPanel(new GridLayout(4,1));
    JPanel displaysymptoms = new JPanel(new GridLayout(2, 5));
    TreeSet<String> symptoms = new TreeSet<>();
    JPanel split;
    public MainLayout(Patient patient) {
        p = patient;
        System.out.println(p.getMale());
         split = new JPanel();
        split.add(new JLabel(" Click to Remove: ", SwingConstants.CENTER), BorderLayout.WEST);
        split.add(displaysymptoms, BorderLayout.CENTER);
        main.add(split);
        main.add(new JLabel(""));
        main.add(new JLabel(""));
//        this.p = p;
        mf.setLayout(new BorderLayout());
        mf.setSize(1200, 800);
        in = new JTextField();
        JPanel sfield = new JPanel(new BorderLayout());
        sfield.add(symptomlabel, BorderLayout.WEST);
        sfield.add(in, BorderLayout.CENTER);
        JPanel top = new JPanel(new GridLayout(4, 1));
        JPanel gandb = new JPanel(new GridLayout(1,2));
        JPanel gender = new JPanel(new GridLayout(1,2));

        JLabel glabel = new JLabel("Currently Male: ");
        gender.add(glabel);
        JButton toggle = new JButton("Change to Female");
        toggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(p.getMale()==true) {
                    p.setMale(false);
                    glabel.setText("Currently Female: ");
                    toggle.setText("Change to Male");

                }
                else {
                    p.setMale(true);
                    glabel.setText("Currently Male: ");
                    toggle.setText("Change to Female");
                }
                mf.pack();
                mf.setSize(1200, 800);
            }
        });
        gender.add(toggle);

        gender.add(new JLabel(""));




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

                boolean valid = p.addSymptom(in.getText());
                if(valid) {
                    updateSymptomLayout();
                }
                else {
                    in.setText("Invalid Symptom. Try Again");
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
                    updateBottomLayout();
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
    public void updateBottomLayout() {
        main.removeAll();
        main.add(split);


        JPanel questionpanel = new JPanel(new BorderLayout());
        ArrayList<String> questions = p.getQuestions();
        if(questions == null) questions = new ArrayList<>();
//        for(int i = 0; i<20; i++) questions.add("Hola");
        JTextArea newsymptoms = new JTextArea();
        newsymptoms.setEditable(false);
        JPanel instructions = new JPanel(new GridLayout(2,1));
        instructions.add(new JLabel("Add The Symptoms Below Using the Interface Above If You Are Experiencing Them:", SwingConstants.CENTER));
        instructions.add(new JLabel("Then, Reclick to Request Your AI Doctor's Updated Opinion!", SwingConstants.CENTER));
        questionpanel.add(instructions, BorderLayout.NORTH);
        for(String s: questions) newsymptoms.append(s + "\n");
        JScrollPane scroller = new JScrollPane(newsymptoms);
        questionpanel.add(scroller, BorderLayout.CENTER);
        main.add(questionpanel);

        JTextArea chattext = new JTextArea();
        chattext.setEditable(false);

        JScrollPane chatInfo = new JScrollPane(chattext);

        JPanel diagnosespanel = new JPanel(new BorderLayout());
        JPanel instructions2 = new JPanel(new GridLayout(3,1));
        instructions2.add(new JLabel("Below Are Our AI Doctor's Possible Diagnoses For Your Illness", SwingConstants.CENTER));
        instructions2.add(new JLabel("Answer Our AI Overlord's Questions to Narrow Down Your Diagnoses, OR...", SwingConstants.CENTER));
        instructions2.add(new JLabel("Click On a Diagnosis to Get Some Information About It From ChatGPT's Rude Sibling", SwingConstants.CENTER));
        diagnosespanel.add(instructions2, BorderLayout.NORTH);
        ArrayList<String> list = p.getDiagnoses();
        if(list == null) list = new ArrayList<>();
        for(int i = 0; i<100; i++) list.add("Impending Death");
        JPanel diagnoses = new JPanel(new GridLayout(list.size(), 1));


        for(int i = 0; i<list.size(); i++) {
            final String diagnosis = list.get(i);
            JButton button = new JButton(diagnosis);
            diagnoses.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    chattext.setText(ChatGPT.getInfo(diagnosis));
                    mf.pack();
                    mf.setSize(1200, 800);
                }
            });
        }
        JScrollPane scroller2 = new JScrollPane(diagnoses);
        diagnosespanel.add(scroller2);
        main.add(diagnosespanel);
        main.add(chatInfo);
        mf.pack();
        mf.setSize(1200, 800);
    }
    public void updateSymptomLayout() {
        displaysymptoms.removeAll();
        for(String s: p.getSymptoms()) {
            JButton button = new JButton(s);
            button.addActionListener(new ActionListener() {
                final String remove = s;
                @Override
                public void actionPerformed(ActionEvent e) {
                    p.removeSymptom(remove);
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
