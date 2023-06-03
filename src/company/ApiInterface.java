package company;

import company.Model.Gender;
import company.Model.HealthDiagnosis;
import company.Model.HealthItem;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApiInterface {
    static public List<HealthDiagnosis> getDiagnoses(Patient p){
        // This is going to need to return a jsonarray, not a jsonobject
        try {
            return Diagonistic._diagnosisClient.loadDiagnosis(p.getSymptomsIds(),Gender.Male, p.getAge());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    static public List<HealthItem> getQuestions(Patient p){
        // Double check documentation to make sure of the return type
        try {
            return Diagonistic._diagnosisClient.loadProposedSymptoms(p.getSymptomsIds(),Gender.Male, p.getAge());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    static public HashMap<String, Long> getSymptoms(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("symptoms.json"));
            JSONParser parse = new JSONParser();
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while(line != null){
                sb.append(line).append("\n");
                line = br.readLine();
            }
            ArrayList<JSONObject> vn = (ArrayList<JSONObject>) parse.parse(sb.toString());
            HashMap<String, Long> si = new HashMap<>();
            for (JSONObject kl : vn){
                si.put((String) kl.get("Name"), (Long) kl.get("ID"));
            }
            return si;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
