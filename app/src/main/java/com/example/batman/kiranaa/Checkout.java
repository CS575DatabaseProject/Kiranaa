package com.example.batman.kiranaa;

import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Checkout extends Fragment {

    private EditText userName;
    private EditText userAddress;
    private EditText userCountry;
    private EditText userZipCode;
    private EditText userCardNumber;
    private EditText userCvvNumber;
    private EditText userCardExpDate;
    private Button checkOutButton;
    private DatabaseReference databaseReference, databaseChildRefrence;
    private Map<String,SaveUserInfo> userInfo = new HashMap<>();
    Singleton var = Singleton.getInstance();
    static String recordStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


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
//                    Toast.makeText(getContext(), "Thank You for choosing Kiranaa", Toast.LENGTH_SHORT).show();

                    databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://kiranaa-575.firebaseio.com/UserInfo/");
                    databaseChildRefrence = FirebaseDatabase.getInstance().getReferenceFromUrl("https://kiranaa-575.firebaseio.com/UserInfo/"+name+"/");
                   final HashMap<String, Integer> user_order = new HashMap<>(var.carthash);
                    final SaveUserInfo saveUserInfo = new SaveUserInfo(address,country,zipcode,cardnumber,cardexpdate,cvvnumber);
                    userInfo.put(name, saveUserInfo );

                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(recordStatus==null || recordStatus =="") {
                                Log.v("data changed", "data changed");
                                boolean Duplicate = false;

                                for (DataSnapshot item : dataSnapshot.getChildren()) {
                                    Log.v("name", "" + name);
                                    Log.v("item", "" + item.getKey().toString());
                                    Log.v("checking", "" + item.getKey().toString().equals(name));

                                    if (item.getKey().toString().equals(name)) {
                                        Log.v("in if", "in if");
                                        Log.v("cart", "cart:" + user_order);
                                        databaseChildRefrence.child("user_order").push().setValue(user_order);
                                        Duplicate = true;
                                        recordStatus = "updated";
                                        break;
                                    }

                                }

                                if (!Duplicate) {
                                    recordStatus = "added";
                                    DatabaseReference dref= databaseReference.child(name);
                                    dref.setValue(saveUserInfo);
                                    databaseChildRefrence.child("user_order").push().setValue(user_order);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }

                    });



                    recordStatus=null;
                    var.carthash.clear();
                    var.cartPrice.clear();
                    var.variable = false;


                    Fragment thankyou = new ThankYouFragment();
                    FragmentManager fragmentManager = getActivity().getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_checkout, thankyou).commit();



                }

            }
        });


        return checkOutInflate;
    }
}

}
