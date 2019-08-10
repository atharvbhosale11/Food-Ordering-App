package com.example.hp.anew;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class orders extends AppCompatActivity  {

    TextView name,add,mobile,email,total;
    ListView order;
    FirebaseDatabase database;
    ImageView home;
    DatabaseReference db;
    ArrayList<caart> clist;
    MyAdapter adapt;
    FirebaseAuth firebaseAuth;
    boolean ord=true;
    public static final String TAG = "YOUR-TAG-NAME";
    int sum;
    user u;
    caart c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        name=(TextView) findViewById(R.id.name);
        add=(TextView) findViewById(R.id.add);
        mobile=(TextView) findViewById(R.id.mobile);
        email=(TextView) findViewById(R.id.email);
        order=(ListView) findViewById(R.id.cartlist);
        total=(TextView) findViewById(R.id.total);
        home=(ImageView) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"RECEIPT in Account Section",Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
            }
        });

        clist=new ArrayList<caart>();
        final FirebaseUser use=firebaseAuth.getInstance().getCurrentUser();
        database=FirebaseDatabase.getInstance();
        db=database.getReference(use.getUid());

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clist.clear();
                sum=0;


                if(dataSnapshot.hasChild("details")==false)
                {
                    Log.d(TAG, "Iam in null ");
                }
                else
                {
                    u=dataSnapshot.child("details").getValue(user.class);
                    name.setText(u.full_nam);
                    email.setText(u.user_email);
                    mobile.setText(String.valueOf(u.phone));
                    add.setText(u.address);
                }
                if(dataSnapshot.hasChild("orders")==false)
                {
                    Log.d(TAG, "Iam in null ");
                    total.setText("0");
                }
                else
                {
                    for (DataSnapshot ds: dataSnapshot.child("orders").getChildren())
                    {
                        c=ds.getValue(caart.class);
                        sum=sum+(c.getPrice()*c.getQty());
                        clist.add(c);
                        total.setText(String.valueOf(sum));
                    }
                    adapt=new MyAdapter(getApplicationContext(),clist);
                    order.setAdapter(adapt);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        if(ord==false)
        {
            Toast.makeText(orders.this,"ORDER NOT PLACED,PLACE ORDER FIRST",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(orders.this,ProfileActivity.class));
        }
    }
}
