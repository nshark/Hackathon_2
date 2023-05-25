package company;
import java.util.*;


public class Patient {
    private ArrayList<String> symptoms;
    private String gender;
    private int age;

    public Patient(){

    }
    public Patient(String gender, int age){
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

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setAge(int age){
        this.age = age;
    }

    public String getGender(){
        return gender;
    }

    public int getAge(){
        return age;
    }

    public String getQuestion(){
        return null;
    }

    public String getDiagnosis(){
        return null;
    }

}
