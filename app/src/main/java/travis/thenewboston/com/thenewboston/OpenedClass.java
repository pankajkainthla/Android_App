package travis.thenewboston.com.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by pankaj on 21-Mar-15.
 */
public class OpenedClass extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    TextView question, test;
    Button returnData;
    RadioGroup selectionList;
    String gotBread, sendData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);

        initialixevars();

        Bundle gotBasket = getIntent().getExtras();
        gotBread = gotBasket.getString("key");
        question.setText(gotBread);

    }

    private void initialixevars() {

        returnData = (Button) findViewById(R.id.bReturn);
        question = (TextView) findViewById(R.id.tvQuestion);
        test = (TextView) findViewById(R.id.tvText);
        returnData.setOnClickListener(this);
        selectionList = (RadioGroup) findViewById(R.id.rgAnswers);
        selectionList.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) this);

    }

    @Override
    public void onClick(View v) {

        Intent person = new Intent();
        Bundle backPack = new Bundle();
        backPack.putString("answer", sendData);
        person.putExtras(backPack);
        setResult(RESULT_OK, person);
        finish();


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rCrazy:
                sendData = "Probably right";
                break;
            case R.id.rSexy:
                sendData = "Definietly right";
                break;
            case R.id.rBoth:
                sendData = "Spot on";
                break;

        }

        test.setText(sendData);
    }
}
