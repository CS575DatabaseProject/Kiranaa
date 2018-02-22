package com.example.batman.kiranaa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Tarun on 2/21/2018.
 */

public class ProductsCardListAdapter extends BaseAdapter {
    /*-----------------Setting the variable-----------------------*/
    Products context;
    ArrayList<String> productListArray = new ArrayList<String>();
    private static LayoutInflater inflater=null;
    public int count = 0;
    /*--------------------------------------Setting the constructor --------------------------*/
    public  ProductsCardListAdapter(Products products,ArrayList<String> productList){
        context = products;
        productListArray = productList;
        inflater = (LayoutInflater) context.getActivity().getSystemService(context.getActivity().LAYOUT_INFLATER_SERVICE);


    }
    /*-------------------------default methods of class BaseAdapter-------------------------*/
    @Override
    public int getCount() {
      return  productListArray.size();
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
    class ProductHolder{
        private TextView textViewProductName;
        private ImageView imageView;
        private TextView textViewNumber;
        private Button buttonAdd;
        private Button buttonRemove;

    }
    /*------------------------------Inflating the view here ----------------------------*/
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       //Creating and inflating the view
       View productView ;
       productView = inflater.inflate(R.layout.product_card_list, null);
       // initialising all the variables of the class product holder
       final ProductHolder productHolder = new ProductHolder();
       productHolder.textViewProductName = (TextView) productView.findViewById(R.id.product_category_text);
       productHolder.buttonAdd = (Button) productView.findViewById(R.id.buttonPlus);
       productHolder.buttonRemove = (Button) productView.findViewById(R.id.buttonMinus);
       productHolder.textViewNumber = (TextView) productView.findViewById(R.id.productCount) ;

       //Setting the context
       productHolder.textViewProductName.setText(productListArray.get(i));
       productHolder.textViewNumber.setText("0");

        productHolder.buttonAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               count++;
               productHolder.textViewNumber.setText(Integer.toString(count));

           }
       });
       productHolder.buttonRemove.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(count == 0){
                   Toast.makeText(context.getActivity(), "value is already null", Toast.LENGTH_SHORT).show();
               }
               else {
                   count--;
                   productHolder.textViewNumber.setText(Integer.toString(count));
               }
           }
       });


       return productView;
    }
}
