package com.example.batman.kiranaa;


import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CartCardListAdapter extends BaseAdapter {
    private Cart context;
    private static LayoutInflater inflater = null;
    private String[] cartProducts;
    private Singleton var = Singleton.getInstance();

    private String price = null;
    private int tempPrice = 0;

    public CartCardListAdapter(Cart cart, String[] key) {
        context = cart;
        inflater = (LayoutInflater) context.getActivity().getSystemService(context.getActivity().LAYOUT_INFLATER_SERVICE);
        cartProducts = key;
    }

    @Override
    public int getCount() {
        return cartProducts.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class CartHolder {
        private TextView cartProductName;
        private TextView cartProductPrice;
        private Button delete;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int position = i;
        View categoryView;
        CartHolder cartHolder = new CartHolder();
        price = String.valueOf(var.cartPrice.get(cartProducts[i]) * var.carthash.get(cartProducts[i]));
        tempPrice = var.cartPrice.get(cartProducts[i]) * var.carthash.get(cartProducts[i]);
        var.totalPrice = tempPrice + var.totalPrice;
        //Inflating the view
        categoryView = inflater.inflate(R.layout.cart_card_list, null);
        cartHolder.cartProductName = (TextView) categoryView.findViewById(R.id.cart_product_name);
        cartHolder.cartProductPrice = (TextView) categoryView.findViewById(R.id.cart_product_price);
        cartHolder.delete = (Button) categoryView.findViewById(R.id.cart_product_delete);

        cartHolder.cartProductName.setText(cartProducts[i]);
        cartHolder.cartProductPrice.setText("Price: " + price);



        cartHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                var.carthash.remove(cartProducts[position]);
                Fragment cart = new Cart();
                // Creting the bundle to send the clicked category to the nect fragment
                //This will set the bundle as an argument to the object
                // Transaction from current activity to next activity

                if (var.carthash.isEmpty()){
                    var.variable = false;

                }
                android.app.FragmentManager fragmentManager = context.getActivity().getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main, cart).commit();

            }
        });
        return categoryView;

    }
}
