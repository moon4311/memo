import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

public class HttpUtil {

  public static void main(String[] args){
    // post("","");
  }
  
  
  
	public static String get(String urlS) {

	    try {
	      URL url = new URL(urlS);
	      HttpURLConnection con = (HttpURLConnection) url.openConnection();
	      con.setRequestMethod("GET");
	      con.setRequestProperty("Content-Type", "application/json; utf-8");
	      con.setRequestProperty("Accept", "application/json");
	      con.setDoOutput(true);

	      //OutputStream os = con.getOutputStream();
	      //byte[] input = body.getBytes("utf-8");
	      //os.write(input);
	      BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));


	      StringBuilder response = new StringBuilder();
	      String responseLine = null;
	      while ((responseLine = br.readLine()) != null) {
	        response.append(responseLine.trim());
	      }
	      //JSONParser parser = new JSONParser();
        return response.toString();
	      //return (JSONObject) parser.parse(response.toString());

	    } catch (MalformedURLException e) {
	      e.printStackTrace();
	      return null;
	    } catch (Exception e) {
	      e.printStackTrace();
	      return null;
	    }
	  }
  
	public static JSONObject post(String urlS, String body) {

	    try {
	      URL url = new URL(urlS);
	      HttpURLConnection con = (HttpURLConnection) url.openConnection();
	      con.setRequestMethod("POST");
	      con.setRequestProperty("Content-Type", "application/json; utf-8");
	      con.setRequestProperty("Accept", "application/json");
	      con.setDoOutput(true);

	      OutputStream os = con.getOutputStream();
	      byte[] input = body.getBytes("utf-8");
	      os.write(input);

	      BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));


	      StringBuilder response = new StringBuilder();
	      String responseLine = null;
	      while ((responseLine = br.readLine()) != null) {
	        response.append(responseLine.trim());
	      }
	      JSONParser parser = new JSONParser();

	      return (JSONObject) parser.parse(response.toString());

	    } catch (MalformedURLException e) {
	      e.printStackTrace();
	      return null;
	    } catch (Exception e) {
	      e.printStackTrace();
	      return null;
	    }
	  }
}
