package com.example.batman.kiranaa;

import java.util.ArrayList;
import java.util.HashMap;

public class Singleton {
    private static final Singleton instance = new Singleton();
    String Category=new String();
    HashMap<String,Integer> carthash = new HashMap<String, Integer>();
    HashMap<String,Integer> cartPrice = new HashMap<>();
    HashMap<String,String> categoryMap = new HashMap<>();
    HashMap<String,String> ProductMap = new HashMap<>();
    ArrayList<OrderInfo> orderList=new ArrayList<>();
    int totalPrice = 0;
    boolean variable = false;
    private Singleton(){}
    public static Singleton getInstance(){
        return instance;
    }
}

