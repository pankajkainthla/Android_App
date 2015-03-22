package travis.thenewboston.com.thenewboston;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by pankaj on 19-Mar-15.
 */
public class Menu extends ListActivity {

    String classes[] = {"startingPoint", "TextPlay", "Email", "Camera", "Data", "SQliteExample", "MapsActivity",
            "HttpExample", "MapsActivity", "MapsActivity", "MapsActivity"};

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);
        String cheese = classes[position];
        try {
            Class ourClass = Class.forName("travis.thenewboston.com.thenewboston." + cheese);
            Intent ourIntent = new Intent(Menu.this, ourClass);
            startActivity(ourIntent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));

    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater blowUp = getMenuInflater();
        blowUp.inflate(R.menu.cool_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.aboutUs:
                Intent i = new Intent("travis.thenewboston.com.thenewboston.AboutUs");
                startActivity(i);
                break;
            case R.id.preferences:
                Intent p = new Intent("travis.thenewboston.com.thenewboston.Prefs");
                startActivity(p);
                break;
            case R.id.exit:

                finish();
                break;

        }


        return false;
    }
}
