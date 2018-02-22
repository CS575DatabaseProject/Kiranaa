package com.example.batman.kiranaa;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class ProductsCardListAdapter extends BaseAdapter {
    /*-----------------Setting the variable-----------------------*/
    private Products context;
    private ArrayList<String> productListArray = new ArrayList<String>();
    private static LayoutInflater inflater = null;
    private int count = 0;
    private HashMap<String, Integer> productCount;

    /*--------------------------------------Setting the constructor --------------------------*/
    public ProductsCardListAdapter(Products products, ArrayList<String> productList, HashMap<String, Integer> productCart) {
        context = products;
        productListArray = productList;
        inflater = (LayoutInflater) context.getActivity().getSystemService(context.getActivity().LAYOUT_INFLATER_SERVICE);
        productCount = productCart;

    }

    /*-------------------------default methods of class BaseAdapter-------------------------*/
    @Override
    public int getCount() {
        return productListArray.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    /*---------------------Class that holds the variables for a single card ----------------*/
    private class ProductHolder {
        private TextView textViewProductName;
        private ImageView imageView;
        private TextView textViewNumber;
        private Button buttonAdd;
        private Button buttonRemove;

    }

    /*------------------------------Inflating the view here ----------------------------*/
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final int position = i;
        //productCount = new HashMap<>();
        //Creating and inflating the view
        View productView;
        productView = inflater.inflate(R.layout.product_card_list, null);
        // initialising all the variables of the class product holder
        final ProductHolder productHolder = new ProductHolder();
        productHolder.textViewProductName = (TextView) productView.findViewById(R.id.product_category_text);
        productHolder.buttonAdd = (Button) productView.findViewById(R.id.buttonPlus);
        productHolder.buttonRemove = (Button) productView.findViewById(R.id.buttonMinus);
        productHolder.textViewNumber = (TextView) productView.findViewById(R.id.productCount);

        //Setting the context
        productHolder.textViewProductName.setText(productListArray.get(i));
        productHolder.textViewNumber.setText("0");
        Log.v("hashmap",""+productCount.keySet());

        productHolder.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /*count++;
               productHolder.textViewNumber.setText(Integer.toString(count));*/
               //Toast.makeText(context.getActivity(), "" + productCount.get(productListArray.get(position)), Toast.LENGTH_SHORT).show();
               productCount.put(productListArray.get(position), productCount.get(productListArray.get(position)) + 1);
               productHolder.textViewNumber.setText(productCount.get(productListArray.get(position)).toString());
            }
        });
        productHolder.buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (productCount.get(productListArray.get(position))== 0) {
                    Toast.makeText(context.getActivity(), "value is already null", Toast.LENGTH_SHORT).show();
                } else {
                    productCount.put(productListArray.get(position), productCount.get(productListArray.get(position)) - 1);
                    productHolder.textViewNumber.setText(productCount.get(productListArray.get(position)).toString());
                }
            }
        });


        return productView;
    }
}
