package travis.thenewboston.com.thenewboston;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by pankaj on 22-Mar-15.
 */
public class GetMethodEx extends AsyncTask<String, Integer, String> {


    @Override
    protected String doInBackground(String... params) {

        String response = "";
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(params[0]);
        try {
            HttpResponse execute = client.execute(httpGet);
            InputStream content = execute.getEntity().getContent();

            BufferedReader buffer = new BufferedReader(
                    new InputStreamReader(content));
            String s = "";
            while ((s = buffer.readLine()) != null) {
                response += s;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }


    public String getInternetData() throws Exception {

        BufferedReader in = null;
        String data = null;
        try {
            HttpClient client = new DefaultHttpClient();
            URI website = new URI("http://www.mybringback.com");
            HttpGet request = new HttpGet();
            request.setURI(website);
            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String l = "";
            String nl = System.getProperty("line.separator");

            while ((l = in.readLine()) != null) {

                sb.append(l + nl);

            }
            in.close();
            data = sb.toString();
            return data;


        } finally {
            if (in != null) {

                try {
                    in.close();
                    return data;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }


}
