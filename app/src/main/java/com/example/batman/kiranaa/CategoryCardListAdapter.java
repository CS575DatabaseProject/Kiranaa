package com.example.batman.kiranaa;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

/**
 * Created by tarun on 2/14/2018.
 */

public class CategoryCardListAdapter extends BaseAdapter {
    Intent intent;
    Context context;
    ArrayList<String> category = new ArrayList<String>();
    private static LayoutInflater inflater=null;

    public CategoryCardListAdapter (MainActivity mainActivity, ArrayList<String> categoryList){
        context = mainActivity;
        category = categoryList;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return category.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class Holder{
        TextView textView ;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int position = i;
        Holder holder=new Holder();
        View categoryView;
        categoryView = inflater.inflate(R.layout.category_card_list, null);
        holder.textView = (TextView) categoryView.findViewById(R.id.category_text);
        holder.textView.setText(category.get(i));
        categoryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Toast.makeText(context,category.get(position), Toast.LENGTH_SHORT).show();*/
                MainActivity.onClickListnerFunction(position);
            }
        });
        return categoryView;
    }


}
