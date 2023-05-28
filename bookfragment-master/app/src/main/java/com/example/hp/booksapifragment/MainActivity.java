package com.example.hp.booksapifragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity
{
    @InjectView(R.id.ed_book)EditText ed_book;
    @InjectView(R.id.recycler)RecyclerView recyclerView;
    RequestQueue requestQueue;
    ArrayList<Book> booksList;
    BooksAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue= Volley.newRequestQueue(this);
        ButterKnife.inject(this);
    }

    public void fetchData(View view)
    {

        String bookName=ed_book.getText().toString();
        String link="https://www.googleapis.com/books/v1/volumes?q="+bookName;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try {
                    booksList=new ArrayList<>();
                    String title,poster;
                    JSONObject root=new JSONObject(response);
                    JSONArray items=root.getJSONArray("items");
                    for (int i=0;i<items.length();i++){
                        JSONObject jsonObject=items.getJSONObject(i);
                        JSONObject volumeInfo=jsonObject.getJSONObject("volumeInfo");
                        title=volumeInfo.optString("title");
                        JSONObject imglink=volumeInfo.getJSONObject("imageLinks");
                        poster=imglink.optString("thumbnail");
                        Toast.makeText(MainActivity.this, ""+title, Toast.LENGTH_SHORT).show();
                        Book book=new Book(title,poster);
                        booksList.add(book);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, ""+response,
                        Toast.LENGTH_SHORT).show();
                adapter=new BooksAdapter(MainActivity.this,booksList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,
                        2));
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}
