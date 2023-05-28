package company;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;

public class ApiInterface {
    static public JSONObject getDiagnoses(Patient p){
        // This is going to need to return a jsonarray, not a jsonobject
        return null;
    }
    static public JSONObject getQuestions(Patient p){
        // Double check documentation to make sure of the return type
        return null;
    }
    static public HashMap<String, Integer> getSymptoms(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("symptoms.json"));
            JSONParser parse = new JSONParser();
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while(line != null){
                sb.append(line).append("\n");
                line = br.readLine();
            }
            return (HashMap<String, Integer>) parse.parse(sb.toString());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
