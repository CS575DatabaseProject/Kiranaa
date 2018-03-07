package com.example.batman.kiranaa;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


public class OrderHistoryCardListAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    OrderHistory context;
    OrderReader[]  newOrderReader;
    Singleton var = Singleton.getInstance();
    int listLength=0;


    public OrderHistoryCardListAdapter(OrderHistory  orderHistory, OrderReader[] orderReaders){
        context = orderHistory;
        inflater = (LayoutInflater) context.getActivity().getSystemService(context.getActivity().LAYOUT_INFLATER_SERVICE);
        newOrderReader = orderReaders;
        //listLength=length;

    }
    @Override
    public int getCount() {
        return newOrderReader.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    private class OrderListHolder{
        TextView orderName, orderDate, orderValue, orderPrice;
        ImageView imageView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        OrderListHolder orderListHolder = new OrderListHolder();
        Log.v("I am at the card class","meesage");
        Log.v("orderReader array count","is :"+i);
        View orderHistoryView = inflater.inflate(R.layout.order_history_card, null);
        orderListHolder.orderName = orderHistoryView.findViewById(R.id.order_name);
        orderListHolder.orderDate = orderHistoryView.findViewById(R.id.order_date);
        orderListHolder.orderValue = orderHistoryView.findViewById(R.id.order_value);
        orderListHolder.orderPrice = orderHistoryView.findViewById(R.id.order_price);
        orderListHolder.imageView = orderHistoryView.findViewById(R.id.order_product_image);


            orderListHolder.orderName.setText(newOrderReader[i].Product);
            orderListHolder.orderDate.setText(newOrderReader[i].OrderDate);
            orderListHolder.orderValue.setText(newOrderReader[i].Count);
            orderListHolder.orderPrice.setText(newOrderReader[i].Amount);
            Log.v("url is",""+var.ProductMap.get(newOrderReader[i].Product));
            Glide.with(context).load(var.ProductMap.get(newOrderReader[i].Product)).into(orderListHolder.imageView);

        return orderHistoryView;
    }
}
