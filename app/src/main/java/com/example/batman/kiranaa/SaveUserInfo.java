package com.example.batman.kiranaa;


import java.util.HashMap;

public class SaveUserInfo {
    public String user_address, user_zip_code, user_card_number, user_cvv_number, user_card_expiry, user_country;
    public HashMap<String,Integer> user_order = new HashMap<>();

    public SaveUserInfo(String userAdd,String userCountry ,String userZipCode,String userCardNum,String userCardExp,String userCardCvv) {
        user_address = userAdd;
        user_country = userCountry;
        user_zip_code = userZipCode;
        user_card_number = userCardNum;
        user_card_expiry = userCardExp;
        user_cvv_number = userCardCvv;
    }
}
