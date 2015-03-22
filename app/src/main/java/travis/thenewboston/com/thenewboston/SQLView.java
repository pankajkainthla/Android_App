package travis.thenewboston.com.thenewboston;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by pankaj on 22-Mar-15.
 */
public class SQLView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlview);
        TextView tv = (TextView) findViewById(R.id.tvSQLinfo);

        HotOrNot info = new HotOrNot(this);
        try {
            info.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String data = info.getData();

        tv.setText(data);
    }
}
