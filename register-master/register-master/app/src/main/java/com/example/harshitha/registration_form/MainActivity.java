package com.example.harshitha.registration_form;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    Spinner s1,s2;
    RadioButton rb1,rb2;
    RadioGroup rg;
    CheckBox c1,c2,c3,c4,c5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        e3=findViewById(R.id.e3);
        e4=findViewById(R.id.e4);
        s1=findViewById(R.id.s1);
        s2=findViewById(R.id.s2);
        c1=findViewById(R.id.c1);
        c2=findViewById(R.id.c2);
        c3=findViewById(R.id.c3);
        c4=findViewById(R.id.c4);
        c5=findViewById(R.id.c5);
        rb1=findViewById(R.id.rb1);
        rb2=findViewById(R.id.rb2);
        rg=findViewById(R.id.rg);

    }

    public void Submit(View view) {
        Intent i= new Intent(this,Detail.class);
        i.putExtra("name",e1.getText().toString().trim());
        i.putExtra("college",e2.getText().toString().trim());
        i.putExtra("mbl",e3.getText().toString().trim());
        i.putExtra("email",e4.getText().toString().trim());
        i.putExtra("branch",s1.getSelectedItem().toString().trim());
        i.putExtra("year",s2.getSelectedItem().toString().trim());
        int id = rg.getCheckedRadioButtonId();
        RadioButton rb = findViewById(id);
        i.putExtra("gender",rb.getText().toString().trim());
        if(c1.isChecked()==true)
        {
            i.putExtra("box1",c1.getText().toString().trim());
        }

        if(c2.isChecked()==true)
        {
            i.putExtra("box2",c2.getText().toString().trim());
        }

        if(c3.isChecked()==true)
        {
            i.putExtra("box3",c3.getText().toString().trim());
        }
        if(c4.isChecked()==true)
        {
            i.putExtra("box4",c4.getText().toString().trim());
        }
        if(c5.isChecked()==true)
        {
            i.putExtra("box5",c5.getText().toString().trim());
        }

        startActivity(i);
    }
}
