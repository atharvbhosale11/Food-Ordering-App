package com.example.hp.anew;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.makeText;

public class Help extends android.support.v4.app.Fragment {

    Button mButton;
    EditText editText;
    FirebaseDatabase database;
    DatabaseReference db;
    FirebaseAuth firebaseAuth;
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.help, container, false);


        mButton=(Button) rootView.findViewById(R.id.Button1);
        editText=(EditText) rootView.findViewById(R.id.editText1);
        final FirebaseUser use=firebaseAuth.getInstance().getCurrentUser();
        database= FirebaseDatabase.getInstance();
        db=database.getReference(use.getUid());
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=editText.getText().toString().trim();
                db.child("feedback").child("Query").setValue(s);
                editText.getText().clear();
                Toast.makeText(getContext(),"You feedback is submitted", Toast.LENGTH_LONG).show();
            }
        });
        return rootView;
    }

}
