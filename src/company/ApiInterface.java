package company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.TreeMap;

public class ApiInterface {
    static public JSONArray getDiagnoses(Patient p){
        // This is going to need to return a jsonarray, not a jsonobject
        JSONParser parser = new JSONParser();
        try {
            return (JSONArray) parser.parse(" [  \n" +
                    "       {  \n" +
                    "          \"ID\":188,\n" +
                    "          \"Name\":\"Abwehrspannung (Bauch)\"\n" +
                    "       },\n" +
                    "       {  \n" +
                    "          \"ID\":238,\n" +
                    "          \"Name\":\"Angst\"\n" +
                    "       },\n" +
                    "       {  \n" +
                    "          \"ID\":97,\n" +
                    "          \"Name\":\"Aphthen\"\n" +
                    "       },\n" +
                    "       {  \n" +
                    "          \"ID\":54,\n" +
                    "          \"Name\":\"Appetitminderung\"\n" +
                    "       },\n" +
                    "       {  \n" +
                    "          \"ID\":131,\n" +
                    "          \"Name\":\"Appetitzunahme\"\n" +
                    "       },\n" +
                    "       {  \n" +
                    "          \"ID\":250,\n" +
                    "          \"Name\":\"Atemabhängige Schmerzen\"\n" +
                    "       },\n" +
                    "        \n" +
                    "    ]");
        } catch (ParseException e) {
            return null;
        }
    }
    static public JSONArray getQuestions(Patient p){
        // Double check documentation to make sure of the return type
        JSONParser parser =new JSONParser();
        try {
            return (JSONArray) parser.parse("    [  \n" +
                    "   {  \n" +
                    "      \"ID\":9,\n" +
                    "      \"Name\":\"Kopfschmerzen\"\n" +
                    "   },\n" +
                    "   {  \n" +
                    "      \"ID\":11,\n" +
                    "      \"Name\":\"Fieber\"\n" +
                    "   },\n" +
                    "   {  \n" +
                    "      \"ID\":44,\n" +
                    "      \"Name\":\"Übelkeit\"\n" +
                    "   },\n" +
                    "   {  \n" +
                    "      \"ID\":50,\n" +
                    "      \"Name\":\"Durchfall\"\n" +
                    "   },\n" +
                    "   {  \n" +
                    "      \"ID\":10,\n" +
                    "      \"Name\":\"Bauchschmerzen\"\n" +
                    "   }\n" +
                    "]\n");
        } catch (ParseException e) {
            return null;
        }
    }
    static public TreeMap<String, Integer> getSymptoms(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("symptoms.json"));
            JSONParser parse = new JSONParser();
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while(line != null){
                sb.append(line).append("\n");
                line = br.readLine();
            }
            TreeMap<String,Integer> map = new TreeMap<>();
            JSONArray arr = (JSONArray)parse.parse(sb.toString());
            for(Object o: arr) {
                System.out.println(o);
                JSONObject o2 = (JSONObject) o;
                map.put(((String)o2.get("Name")).toLowerCase(), Integer.parseInt(o2.get("ID").toString()));
//                o2.get("name");
            }
            return map;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
