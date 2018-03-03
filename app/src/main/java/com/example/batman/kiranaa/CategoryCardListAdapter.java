package com.example.batman.kiranaa;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

import java.util.ArrayList;



public class CategoryCardListAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> category = new ArrayList<String>();
    private static LayoutInflater inflater=null;
    ArrayList<String> urlToImg = new ArrayList<String>();
    private Bundle arguments = new Bundle();

    public CategoryCardListAdapter (MainActivity mainActivity, ArrayList<String> categoryList, ArrayList<String> url){
        context = mainActivity;
        category = categoryList;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        urlToImg = url;
    }
    @Override
    public int getCount() {
        return category.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class Holder{
        TextView textView ;
        ImageView imageView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.v("i is", "" + i);
        Log.v("urlToImg size is", "" + urlToImg.size());

        //Declaring all the variables
        final int position = i;
        Holder holder=new Holder();
        View categoryView;
        //Inflating the view
        categoryView = inflater.inflate(R.layout.category_card_list, null);
        if (urlToImg.size()<i+1)
        {return categoryView;}
        //initialising the variable of class Holder
        holder.textView = (TextView) categoryView.findViewById(R.id.category_text);
        holder.imageView = (ImageView) categoryView.findViewById(R.id.category_image);

        //Setting the text and the image of the card with the values received from mainActivity

        holder.textView.setText(category.get(i));
        Log.v("i is", "" + i);
        Log.v("urlToImg is", "" + urlToImg.get(i));

        Glide.with(context).load(urlToImg.get(i)).into(holder.imageView);

        //On click should go to respective category products
        categoryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Object foe the next fragment
                Fragment productName = new Products();
                // Creting the bundle to send the clicked category to the nect fragment
                arguments.putString( "CurrentCategoryName" , category.get(position));
                //This will set the bundle as an argument to the object
                productName.setArguments(arguments);
                // Transaction from current activity to next activity
                android.app.FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main, productName).commit();
            }
        });
        return categoryView;
    }


}
