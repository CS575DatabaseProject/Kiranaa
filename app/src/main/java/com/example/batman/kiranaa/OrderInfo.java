package com.example.batman.kiranaa;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by VUTSAL on 3/6/2018.
 */

public class OrderInfo {
    //public String Category;
    public String Product;
    public Integer Count;
    public Integer Amount;
    public String OrderDate;
    OrderInfo(String product,Integer count,Integer amount)
    {
        //this.Category=category;
        this.Product=product;
        this.Amount=amount;
        this.Count=count;

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        dateFormat.format(date);

        this.OrderDate=dateFormat.format(date).toString();
    }
}
