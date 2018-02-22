package com.example.batman.kiranaa;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class Products extends Fragment {

    private ListView productsListview ;
    private DatabaseReference databaseReference;
    private Products context = this;
    ArrayList<String> productList = new ArrayList<String>();
    HashMap<String,Integer> productCart = new HashMap<>();
//    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
//            this,
//            android.R.layout.simple_list_item_1,
//            productList );
    public Products() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v("products","I am the produts class");

        final View rootView = inflater.inflate(R.layout.fragment_products, container, false);
        productsListview = (ListView) rootView.findViewById(R.id.productsList) ;
        databaseReference = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://kiranaa-575.firebaseio.com/Products/Cleaners");
        databaseReference.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                String key = dataSnapshot.getKey();
                productList.add(key);
                Log.v("hi",""+key);
                productCart.put(key,0);
                populate();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        return rootView;

    }
    public void populate(){
        Log.v("populate","at the funcion");
        ProductsCardListAdapter productsCardListAdapter = new ProductsCardListAdapter(context,productList,productCart);
        productsListview.setAdapter(productsCardListAdapter);
    }

}
