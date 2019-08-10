package com.example.hp.anew;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Rate extends Fragment {

    RatingBar ratingbar;
    Button button;
    Feedback f;
    FirebaseDatabase database;
    DatabaseReference db;
    FirebaseAuth firebaseAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.rate, container, false);


        button=(Button) rootView.findViewById(R.id.button);
        ratingbar=(RatingBar) rootView.findViewById(R.id.ratingBar);
        final FirebaseUser use=firebaseAuth.getInstance().getCurrentUser();
        database= FirebaseDatabase.getInstance();
        db=database.getReference(use.getUid());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating=String.valueOf(ratingbar.getRating());
                db.child("feedback").child("Ratings").setValue(rating);
                Toast.makeText(getContext(),"You rating "+rating+" is submitted", Toast.LENGTH_LONG).show();
            }
        });
        return rootView;
    }
}
