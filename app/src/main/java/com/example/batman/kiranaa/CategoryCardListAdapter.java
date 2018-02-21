package com.example.batman.kiranaa;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v4.app.FragmentManager;
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

import java.util.ArrayList;

/**
 * Created by tarun on 2/14/2018.
 */

public class CategoryCardListAdapter extends BaseAdapter {
    Intent intent;
    Context context;
    ArrayList<String> category = new ArrayList<String>();
    private static LayoutInflater inflater=null;
    ArrayList<String> urlToImg = new ArrayList<String>();

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

        //https://firebasestorage.googleapis.com/v0/b/kiranaa-575.appspot.com/o/Categories%2FCleaners.png?alt=media&token=e6f157dc-f765-451d-a6a7-76fbbcdded78
        final int position = i;
        Holder holder=new Holder();
        View categoryView;
        categoryView = inflater.inflate(R.layout.category_card_list, null);
        holder.textView = (TextView) categoryView.findViewById(R.id.category_text);
        holder.imageView = (ImageView) categoryView.findViewById(R.id.category_image);
        holder.textView.setText(category.get(i));
        /*Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);*/
        //Picasso.with(context).load("gs://kiranaa-575.appspot.com/Categories/Cleaners.png").resize(100,90).into(holder.imageView);
        Glide.with(context).load(urlToImg.get(i)).into(holder.imageView);
        categoryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context,category.get(position), Toast.LENGTH_SHORT).show();
//                MainActivity.onClickListnerFunction(position);
                Fragment products = new Products();
                android.app.FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main, products).commit();
            }
        });
        return categoryView;
    }


}
/*------for the image upload--------------
*         storageReference = FirebaseStorage.getInstance().getReference();

*           final StorageReference filepath = storageReference.child("Categories").child("Cleaners.png");

        filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Toast.makeText(context, "here", Toast.LENGTH_SHORT).show();
            }


        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception exception) {
                Toast.makeText(context, ""+exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
*
*     StorageReference storageReference ;

*
*
* */