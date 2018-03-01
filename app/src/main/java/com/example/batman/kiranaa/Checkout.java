package com.example.batman.kiranaa;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class Checkout extends Fragment {

    private EditText userName;
    private EditText userAddress;
    private EditText userCountry;
    private EditText userZipCode;
    private EditText userCardNumber;
    private EditText userCvvNumber;
    private EditText userCardExpDate;
    private Button checkOutButton;
    Singleton var = Singleton.getInstance();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (var.variable == false) {
            Toast.makeText(getActivity(), "No item selected", Toast.LENGTH_SHORT).show();
            return null;
        } else {

        final View checkOutInflate = inflater.inflate(R.layout.fragment_checkout, container, false);
        // Inflate the layout for this fragment

        userName = (EditText) checkOutInflate.findViewById(R.id.userName);
        userAddress = (EditText) checkOutInflate.findViewById(R.id.userAddress);
        userCountry = (EditText) checkOutInflate.findViewById(R.id.userCountry);
        userZipCode = (EditText) checkOutInflate.findViewById(R.id.userZipCode);
        userCardNumber = (EditText) checkOutInflate.findViewById(R.id.userCardNumber);
        userCvvNumber = (EditText) checkOutInflate.findViewById(R.id.userCvvNumber);
        userCardExpDate = (EditText) checkOutInflate.findViewById(R.id.userCardExpDate);
        checkOutButton = (Button) checkOutInflate.findViewById(R.id.checkOut);


        checkOutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String name = userName.getText().toString().trim();
                final String address = userAddress.getText().toString().trim();
                final String country = userCountry.getText().toString().trim();
                final String zipcode = userZipCode.getText().toString().trim();
                final String cardnumber = userCardNumber.getText().toString().trim();
                final String cvvnumber = userCvvNumber.getText().toString().trim();
                final String cardexpdate = userCardExpDate.getText().toString().trim();
                if (name.isEmpty() || address.isEmpty() || country.isEmpty() || zipcode.isEmpty() || cardnumber.isEmpty() || cvvnumber.isEmpty() || cardexpdate.isEmpty()) {
                    Toast.makeText(getActivity(), "Values can not be left empty", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Thank You for choosing Kiranaa", Toast.LENGTH_SHORT).show();

                }

            }
        });


        return checkOutInflate;
    }
}

}
