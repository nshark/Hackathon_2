package company;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

    public TreeSet<String> getSymptoms(){
        return symptoms;
    }

    public boolean addSymptom(String input){
        // add a check to make sure the symptom is real
        symptoms.add(input);
        return true;
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
        org.json.simple.JSONObject jsonObject = ApiInterface.getQuestions(this);
        return null;
        // JOEY: RETURN EMPTY ARRAYLIST IF NO QUESTIONS, NOT NULL PLEASE
    }

    public ArrayList<String> getDiagnoses(){
        // JOEY: RETURN EMPTY ARRAYLIST IF NO QUESTIONS, NOT NULL PLEASE
        org.json.simple.JSONObject jsonObject = ApiInterface.getDiagnoses(this);
        try {

            org.json.simple.JSONArray msg = (org.json.simple.JSONArray) jsonObject.get("Issue");

            int n =   msg.size(); //(msg).length();
            for (int i = 0; i < n; i++) {
                org.json.simple.JSONObject test = (org.json.simple.JSONObject) msg.get(i);
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
