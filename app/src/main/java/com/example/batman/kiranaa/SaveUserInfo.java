package com.example.batman.kiranaa;


public class SaveUserInfo {
    public String uAddress, uZipCode, uCardNumber, uCvvNumber, uCardExp, uCountry;

    public SaveUserInfo(String userAdd,String userCountry ,String userZipCode,String userCardNum,String userCardExp,String userCardCvv) {
        uAddress = userAdd;
        uCountry = userCountry;
        uZipCode = userZipCode;
        uCardNumber = userCardNum;
        uCardExp = userCardExp;
        uCvvNumber = userCardCvv;
    }
}
