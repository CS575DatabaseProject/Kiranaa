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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Checkout.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Checkout#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Checkout extends Fragment {

    private EditText userName;
    private EditText userAddress;
    private EditText userCountry;
    private EditText userZipCode;
    private EditText userCardNumber;
    private EditText userCvvNumber;
    private EditText userCardExpDate;
    private Button CheckOutButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View checkOutInflate = inflater.inflate(R.layout.fragment_checkout, container, false);
        // Inflate the layout for this fragment
        userName = (EditText)checkOutInflate.findViewById(R.id.userName);
        userAddress = (EditText)checkOutInflate.findViewById(R.id.userAddress);
        userCountry = (EditText)checkOutInflate.findViewById(R.id.userCountry);
        userZipCode = (EditText)checkOutInflate.findViewById(R.id.userZipCode);
        userCardNumber = (EditText)checkOutInflate.findViewById(R.id.userCardNumber);
        userCvvNumber = (EditText)checkOutInflate.findViewById(R.id.userCvvNumber);
        userCardExpDate = (EditText)checkOutInflate.findViewById(R.id.userCardExpDate);







        return checkOutInflate;
    }


}
