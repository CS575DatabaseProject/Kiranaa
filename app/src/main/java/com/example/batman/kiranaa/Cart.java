package com.example.batman.kiranaa;

import android.app.Activity;
import android.app.FragmentManager;
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

import java.util.ArrayList;


public class Cart extends Fragment {
    Cart context = this;
    private ListView listView;
    private Button button;
    private TextView totalPrice;
    Singleton var = Singleton.getInstance();
    String [] key;
    int tempPrice ;
    int count = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("cart------------------",""+var.carthash);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        count++;
//        Toast.makeText(getActivity(), "Oncreate method call", Toast.LENGTH_SHORT).show();
        if(var.variable == false){
            Toast.makeText(getActivity(), "Cart is empty", Toast.LENGTH_SHORT).show();
            return null;
        }
        else {
            // Inflate the layout for this fragment
            final View cartView = inflater.inflate(R.layout.fragment_cart, container, false);
            listView = (ListView) cartView.findViewById(R.id.cart_listView);
            button = (Button) cartView.findViewById(R.id.chekout_payment);
            totalPrice = (TextView) cartView.findViewById(R.id.product_total_price);


            key = var.carthash.keySet().toArray(new String[0]);
            CartCardListAdapter cartCardListAdapter = new CartCardListAdapter(context,key);
            for (int i = 0; i < key.length; i++) {
                tempPrice = var.cartPrice.get(key[i]) * var.carthash.get(key[i]);
                var.totalPrice = tempPrice + var.totalPrice;
            }
            totalPrice.setText("Total Price :"+ var.totalPrice);
            tempPrice = 0;
            var.totalPrice = 0;


            listView.setAdapter(cartCardListAdapter);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (String key : var.carthash.keySet()) {
                        Log.v("carthashkey",""+key);
                        OrderInfo oi=new OrderInfo(key,var.carthash.get(key),var.cartPrice.get(key));
                        var.orderList.add(oi);
                    }
                    Fragment checkout = new Checkout();
                    FragmentManager fragmentManager = getActivity().getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_main, checkout).commit();

                }
            });
            return cartView;
        }

    }







}
