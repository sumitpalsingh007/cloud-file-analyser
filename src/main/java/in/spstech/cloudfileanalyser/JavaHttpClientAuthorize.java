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
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaHttpClientAuthorize {
    public static void main(String[] args) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost("https://login.microsoftonline.com/adc279f0-657c-4bc8-9e3e-f3665ae24f5b/oauth2/v2.0/authorize?client_id=8c7a3a91-cc76-48b5-aed2-93e1791682f1&response_type=code&prompt=admin_consent");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "password"));

        params.add(new BasicNameValuePair("client_id", "8c7a3a91-cc76-48b5-aed2-93e1791682f1"));


        params.add(new BasicNameValuePair("client_secret", "2vVnbV.Cz._vIbz1isd1_62G3LxGQ-Qqg4"));

        params.add(new BasicNameValuePair("resource", "https://graph.microsoft.com"));

        params.add(new BasicNameValuePair("username", "sumitpal@klassify.org"));
        params.add(new BasicNameValuePair("password", "IAmEnough@1"));

        String token = "";
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity respEntity = response.getEntity();

        if (respEntity != null) {
            // EntityUtils to get the response content
            String content =  EntityUtils.toString(respEntity);


            System.out.println("content --" + content);
        }
    }
}
