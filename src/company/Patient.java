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
    private ArrayList<String> symptoms;
    private boolean isMale;
    private int age;
    private Deque<String> questions;

    public Patient(){

    }
    public Patient(boolean gender, int age){
        this.isMale = gender;
        this.age = age;
    }

    public void setSymptoms(ArrayList<String> newsymptoms){
        symptoms = newsymptoms;
    }

    public ArrayList<String> getSymptoms(){
        return symptoms;
    }

    public void addSymptom(String input){
        // add a check to make sure the symptom is real
        symptoms.add(input);
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

    public String getQuestion(){
        return null;
    }

    public String getDiagnosis(){
        org.json.simple.JSONObject jsonObject;
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
