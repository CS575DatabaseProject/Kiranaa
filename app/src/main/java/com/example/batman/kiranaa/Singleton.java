package com.example.batman.kiranaa;

import java.util.HashMap;

public class Singleton {
    private static final Singleton instance = new Singleton();
    HashMap<String,Integer> carthash = new HashMap<String, Integer>();
    private Singleton(){}
    public static Singleton getInstance(){
        return instance;
    }
}
