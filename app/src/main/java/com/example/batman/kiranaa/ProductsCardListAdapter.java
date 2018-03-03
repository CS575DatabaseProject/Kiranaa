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

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;


public class ProductsCardListAdapter extends BaseAdapter {
    /*-----------------Setting the variable-----------------------*/
    private Products context;
    private ArrayList<String> productListArray = new ArrayList<String>();
    private ArrayList<String> productListArrayValue = new ArrayList<>();
    private ArrayList<String> urlToImg = new ArrayList<String>();
    private static LayoutInflater inflater = null;
    private int count = 0;
    private HashMap<String, Integer> productCount;
    Singleton var = Singleton.getInstance();

    public ProductsCardListAdapter(){
        //Empty constructor
    }
    /*--------------------------------------Setting the constructor --------------------------*/
    public ProductsCardListAdapter(Products products, ArrayList<String> productList,ArrayList<String> productListValue, HashMap<String, Integer> productCart,ArrayList<String> url) {
        context = products;
        productListArray = productList;
        inflater = (LayoutInflater) context.getActivity().getSystemService(context.getActivity().LAYOUT_INFLATER_SERVICE);
        productCount = productCart;
        productListArrayValue = productListValue;
        urlToImg = url;

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
        private TextView productValue;


    }

    /*------------------------------Inflating the view here ----------------------------*/
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final int position = i;
        //productCount = new HashMap<>();
        //Creating and inflating the view
        View productView;
        productView = inflater.inflate(R.layout.product_card_list, null);
        if (urlToImg.size()<i+1)
        {return productView;}
        // initialising all the variables of the class product holder
        final ProductHolder productHolder = new ProductHolder();
        productHolder.textViewProductName = (TextView) productView.findViewById(R.id.product_category_text);
        productHolder.buttonAdd = (Button) productView.findViewById(R.id.buttonPlus);
        productHolder.buttonRemove = (Button) productView.findViewById(R.id.buttonMinus);
        productHolder.textViewNumber = (TextView) productView.findViewById(R.id.productCount);
        productHolder.productValue = (TextView)  productView.findViewById(R.id.product_value);
        productHolder.imageView = (ImageView) productView.findViewById(R.id.category_image);
        Glide.with(context).load(urlToImg.get(i)).into(productHolder.imageView);
        //Setting the context
        productHolder.textViewProductName.setText(productListArray.get(i));
        productHolder.productValue.setText(productListArrayValue.get(i)+ " $");
        productHolder.textViewNumber.setText("0");
        Log.v("hashmap",""+productCount.keySet());


        productHolder.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context.getActivity(), ""+Integer.parseInt(productListArrayValue.get(i)), Toast.LENGTH_SHORT).show();
                var.carthash.put(productListArray.get(i), productCount.get(productListArray.get(i)) + 1);
                var.cartPrice.put(productListArray.get(i),Integer.parseInt(productListArrayValue.get(i)));
                productCount.put(productListArray.get(i), var.carthash.get(productListArray.get(i)));
                productHolder.textViewNumber.setText(productCount.get(productListArray.get(i)).toString());
            }
        });
        productHolder.buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (productCount.get(productListArray.get(i))== 0) {
                    Toast.makeText(context.getActivity(), "value is already null", Toast.LENGTH_SHORT).show();
                } else {
                    var.cartPrice.put(productListArray.get(i),Integer.parseInt(productListArrayValue.get(i)));
                    var.carthash.put(productListArray.get(i), productCount.get(productListArray.get(i)) - 1);
                    productCount.put(productListArray.get(i), var.carthash.get(productListArray.get(i)));
                    productHolder.textViewNumber.setText(productCount.get(productListArray.get(i)).toString());
                }
            }
        });



        return productView;
    }







}
