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
import android.widget.Button;
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
    private Button cartButton;
    private DatabaseReference databaseReference;
    private Products context = this;
    public ArrayList<String> productListKey = new ArrayList<String>();
    public ArrayList<String> productListValue = new ArrayList<String>();
    public HashMap<String,Integer> productCart = new HashMap<>();
    private Bundle bundle;
    private String currentCategory;
    Singleton var = Singleton.getInstance();

    /*------------------------------------------------Overridden methods-----------------------------------------------------------*/
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // getting the bundle from the previous fragment with the clicked category value
        bundle = getArguments();
        currentCategory = bundle.getString("CurrentCategoryName");
        // inflating the view
        final View rootView = inflater.inflate(R.layout.fragment_products, container, false);
        // Initialising the list view for th products of the respective category
        productsListview = (ListView) rootView.findViewById(R.id.productsList) ;
        cartButton = (Button) rootView.findViewById(R.id.buttonAddToCart) ;
        // creating database reference to get the data from firebase
        databaseReference = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://kiranaa-575.firebaseio.com/Products/"+currentCategory);

        // Getting the data here
        databaseReference.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey();
                String value =  dataSnapshot.getValue().toString();
                productListKey.add(key);
                productListValue.add(value);
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


        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("hash value is",""+var.carthash);
                Toast.makeText(context.getActivity(), "Added to cart", Toast.LENGTH_SHORT).show();

            }
        });

        return rootView;

    }
    public void populate(){
        Log.v("populate","at the funcion");
        ProductsCardListAdapter productsCardListAdapter = new ProductsCardListAdapter(context,productListKey,productListValue,productCart);
        productsListview.setAdapter(productsCardListAdapter);
    }

}




/*------------------------------------------------------------If required-------------------------------------------------------*/
/*public Products() {
        // Required empty public constructor
    }*/