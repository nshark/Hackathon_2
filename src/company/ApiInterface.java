package company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

public class ApiInterface {
    static TreeMap<String, Integer> symptomset = null;
    static public JSONObject getDiagnoses(Patient p){
        // This is going to need to return a jsonarray, not a jsonobject
    public static String key;
    static public String updateAPIkey(){
        return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im5vYWhfc2l0a29mZjI1QG1pbHRvbi5lZHUiLCJyb2xlIjoiVXNlciIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL3NpZCI6IjEyMzUwIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy92ZXJzaW9uIjoiMjAwIiwiaHR0cDovL2V4YW1wbGUub3JnL2NsYWltcy9saW1pdCI6Ijk5OTk5OTk5OSIsImh0dHA6Ly9leGFtcGxlLm9yZy9jbGFpbXMvbWVtYmVyc2hpcCI6IlByZW1pdW0iLCJodHRwOi8vZXhhbXBsZS5vcmcvY2xhaW1zL2xhbmd1YWdlIjoiZW4tZ2IiLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL2V4cGlyYXRpb24iOiIyMDk5LTEyLTMxIiwiaHR0cDovL2V4YW1wbGUub3JnL2NsYWltcy9tZW1iZXJzaGlwc3RhcnQiOiIyMDIzLTA1LTIzIiwiaXNzIjoiaHR0cHM6Ly9zYW5kYm94LWF1dGhzZXJ2aWNlLnByaWFpZC5jaCIsImF1ZCI6Imh0dHBzOi8vaGVhbHRoc2VydmljZS5wcmlhaWQuY2giLCJleHAiOjE2ODU2MzQ2NjIsIm5iZiI6MTY4NTYyNzQ2Mn0.Qto04EgS6SFFOK8CN53I7W7WRf0fAmUz6DysIGRNz3M";
    }
    static public JSONArray getDiagnoses(Patient p){
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL("https://sandbox-healthservice.priaid.ch/diagnosis?language=en-gb&token="+key+"&symptoms=[234]&year_of_birth="+p.getAge()+"&gender="+p.getGender());
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            boolean ls = true;
            System.out.println(url.toString());
            while(ls){
                int v = br.read();
                if (v==-1){
                    ls = false;
                }
                else{
                    sb.append((char) v);

                }
            }
            System.out.println(sb);
            JSONParser parser = new JSONParser();
            return (JSONArray) parser.parse(sb.toString());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

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
