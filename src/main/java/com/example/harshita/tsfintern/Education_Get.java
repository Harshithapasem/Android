package com.example.harshita.tsfintern;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Education_Get extends AppCompatActivity {
    TextView scollege,branch,scity,startyr,endyr;
    RequestQueue queue;

    ImageView edit;
    LinearLayout ll;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education__get);
        scollege=(TextView) findViewById(R.id.tvcollege);
        scity=(TextView) findViewById(R.id.tvcity);
        branch=(TextView) findViewById(R.id.tvbranch);
        startyr=(TextView) findViewById(R.id.tvstyr);
        endyr=(TextView) findViewById(R.id.tvedyr);
        edit=(ImageView)findViewById(R.id.ivedit);
        ll=(LinearLayout)findViewById(R.id.ll);
        Intent in=getIntent();
        final String id=in.getStringExtra("id");
        fetchdata(id);
        fetchphoto(id);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(Education_Get.this,Education_Act.class);
                in.putExtra("id",id);
                startActivity(in);
            }
        });
    }

    private void fetchphoto(String id) {

        RequestQueue requestQueue;

        String URL=Constant.BASE_URL+"/user/personaldetail/profilepic/"+id;
        requestQueue=Volley.newRequestQueue(this.getApplicationContext());
        ImageRequest imageRequest=new ImageRequest(URL, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                ImageView certi=new ImageView(Education_Get.this);
                certi.setImageBitmap(response);
                certi.setMaxWidth(10);
                certi.setMaxHeight(10);
                ll.addView(certi);
                Toast.makeText(Education_Get.this, "Uploaded successfuly", Toast.LENGTH_SHORT).show();

            }
        }, 0, 0, null,null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(imageRequest);

    }

    private void fetchdata(String id) {
        queue= Volley.newRequestQueue(this);
        String URL=Constant.BASE_URL+"/user/educationdetail/"+id;
        Map<String,String> params=new HashMap<>();
        JSONObject parameters=new JSONObject(params);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, URL, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject data =response.getJSONObject("data");
                    String degree=data.getString("degree");
                    String city=data.getString("location");
                    String college=data.getString("organisation");
                    String startYear=data.getString("start_year");
                    String endYear=data.getString("end_year");
                    branch.setText("Degree : "+degree);
                    scity.setText("City : "+city);
                    scollege.setText("College : "+college);
                    startyr.setText("Start Year : "+startYear);
                    endyr.setText("End Year : "+endYear);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }
}
