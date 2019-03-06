package com.example.mikey.del2;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;


import java.util.ArrayList;
import java.util.stream.IntStream;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList clickcounts;
    TextView groupa;
    TextView groupb;
    TextView cata;
    TextView catb;
    TextView sannolikhet;

    EditText catA;
    EditText catB;
    EditText groupA;
    EditText groupB;
    Button buttonA, buttonB, buttonC, buttonD;
    float clickcountA = 0;
    float clickcountB = 0;
    float clickcountC = 0;
    float clickcountD = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickcounts = new ArrayList<>();

        sannolikhet = findViewById(R.id.sannolikhet);
        groupa = findViewById(R.id.firstgroup);
        groupb = findViewById(R.id.secondgroup);
        cata = findViewById(R.id.firstcat);
        catb = findViewById(R.id.secondcat);

        catA = findViewById(R.id.inputcatA);
        catB = findViewById(R.id.inputcatB);
        groupA = findViewById(R.id.inputgroupA);
        groupB = findViewById(R.id.inputgroupB);

        buttonA = findViewById(R.id.button3);
        buttonA.setOnClickListener(this);
        buttonB = findViewById(R.id.button4);
        buttonB.setOnClickListener(this);
        buttonC = findViewById(R.id.button5);
        buttonC.setOnClickListener(this);
        buttonD = findViewById(R.id.button6);
        buttonD.setOnClickListener(this);

    }
    public void inputSave(View v)
    {
        iscategoryEmpty();
        isgroupEmpty();
        String addedcatA = catA.getText().toString();
        String addedcatB = catB.getText().toString();
        String addedgroupA = groupA.getText().toString();
        String addedgroupB = groupB.getText().toString();

        groupa.setText(addedgroupA);
        groupb.setText(addedgroupB);
        cata.setText(addedcatA);
        catb.setText(addedcatB);
    }
    public void iscategoryEmpty()
    {
        if (catA.getText().toString().trim().length() == 0 || catB.getText().toString().trim().length() == 0)
        {
            catA.setError("Not enough categories set");
            catB.setError("Not enough categories set");

        }
    }

    public void isgroupEmpty()
    {
        if (groupA.getText().toString().trim().length() == 0 || groupB.getText().toString().trim().length() == 0)
        {
            groupA.setError("Not enough groups set");
            groupB.setError("Not enough groups set");

        }
    }
    public void reloadApp(View v)
    {
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.button3:
                clickcountA++;
                buttonA.setText(Float.toString(clickcountA));
                break;
            case R.id.button4:
                clickcountB++;
                buttonB.setText(Float.toString(clickcountB));
                break;
            case R.id.button5:
                clickcountC++;
                buttonC.setText(Float.toString(clickcountC));
                break;
            case R.id.button6:
                clickcountD++;
                buttonD.setText(Float.toString(clickcountD));
                break;
        }

    }

    public void chiSquared(View v)
    {

        clickcounts.add(clickcountA);
        clickcounts.add(clickcountB);
        clickcounts.add(clickcountC);
        clickcounts.add(clickcountD);

        float a = clickcountA;
        float b = clickcountB;
        float c = clickcountC;
        float d = clickcountD;
        float total = a+b+c+d;
        float ad = a * d;
        float bc = b * c;
        double adbc =  (ad - bc);
        double adbcu = Math.pow(adbc, 2);

        float ab = a + b;
        float cd = c + d;
        float bd = b + d;
        float ac = a + c;
        double abcdbdac = (ab*cd*bd*ac);

        double chisquare = (adbcu / abcdbdac * total);
        float pvalue = (float) 3.841;
        if (chisquare > pvalue)
        {
            sannolikhet.setText(String.valueOf("Sannolikheten för att resultatet är oberoende av grupp är < 0,05" + "   ||   "+ "Chi2: " + chisquare));
        }
        else
            sannolikhet.setText(String.valueOf("Sannolikheten för att resultatet är oberoende av grupp är > 0,05" + "   ||   "+ "Chi2: " + chisquare));
    }


}
