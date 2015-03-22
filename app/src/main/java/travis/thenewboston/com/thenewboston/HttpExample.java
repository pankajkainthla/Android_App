package travis.thenewboston.com.thenewboston;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by pankaj on 22-Mar-15.
 */
public class HttpExample extends Activity {

    TextView httpStuff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpexample);

        httpStuff = (TextView) findViewById(R.id.tvHttp);
        new Read().execute(new String[]{"http://thenewboston.com"});
/*
        GetMethodEx test=new GetMethodEx();
        try {
          //  String returned=test.getInternetData();
            String returned=test.doInBackground(new String[] { "http://thenewboston.com" });

            httpStuff.setText(returned);
        } catch (Exception e) {
            e.printStackTrace();
            httpStuff.setText(e.toString());
        }
        */
    }


    public class Read extends AsyncTask<String, Integer, String> {

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
                httpStuff.setText(e.toString());
            }

            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            httpStuff.setText("Loadeddddddd..." + result);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
            httpStuff.setText("Loaded..." + values[0]);

        }

    }
}
