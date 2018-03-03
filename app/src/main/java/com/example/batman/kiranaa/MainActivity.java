package com.example.batman.kiranaa;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
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
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
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
        private static ArrayList<String> categoryList ;//;= new ArrayList<String>();
        private CategoryCardListAdapter categoryCardListAdapter;
        private DatabaseReference databaseReference;
        private StorageReference storageReference;
        private StorageReference filepath;
        private ArrayList<String> url ;//= new ArrayList<String>();
        private ArrayList<String> categoryKey;// = new ArrayList<String>();
        int categoryCount=0;
        int temp;
    private Task<Void> allTask;



/*-------------------------------------------OnCreate Method----------------------------------------------------------------------------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        categoryList=new ArrayList<String>();
        url=new ArrayList<String>();
        categoryKey=new ArrayList<String>();
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

                categoryCount++;
                temp = 0;
                String value = dataSnapshot.getValue(String.class);
                categoryList.add(value);
                String key = dataSnapshot.getKey();
                categoryKey.add(key);
                filepath = storageReference.child("Categories").child(value + ".jpg");
                Log.v("filepath is", "" + filepath);
//                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//                        Log.v("url is", "" + uri);
//                        url.add(uri.toString());
//                        temp++;
//                        Log.v("temp is", "" + temp);
//                        //init();
//                    }
//
//
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(Exception exception) {
//                        Log.v("error is", "" + exception.getMessage());
//                    }
//                });

                Log.v("value is", "" + value);
                allTask = Tasks.whenAll( filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //Log.v("url is", "" + uri);
                        url.add(uri.toString());
                        temp++;
                        //Log.v("temp is", "" + temp);
                        //init();
                    }


                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception exception) {
                        Log.v("error is", "" + exception.getMessage());
                    }
                }));
                allTask.addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.v("url is", "" + url);
                        Log.v("category is", "" + categoryList);
                        Log.v("temp is", "" + temp);
                      init();
                    }
                });
                allTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Log.v("task failed", "failed task" );
                    }
                });


                //Task<Void> urlTask= Tasks.await(allTask,1,TimeUnit.SECONDS);
//                Log.v("url.size)", "URL SIZE"+url.size() );
//                Log.v("categoryList.size()", "URL SIZE"+categoryList.size() );
//                try {
//                    Log.v("url.size()", "WAITING........"+url.size() );
//                    TimeUnit.SECONDS.sleep(3);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                Log.v("url.size() AGAIN", "URL SIZE AGAIN"+url.size() );
                Log.v("categoryList.size() AGAIN", "categoryList SIZE AGAIN"+categoryList.size() );
//                while (url.size()!=categoryList.size())
//                {
//                    continue;
//                }

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


    public void init(){
        categoryCardListAdapter = new CategoryCardListAdapter(MainActivity.this,categoryList,url);
        categoryListView.setAdapter(categoryCardListAdapter);
    }

  /*  @Override
    public void onRestart(){
        Log.v("position","onRstart");
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
*/
    @Override
    public void onBackPressed() {
        finish();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();


        }

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
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_cart) {
            Fragment cart = new Cart();
            // Creting the bundle to send the clicked category to the nect fragment
            //This will set the bundle as an argument to the object
            // Transaction from current activity to next activity
            android.app.FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main, cart).commit();

        } else if (id == R.id.nav_payment) {
            Fragment checkout = new Checkout();
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main, checkout).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Fragment cart = new Cart();
        // Creting the bundle to send the clicked category to the nect fragment
        //This will set the bundle as an argument to the object
        // Transaction from current activity to next activity
        android.app.FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, cart).commit();
        return true;
    }
}


