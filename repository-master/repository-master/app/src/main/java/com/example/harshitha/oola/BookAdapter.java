package com.example.harshitha.oola;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder>
{
    Context context;
    ArrayList<Book> bookArrayList;

    public BookAdapter(MainActivity mainActivity, ArrayList<Book> booksList)
    {
        this.context=mainActivity;
        this.bookArrayList=booksList;
    }


    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(context).inflate(R.layout.bookitem,parent,false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        Picasso.with(context).load(bookArrayList.get(position).getImgUrl())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    public class BookHolder extends RecyclerView.ViewHolder
    {
        @InjectView(R.id.imgview)ImageView img;

        public BookHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this,itemView);
        }
    }
}

