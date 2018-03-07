package com.example.batman.kiranaa;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import org.json.JSONArray;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class OrderHistory extends Fragment {
    OrderHistory context = this;
ArrayList<OrderReader> orderList=new ArrayList<OrderReader>();
    private ListView orderListView;
    private DatabaseReference databaseReference;

    OrderHistoryCardListAdapter orderHistoryCardListAdapter;
    OrderReader [] orderReader = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View orderHistoryInflate = inflater.inflate(R.layout.fragment_order_history, container, false);

        orderListView  = (ListView) orderHistoryInflate.findViewById(R.id.order_list_view);
        databaseReference = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://kiranaa-575.firebaseio.com/UserInfo/vatsal/user_order");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ObjectMapper mapper = new ObjectMapper();
                String json = "";

                try {
                   json  = mapper.writeValueAsString(dataSnapshot.getValue());
                   Log.v("string","to Json"+json);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

                try {
                    orderReader = mapper.readValue(json,OrderReader[].class);
                    for (int k=0;k<orderReader.length;k++)
                    {
                        orderList.add(orderReader[k]);
                    }
                    Log.v("order value",""+orderReader);



                        //Log.v("order value",""+mapper.writeValueAsString(orderReader[j]));
                        callView();
                       // Log.v("calview ended","callview finished");



                } catch (JsonParseException exception){
                    Log.v("error","Json Parser"+exception);
                } catch (JsonMappingException e){
                    Log.v("error","Mapping"+e);
                }
                catch (IOException e) {
                    Log.v("error","IoException"+e);
                    e.printStackTrace();
                }
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
      //  callView();
        return orderHistoryInflate;

    }
    public void callView(){
//        for (int i=0;i<orderReader.length;i++)
//        {
            Log.v("callviewloop","callviewloop:");
            //Log.v("callviewloop","order:"+new ObjectMapper().writeValueAsString(orderReader[i]));
            orderHistoryCardListAdapter = new OrderHistoryCardListAdapter(context, orderList);
            orderListView.setAdapter(orderHistoryCardListAdapter);}

    //}

}
