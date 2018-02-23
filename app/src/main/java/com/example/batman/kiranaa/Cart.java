package com.example.batman.kiranaa;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;


public class Cart extends Fragment {
    private ListView listView;
    private Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View cartView = inflater.inflate(R.layout.fragment_cart, container, false);
        listView = (ListView) cartView.findViewById(R.id.cart_listView);
        button = (Button) cartView.findViewById(R.id.chekout_payment);

        return cartView;
    }







}
