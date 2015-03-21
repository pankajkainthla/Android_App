package travis.thenewboston.com.thenewboston;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.Random;

/**
 * Created by pankaj on 19-Mar-15.
 */
public class TextPlay extends Activity implements View.OnClickListener {

    Button chkCmd;
    ToggleButton passTog;
    EditText input;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);
        baconandeggs();


        chkCmd.setOnClickListener(this);
        passTog.setOnClickListener(this);

    }

    private void baconandeggs() {
        chkCmd = (Button) findViewById(R.id.bResults);
        passTog = (ToggleButton) findViewById(R.id.tbPassword);
        input = (EditText) findViewById(R.id.etCommands);
        display = (TextView) findViewById(R.id.tvResults);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvResults:
                String check = input.getText().toString();
                if (check.contentEquals("left")) {

                    display.setGravity(Gravity.LEFT);


                } else if (check.contentEquals("center")) {

                    display.setGravity(Gravity.CENTER);


                } else if (check.contentEquals("right")) {

                    display.setGravity(Gravity.RIGHT);


                } else if (check.contentEquals("blue")) {

                    display.setBackgroundColor(Color.BLUE);


                } else if (check.contentEquals("red")) {
                    display.setBackgroundColor(Color.RED);


                } else {

                    display.setText("awsome");
                    Random random = new Random();
                    display.setTextSize(random.nextInt(50));


                }
                break;
            case R.id.tbPassword:
                if (passTog.isChecked()) {
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);


                } else {
                    input.setInputType(InputType.TYPE_CLASS_TEXT);

                }
                break;
            default:
                break;

        }
    }
}
