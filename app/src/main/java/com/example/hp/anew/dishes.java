package com.example.hp.anew;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class dishes extends Fragment implements View.OnClickListener {

    TextView q1,q2,q3,q4;
    ImageButton dec1,dec2,dec3,dec4;
    ImageButton inc1,inc2,inc3,inc4;
    private DatabaseReference db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dishes, container, false);

        dec1=(ImageButton) rootView.findViewById(R.id.dec1);
        dec2=(ImageButton) rootView.findViewById(R.id.dec2);
        dec3=(ImageButton) rootView.findViewById(R.id.dec3);
        dec4=(ImageButton) rootView.findViewById(R.id.dec4);

        inc1=(ImageButton) rootView.findViewById(R.id.inc1);
        inc2=(ImageButton) rootView.findViewById(R.id.inc2);
        inc3=(ImageButton) rootView.findViewById(R.id.inc3);
        inc4=(ImageButton) rootView.findViewById(R.id.inc4);

        q1=(TextView) rootView.findViewById(R.id.quan1);
        q2=(TextView) rootView.findViewById(R.id.quan2);
        q3=(TextView) rootView.findViewById(R.id.quan3);
        q4=(TextView) rootView.findViewById(R.id.quan4);

        inc1.setOnClickListener(this);
        inc2.setOnClickListener(this);
        inc3.setOnClickListener(this);
        inc4.setOnClickListener(this);

        dec1.setOnClickListener(this);
        dec2.setOnClickListener(this);
        dec3.setOnClickListener(this);
        dec4.setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onClick(View v) {
        db= FirebaseDatabase.getInstance().getReference();
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        switch (v.getId())
        {
            case R.id.inc1:
                String p1 = q1.getText().toString();
                int k1 = Integer.parseInt(p1);
                k1++;
                q1.setText(String.valueOf(k1));
                caart c=new caart("Panner Masala",k1,80);
                if(user != null)
                {
                    if(k1==1)
                    {
                        db.child(user.getUid()).child("orders").child(c.name).setValue(c);
                    }
                    else
                    {
                        db.child(user.getUid()).child("orders").child(c.name).setValue(c);
                    }
                }
                break;
            case R.id.inc2:
                String p2 = q2.getText().toString();
                int k2 = Integer.parseInt(p2);
                k2++;
                q2.setText(String.valueOf(k2));
                caart c2=new caart("Malai Kofta",k2,90);
                if(user != null)
                {
                    if(k2==1)
                    {
                        db.child(user.getUid()).child("orders").child(c2.name).setValue(c2);
                    }
                    else
                    {
                        db.child(user.getUid()).child("orders").child(c2.name).setValue(c2);
                    }
                }
                break;
            case R.id.inc3:
                String p3 = q3.getText().toString();
                int k3 = Integer.parseInt(p3);
                k3++;
                q3.setText(String.valueOf(k3));
                caart c3=new caart("ShevBhaji",k3,70);
                if(user != null)
                {
                    if(k3==1)
                    {
                        db.child(user.getUid()).child("orders").child(c3.name).setValue(c3);
                    }
                    else
                    {
                        db.child(user.getUid()).child("orders").child(c3.name).setValue(c3);
                    }
                }
                break;
            case R.id.inc4:
                String p4 = q4.getText().toString();
                int k4 = Integer.parseInt(p4);
                k4++;
                q4.setText(String.valueOf(k4));
                caart c4=new caart("Bhendi Masala",k4,75);
                if(user != null)
                {
                    if(k4==1)
                    {
                        db.child(user.getUid()).child("orders").child(c4.name).setValue(c4);
                    }
                    else
                    {
                        db.child(user.getUid()).child("orders").child(c4.name).setValue(c4);
                    }
                }
                break;
            case R.id.dec1:
                String m1=q1.getText().toString();
                int l1=Integer.parseInt(m1);
                l1--;
                caart c5=new caart("Panner Masala",l1,80);
                if(l1<=0)
                {
                    q1.setText("0");
                    db.child(user.getUid()).child("orders").child(c5.name).setValue(null);
                }
                else {
                    q1.setText(String.valueOf(l1));
                    db.child(user.getUid()).child("orders").child(c5.name).setValue(c5);
                }
                break;
            case R.id.dec2:
                String m2=q2.getText().toString();
                int l2=Integer.parseInt(m2);
                l2--;
                caart c6=new caart("Malai Kofta",l2,90);
                if(l2<=0)
                {
                    q2.setText("0");
                    db.child(user.getUid()).child("orders").child(c6.name).setValue(null);
                }
                else {
                    q2.setText(String.valueOf(l2));
                    db.child(user.getUid()).child("orders").child(c6.name).setValue(c6);
                }
                break;
            case R.id.dec3:
                String m3=q3.getText().toString();
                int l3=Integer.parseInt(m3);
                l3--;
                caart c7=new caart("ShevBhaji",l3,70);
                if(l3<=0)
                {
                    q3.setText("0");
                    db.child(user.getUid()).child("orders").child(c7.name).setValue(null);
                }
                else {
                    q3.setText(String.valueOf(l3));
                    db.child(user.getUid()).child("orders").child(c7.name).setValue(c7);
                }
                break;
            case R.id.dec4:
                String m4=q4.getText().toString();
                int l4=Integer.parseInt(m4);
                l4--;
                caart c8=new caart("Bhendi Masala",l4,75);
                if(l4<=0)
                {
                    q4.setText("0");
                    db.child(user.getUid()).child("orders").child(c8.name).setValue(null);
                }
                else {
                    q4.setText(String.valueOf(l4));
                    db.child(user.getUid()).child("orders").child(c8.name).setValue(c8);
                }
                break;
        }
    }
}