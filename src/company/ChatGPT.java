package company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatGPT {

    public static String chatGPT(String text) throws Exception {
        String url = "https://api.openai.com/v1/chat/completions";
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer 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");
//      please pretend to be a kind doctor in your future responses
//      Of course, I can certainly adopt the persona of a kind and compassionate doctor in my future responses. Please feel free to ask any medical questions or share your concerns, and I'll do my best to provide you with helpful and empathetic information.
//        if(true)return;
        JSONObject data = new JSONObject();
        data.put("model", "gpt-3.5-turbo");
        JSONArray arr = new JSONArray();

        JSONObject past = new JSONObject();
        past.put("role", "user");
        past.put("content", "please pretend to be my doctor in your future responses");
        arr.add(past);

        JSONObject response = new JSONObject();
        response.put("role", "assistant");
        response.put("content", "Of course, I can certainly adopt the persona of your kind and compassionate doctor in my future responses. Please feel free to ask any medical questions or share your concerns, and I'll do my best to provide you with helpful and empathetic information.");
        arr.add(response);

        JSONObject prompt = new JSONObject();
        prompt.put("role", "user");
        prompt.put("content", "Please provide some information on " + text + " and what to do if I get diagnosed with it.");
        arr.add(prompt);

        data.put("messages",arr);

//        curl https://api.openai.com/v1/chat/completions \
//        -H "Content-Type: application/json" \
//        -H "Authorization: Bearer $OPENAI_API_KEY" \
//        -d '{
//        "model": "gpt-3.5-turbo",
//                "messages": [{"role": "user", "content": "Hello!"}]


        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                .reduce((a, b) -> a + b).get();
//        System.out.println(output);

        JSONParser parser = new JSONParser();
        JSONArray responsearr = (JSONArray) ((JSONObject) parser.parse(output)).get("choices");
        String strresponse = (String)((JSONObject)((JSONObject)responsearr.get(0)).get("message")).get("content");
//        System.out.println(strresponse);
        return strresponse;
//        System.out.println(new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text"));
    }
}
