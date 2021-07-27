package in.spstech.cloudfileanalyser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JavaHttpClient {
    public static void main(String[] args) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("https://login.microsoftonline.com/a3c3aa24-1525-47ce-8df7-d1a81582fb9d/oauth2/token");

        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "password"));
        params.add(new BasicNameValuePair("client_id", "a232b40c-6d2a-426a-ba84-38e7fe0b9711"));
        params.add(new BasicNameValuePair("client_secret", "em0lVI4-qp41Lan.~z6s.V-X~8eHM49G.g"));
        //6zI47Z_p5a5V~iZEr5CP~4x0p-6IPadxee
        params.add(new BasicNameValuePair("resource", "https://graph.microsoft.com"));
        params.add(new BasicNameValuePair("username", "sumitpalsingh007_gmail.com#EXT#@sumitpalsingh007gmail.onmicrosoft.com"));
        //params.add(new BasicNameValuePair("username", "sumitpalsingh007gmail.onmicrosoft.com"));
        params.add(new BasicNameValuePair("password", "IAmEnough@1"));

        String token = "";
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity respEntity = response.getEntity();

        if (respEntity != null) {
            // EntityUtils to get the response content
            String content =  EntityUtils.toString(respEntity);
            System.out.println(content);
            token = String.valueOf(content.split("access_token\":\"")[1]).split("\"")[0];
        }

        HttpClient httpClient1 = new DefaultHttpClient();
        HttpPost httpPost1 = new HttpPost("https://login.microsoftonline.com/common/oauth2/authorize");

        // Request parameters and other properties.
        List<NameValuePair> params1 = new ArrayList<>();
        params1.add(new BasicNameValuePair("Authorization","Bearer " + token));
        params1.add(new BasicNameValuePair("client_id", "a232b40c-6d2a-426a-ba84-38e7fe0b9711"));
        httpPost1.setEntity(new UrlEncodedFormEntity(params1, "UTF-8"));

        HttpResponse response1 = httpClient1.execute(httpPost1);
        HttpEntity respEntity1 = response1.getEntity();

        if (respEntity1 != null) {
            // EntityUtils to get the response content
            String content =  EntityUtils.toString(respEntity1);
            System.out.println(content);
        }

        HttpClient httpClient2 = new DefaultHttpClient();
        HttpPost httpPost2 = new HttpPost("https://graph.microsoft.com/v1.0/me/drive/root/children");

        // Request parameters and other properties.
        List<NameValuePair> params2 = new ArrayList<>();
        params2.add(new BasicNameValuePair("Authorization","Bearer " + token));
        params2.add(new BasicNameValuePair("client_id", "a232b40c-6d2a-426a-ba84-38e7fe0b9711"));
        httpPost2.setEntity(new UrlEncodedFormEntity(params2, "UTF-8"));

        HttpResponse response2 = httpClient2.execute(httpPost2);
        HttpEntity respEntity2 = response2.getEntity();

        if (respEntity2 != null) {
            // EntityUtils to get the response content
            String content =  EntityUtils.toString(respEntity2);
            System.out.println(content);
        }
    }
}
