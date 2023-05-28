package company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.TreeMap;

public class ApiInterface {
    static TreeMap<String, Integer> symptomset = null;
    static public JSONObject getDiagnoses(Patient p){
        // This is going to need to return a jsonarray, not a jsonobject
        return null;
    }
    static public JSONObject getQuestions(Patient p){
        // Double check documentation to make sure of the return type
        return null;
    }
    static public TreeMap<String, Integer> getSymptoms(){
        if(symptomset!=null) return symptomset;
        symptomset = new TreeMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("symptoms.json"));
            JSONParser parse = new JSONParser();
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while(line != null){
                System.out.println(line);
                sb.append(line).append("\n");
                line = br.readLine();
            }
            JSONArray arr = (JSONArray) parse.parse(sb.toString());
            for(Object o: arr) {
                JSONObject o2 = (JSONObject) o;
                symptomset.put(((String)o2.get("Name")).toLowerCase(),Integer.parseInt(o2.get("ID").toString()));
            }
            return symptomset;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
