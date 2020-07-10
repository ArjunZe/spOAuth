/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spOAuth;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.json.*;

/**
 *
 * @author mreddy
 */
@ManagedBean
@SessionScoped
public class Auth {

    private static String relm;

    public String check(String site, String clientId, String clientSceret, String authcode) {
        //System.out.println(site + " " + clientId + " " + clientSceret + "  c " + authcode);
        String line;
        StringBuilder jsonString = new StringBuilder();
        try {
            //http://stackoverflow.com/questions/15570656/how-to-send-request-payload-to-rest-api-in-java
            URL url = new URL("https://accounts.accesscontrol.windows.net/" + Auth.getBearer(site) + "/tokens/OAuth/2");
            //URL url2 = new URL("https://2cd8c0169a0eac41c33a481d9043d3e0.m.pipedream.net");
            String client_id = URLEncoder.encode(clientId, "UTF-8");
            String client_secret = URLEncoder.encode(clientSceret, "UTF-8");
            String redirect_uri = URLEncoder.encode("https://localhost:8443/spOAuth/callback.jsp", "UTF-8");
            String resource = URLEncoder.encode("00000003-0000-0ff1-ce00-000000000000/" + site + ".sharepoint.com@" + relm, "UTF-8");
            String payload = "grant_type=authorization_code&client_id=" + client_id + "%40" + relm + "&client_secret=" + client_secret + "&code=" + authcode + "&redirect_uri=" + redirect_uri + "&resource=" + resource;
            //String encodePayload = URLEncoder.encode(payload, "UTF-8");
            System.out.println(url);
            System.out.println(payload);
            byte[] postData = payload.getBytes(StandardCharsets.UTF_8); //https://stackoverflow.com/questions/40574892/how-to-send-post-request-with-x-www-form-urlencoded-body/40576153
            int postDataLength = postData.length;
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            connection.setUseCaches(false);
            //OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            //writer.write(postData);
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.write(postData);
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getResponseCode() / 100 == 2 ? connection.getInputStream() : connection.getErrorStream()));
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }

            br.close();
            connection.disconnect();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        String s = jsonString.toString();
        JSONObject jsonObject = new JSONObject(s);   //http://stackoverflow.com/questions/16574482/decoding-json-string-in-java
       // String token = jsonObject.getString("success");
        //System.out.println("icSessionId "+token);
        //String surl = jsonObject.getString("serverUrl");
        relm=null;
        return s;
    }

    public static String getBearer(String site) {
        String line;
        try {
            String urlr = "https://" + site + ".sharepoint.com/_vti_bin/client.svc";
            URL url = new URL(urlr);                                                                  //"https://app2.informaticacloud.com/saas/api/v2/agent/0001MT08000000000090"
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            //connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer ");
            line = connection.getHeaderField("WWW-Authenticate");
            System.out.println(line);
            relm = line.replace("\"", "").split(",")[0].split("=")[1];
            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return relm;
    }

}
