package com.example.batman.kiranaa;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.util.TimeUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
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
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


        //objects for listview, firebaseadapter and firebaselistoption initialize
        private static Context context;
        private ListView categoryListView;
        private FirebaseListAdapter<String> firebaseListAdapter;
        private FirebaseListOptions<String> options ;
        private static ArrayList<String> categoryList = new ArrayList<String>();
        private CategoryCardListAdapter categoryCardListAdapter;
        private DatabaseReference databaseReference;
        private StorageReference storageReference;
        private StorageReference filepath;
        private ArrayList<String> url = new ArrayList<String>();
        private ArrayList<String> categoryKey = new ArrayList<String>();
        int temp;

/*-------------------------------------------OnCreate Method----------------------------------------------------------------------------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        //database for categories initialized
        databaseReference = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://kiranaa-575.firebaseio.com/Categories");
        storageReference = FirebaseStorage.getInstance().getReference();


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        categoryListView = (ListView) findViewById(R.id.category_listview);

        databaseReference.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                temp = 0;
                String value = dataSnapshot.getValue(String.class);
                categoryList.add(value);
                String key = dataSnapshot.getKey();
                categoryKey.add(key);
                filepath = storageReference.child("Categories").child(value+".jpg");
                Log.v("filepath is",""+filepath);
                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.v("url is",""+uri);
                        url.add(uri.toString());
                        temp++;
                        Log.v("temp is",""+temp);
                        init();
                    }


                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception exception) {
                        Log.v("error is",""+exception.getMessage());
                    }
                });

                Log.v("value is", "" + value);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                String key = dataSnapshot.getKey();
                int index = categoryKey.indexOf(key);
                categoryList.set(index,value);
                init();
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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



    }
    

    /*----------------------------------------------End of OnCreate Method----------------------------------------------------------------------------------------*/

    public static void onClickListnerFunction(int position){
        Log.v("Name",""+ categoryList.get(position));

    }
    public void init(){
        categoryCardListAdapter = new CategoryCardListAdapter(MainActivity.this,categoryList,url);
        categoryListView.setAdapter(categoryCardListAdapter);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public void onRestart(){
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
    /*--------------------------------------------------------------------------------------------------------------------------------------*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    /*---------------------------------------------------------------------------------------------------------------------------------------*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_category) {

        } else if (id == R.id.nav_payment) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


/*------For getting the data for list--*/
 /*databaseReference.addChildEventListener(new ChildEventListener() {

@Override
public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                *//*---------------Setting up all the  variables----------------------*//*
        String value = dataSnapshot.getValue(String.class);
        categoryList.add(value);
        String key = dataSnapshot.getKey();
        categoryKey.add(key);
        filepath = storageReference.child("Categories").child(value+".jpg");
        Log.v("filepath is",""+filepath);
        filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
@Override
public void onSuccess(Uri uri) {
        Log.v("url is",""+uri);
        url.add(uri.toString());
        init();
        }


        }).addOnFailureListener(new OnFailureListener() {
@Override
public void onFailure(Exception exception) {
        Log.v("error is",""+exception.getMessage());
        }
        });
                *//*----------------Getting the filepath for every image-------------*//*

        Log.v("value is", "" + value);
        try {
        TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        e.printStackTrace();
        }

        }

@Override
public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        String value = dataSnapshot.getValue(String.class);
        String key = dataSnapshot.getKey();
        int index = categoryKey.indexOf(key);
        categoryList.set(index,value);
        init();
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
*/