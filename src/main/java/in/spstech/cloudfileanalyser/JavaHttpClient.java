package in.spstech.cloudfileanalyser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaHttpClient {
    public static void main(String[] args) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        //HttpPost httpPost = new HttpPost("https://login.microsoftonline.com/sumitpalsingh007gmail.onmicrosoft.com/oauth2/token");
        HttpPost httpPost = new HttpPost("https://login.microsoftonline.com/adc279f0-657c-4bc8-9e3e-f3665ae24f5b/oauth2/token");
        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "password"));
        //params.add(new BasicNameValuePair("client_id", "a232b40c-6d2a-426a-ba84-38e7fe0b9711"));
        params.add(new BasicNameValuePair("client_id", "8c7a3a91-cc76-48b5-aed2-93e1791682f1"));

        //params.add(new BasicNameValuePair("client_secret", "em0lVI4-qp41Lan.~z6s.V-X~8eHM49G.g"));
        params.add(new BasicNameValuePair("client_secret", "2vVnbV.Cz._vIbz1isd1_62G3LxGQ-Qqg4"));

        params.add(new BasicNameValuePair("resource", "https://graph.microsoft.com"));
        //params.add(new BasicNameValuePair("username", "sumitpalsingh007_gmail.com#EXT#@sumitpalsingh007gmail.onmicrosoft.com"));
        params.add(new BasicNameValuePair("username", "sumitpal@klassify.org"));
        params.add(new BasicNameValuePair("password", "IAmEnough@1"));

        String token = "";
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity respEntity = response.getEntity();

        if (respEntity != null) {
            // EntityUtils to get the response content
            String content =  EntityUtils.toString(respEntity);

            token = String.valueOf(content.split("access_token\":\"")[1]).split("\"")[0];
            System.out.println("Token --" + token);
        }

        HttpClient httpClient2 = new DefaultHttpClient();
        HttpPost httpPost2 = new HttpPost("https://graph.microsoft.com/v1.0/me/drive/root/children");

        // Request parameters and other properties.
        Map map = new HashMap();
        map.put("Authorization", "Bearer " + token);
        map.put("client_id", "a232b40c-6d2a-426a-ba84-38e7fe0b9711");
        //map.put("client_id", "c296f2cb-4380-4df3-873c-b4e22282e649");

        httpPost2.setHeader("Authorization","Bearer " + token);
        httpPost2.setHeader("client_id", "a232b40c-6d2a-426a-ba84-38e7fe0b9711");
        //httpPost2.setHeader("client_id", "c296f2cb-4380-4df3-873c-b4e22282e649");
        httpPost2.setHeader("Content-Type", "application/json");

        ObjectMapper om = new ObjectMapper();
        StringEntity stringEntity = new StringEntity(om.writeValueAsString(map));
        stringEntity.setContentType("application/json");
        httpPost2.setEntity(stringEntity);

        HttpResponse response2 = httpClient2.execute(httpPost2);
        HttpEntity respEntity2 = response2.getEntity();

        if (respEntity2 != null) {
            // EntityUtils to get the response content
            String content =  EntityUtils.toString(respEntity2);
            System.out.println(content);
        }
    }
}
