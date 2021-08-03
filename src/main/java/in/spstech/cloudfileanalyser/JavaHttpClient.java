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

    public static final String CLIENT_ID = "8c7a3a91-cc76-48b5-aed2-93e1791682f1";
    //"a232b40c-6d2a-426a-ba84-38e7fe0b9711";
    public static final String CLIENT_SECRET = "2vVnbV.Cz._vIbz1isd1_62G3LxGQ-Qqg4";
    //"em0lVI4-qp41Lan.~z6s.V-X~8eHM49G.g";
    public static final String USER_NAME = "sumitpal@klassify.org";
    //"sumitpalsingh007_gmail.com#EXT#@sumitpalsingh007gmail.onmicrosoft.com";
    public static final String PASSWORD = "IAmEnough@1";
    public static final String URl = "https://login.microsoftonline.com/adc279f0-657c-4bc8-9e3e-f3665ae24f5b/oauth2/token";
    //"https://login.microsoftonline.com/sumitpalsingh007gmail.onmicrosoft.com/oauth2/token";

    public static void main(String[] args) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(URl);

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "password"));
        params.add(new BasicNameValuePair("client_id", CLIENT_ID));
        params.add(new BasicNameValuePair("client_secret", CLIENT_SECRET));
        params.add(new BasicNameValuePair("resource", "https://graph.microsoft.com"));
        params.add(new BasicNameValuePair("username", USER_NAME));
        params.add(new BasicNameValuePair("password", PASSWORD));

        String token = "";
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity respEntity = response.getEntity();

        if (respEntity != null) {
            String content =  EntityUtils.toString(respEntity);
            token = String.valueOf(content.split("access_token\":\"")[1]).split("\"")[0];
            System.out.println("Token --" + token);
        }

        HttpClient httpClient2 = new DefaultHttpClient();
        HttpPost httpPost2 = new HttpPost("https://graph.microsoft.com/v1.0/me/drive/root/children");

        Map map = new HashMap();
        map.put("Authorization", "Bearer " + token);
        map.put("client_id", CLIENT_ID);

        httpPost2.setHeader("Authorization","Bearer " + token);
        httpPost2.setHeader("client_id", CLIENT_ID);
        httpPost2.setHeader("Content-Type", "application/json");

        ObjectMapper om = new ObjectMapper();
        StringEntity stringEntity = new StringEntity(om.writeValueAsString(map));
        stringEntity.setContentType("application/json");
        httpPost2.setEntity(stringEntity);

        HttpResponse response2 = httpClient2.execute(httpPost2);
        HttpEntity respEntity2 = response2.getEntity();

        if (respEntity2 != null) {
            String content =  EntityUtils.toString(respEntity2);
            System.out.println(content);
        }
    }
}
