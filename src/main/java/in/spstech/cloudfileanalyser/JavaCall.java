package in.spstech.cloudfileanalyser;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class JavaCall {

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://login.microsoftonline.com/a3c3aa24-1525-47ce-8df7-d1a81582fb9d/oauth2/token");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);

        Map<String,String> arguments = new HashMap<>();
        arguments.put("grant_type", "password");
        arguments.put("client_id", "a232b40c-6d2a-426a-ba84-38e7fe0b9711");
        arguments.put("resource", "https://graph.microsoft.com");
        arguments.put("username", "sumitpalsingh007_gmail.com#EXT#@sumitpalsingh007gmail.onmicrosoft.com");
        //sumitpalsingh007gmail.onmicrosoft.com
        arguments.put("password", "IAmEnough@1");

        StringJoiner sj = new StringJoiner("&");
        for(Map.Entry<String,String> entry : arguments.entrySet())
            sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                    + URLEncoder.encode(entry.getValue(), "UTF-8"));
        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        http.connect();
        try(OutputStream os = http.getOutputStream()) {
            os.write(out);
        }
        System.out.println(http.getInputStream().read());
    }
}
