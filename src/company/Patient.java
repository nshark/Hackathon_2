package company;
import java.util.*;


public class Patient {
    private ArrayList<String> symptoms;
    private boolean gender;
    private int age;
    private Deque<String> questions;

    public Patient(){

    }
    public Patient(boolean gender, int age){
        this.gender = gender;
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

    public void setGender(boolean gender){
        this.gender = gender;
    }

    public void setAge(int age){
        this.age = age;
    }

    public boolean getGender(){
        return gender;
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
                //String test =(String) msg.get(i).toString();
                org.json.simple.JSONObject test = (org.json.simple.JSONObject) msg.get(i);
                org.json.simple.JSONObject test2 = (org.json.simple.JSONObject) test.get("ability");
                String abilityName = (String) test2.get("name");
                System.out.println(abilityName);
                // System.out.println(person.getInt("key"));
            }
            String name= (String)jsonObject.get("height").toString();
            System.out.println(name);
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
