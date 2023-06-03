package company;
import java.util.*;

import company.Model.HealthDiagnosis;
import company.Model.HealthItem;
import org.json.simple.JSONArray;


public class Patient {
    private TreeSet<String> symptoms = new TreeSet<>();
    private boolean isMale = true;
    private int age;
//    private Deque<String> questions;

    private ApiInterface apiInterface = new ApiInterface();

    public Patient(){

    }
    public Patient(boolean gender, int age){
        this.isMale = gender;
        this.age = age;
    }

    public void setSymptoms(TreeSet<String> newsymptoms){
        symptoms = newsymptoms;
    }

    public TreeSet<String> getSymptoms(){
        return symptoms;
    }
    public List<Integer> getSymptomsIds(){
        ArrayList<Integer> symp = new ArrayList<>();
        String[] si = (String[]) symptoms.toArray();
        HashMap<String, Long> sm = ApiInterface.getSymptoms();
        for (int i = 0; i < symptoms.size(); i++) {
            symp.add(Math.toIntExact(sm.get(si[i])));
        }
        return symp;
    }
    public boolean addSymptom(String input){
        // add a check to make sure the symptom is real
        if(ApiInterface.getSymptoms().containsKey(input)) {
            symptoms.add(input);
            return true;
        }
        return false;
        // JOEY: RETURN FALSE IF IT IS AN INVALID SYMPTOM
    }
    public void removeSymptom(String input){
        symptoms.remove(input);
    }

    public void setMale(boolean male){
        this.isMale = male;
    }

    public void setAge(int age){
        this.age = age;
    }

    public boolean getMale(){
        return isMale;
    }

    public int getAge(){
        return age;
    }

    public ArrayList<String> getQuestions(){
        List<HealthItem> jsonObject = ApiInterface.getQuestions(this);


        return new ArrayList<String>();
    }


    public ArrayList<String> getDiagnoses(){
        List<HealthDiagnosis> allIssues = ApiInterface.getDiagnoses(this);
        ArrayList<String> result = new ArrayList<>();
//        org.json.simple.JSONArray allIssues = null;
        try {
            int n = allIssues.size();
            for (int i = 0; i < n; i++) {
                HealthDiagnosis issue = allIssues.get(i);;
                String issueName = issue.Issue.IcdName;
                result.add(issueName);
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
