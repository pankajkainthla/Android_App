package travis.thenewboston.com.thenewboston;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by pankaj on 22-Mar-15.
 */
public class SQliteExample extends Activity implements View.OnClickListener {

    Button sqlUpdate, sqlView, sqlModify, sqlGetInfo, sqlDelete;
    EditText sqlName, sqlHotness, sqlRow;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bSQLUpdate:
                boolean diditWork = true;
                try {

                    String name = sqlName.getText().toString();
                    String hotness = sqlHotness.getText().toString();
                    HotOrNot entry = new HotOrNot(this);
                    entry.open();
                    entry.createEntry(name, hotness);

                    entry.close();
                } catch (SQLException e) {
                    diditWork = false;

                } finally {
                    if (diditWork) {
                        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage("success");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();

                    } else {

                        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage("error");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();


                    }
                }

                break;

            case R.id.bSQLopenView:

                Intent i = new Intent("travis.thenewboston.com.thenewboston.SQLView");
                startActivity(i);

                break;
            case R.id.bGetInfo:

                String s = sqlRow.getText().toString();
                long l = Long.parseLong(s);
                HotOrNot hon = new HotOrNot(this);
                try {
                    hon.open();
                    String returnedName = hon.getName(l);
                    String returnedHotness = hon.getHotness(l);
                    hon.close();
                    sqlName.setText(returnedName);
                    sqlHotness.setText(returnedHotness);

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.bSQLmodify:
                String mName = sqlName.getText().toString();
                String mHotness = sqlHotness.getText().toString();
                String sRow = sqlRow.getText().toString();
                long lRow = Long.parseLong(sRow);

                HotOrNot ex = new HotOrNot(this);
                try {
                    ex.open();
                    ex.updateEntry(lRow, mName, mHotness);
                    ex.close();
                } catch (SQLException e) {
                    e.printStackTrace();

                }


                break;

            case R.id.bSQLdelete:
                String sRowDelete = sqlRow.getText().toString();
                long lRowDelete = Long.parseLong(sRowDelete);
                HotOrNot delete = new HotOrNot(this);
                try {
                    delete.open();
                    delete.deleteEntry(lRowDelete);
                    delete.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {

                } catch (Exception e) {

                }


                break;

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqliteexample);

        sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
        sqlName = (EditText) findViewById(R.id.etSQLName);
        sqlHotness = (EditText) findViewById(R.id.etSQLHotness);

        sqlView = (Button) findViewById(R.id.bSQLopenView);
        sqlView.setOnClickListener(this);
        sqlUpdate.setOnClickListener(this);

        sqlRow = (EditText) findViewById(R.id.etSQLrowInfo);
        sqlModify = (Button) findViewById(R.id.bSQLmodify);
        sqlGetInfo = (Button) findViewById(R.id.bGetInfo);
        sqlDelete = (Button) findViewById(R.id.bSQLdelete);

        sqlDelete.setOnClickListener(this);
        sqlModify.setOnClickListener(this);
        sqlGetInfo.setOnClickListener(this);

    }
}
