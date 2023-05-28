package com.example.harshitha.registration_form;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class Detail extends AppCompatActivity {
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        t = findViewById(R.id.t);
        t.setMovementMethod(new ScrollingMovementMethod());
        String s = getIntent().getStringExtra("name");
        String s1 = getIntent().getStringExtra("college");
        String s2 = getIntent().getStringExtra("mbl");
        String s3 = getIntent().getStringExtra("email");
        String s4 = getIntent().getStringExtra("branch");
        String s5 = getIntent().getStringExtra("year");

        String s6 = getIntent().getStringExtra("gender");
        String s7 = getIntent().getStringExtra("box1");
        String s8 = getIntent().getStringExtra("box2");
        String s9 = getIntent().getStringExtra("box3");
        String s10 = getIntent().getStringExtra("box4");
        String s11 = getIntent().getStringExtra("box5");
        String k = check(s7, s8, s9, s10, s11);
        t.setText("Name:\n" + s + "\n\nCollegename:\n" + s1 + "\n\nPhone:\n" + s2 + "\n\nEmail:\n" + s3 + "\n\nBranch:\n" + s4 + "\n\nYear:\n"
                + s5 + "\n\nGender:\n" + s6 + "\n\nInterests:" + k);
    }

    private String check(String s7, String s8, String s9, String s10, String s11) {
        String p = "";
        if (s7 != null) {
            p+= "\n"+s7;
        }
        if (s8 != null) {
            p+= "\n"+s8;;
        }
        if (s9 != null) {
            p+= "\n"+s9;
        }
        if (s10 != null){
            p+= "\n"+s10;
        }

            if (s11 != null) {

                p+= "\n"+s11;}
            return p;

        }


}

