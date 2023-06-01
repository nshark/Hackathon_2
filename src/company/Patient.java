package company;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


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
    //TODO: This should return a int[] to be fed to the API, not a TreeSet<String>
    public TreeSet<String> getSymptoms(){
        return symptoms;
    }

    public boolean addSymptom(String input){
        // add a check to make sure the symptom is real
        if(ApiInterface.getSymptoms().keySet().contains(input)) {
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
    public String getGender(){if(isMale){return "Male";}else{return "Female";}}
    public ArrayList<String> getQuestions(){
        org.json.simple.JSONObject jsonObject = ApiInterface.getQuestions(this);


        return new ArrayList<String>();
    }


    public ArrayList<String> getDiagnoses(){
//        org.json.simple.JSONArray allIssues = ApiInterface.getDiagnoses(this);
        ArrayList<String> result = new ArrayList<>();
//        org.json.simple.JSONArray allIssues = null;
        org.json.simple.JSONArray allIssues = new JSONArray();
        try {
            int n = allIssues.size();
            for (int i = 0; i < n; i++) {
                org.json.simple.JSONObject issue = (org.json.simple.JSONObject) allIssues.get(i);
                HashMap<String, String> issueInfo = (HashMap<String, String>) issue.get("Issue");
                String issueName = issueInfo.get("Name");
                result.add(issueName);
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
