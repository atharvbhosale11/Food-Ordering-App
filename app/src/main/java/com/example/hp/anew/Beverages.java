package com.example.hp.anew;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Beverages extends AppCompatActivity implements View.OnClickListener
{
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    List<Items> productList;
    ProductAdapter adapter;
    FloatingActionButton back2,cart;
    String name,img;
    int price;
    ProgressDialog progressDialog;
    private static final String TAG = MainActivity.class.getSimpleName();
    FirebaseDatabase database;
    DatabaseReference db;
    Items t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverages);


        productList=new ArrayList<Items>();
        recyclerView=(RecyclerView) findViewById(R.id.bevlist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database=FirebaseDatabase.getInstance();
        db=database.getReference("Items");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //productList.clear();
                for (DataSnapshot ds: dataSnapshot.child("beverages").getChildren())
                {
                    t=ds.getValue(Items.class);
                    Log.d(TAG, "Iam in cart "+t.getName());

                    productList.add(t);
                }
                adapter=new ProductAdapter(getApplicationContext(),productList);
                //recyclerview.setItemAnimator( new DefaultItemAnimator());
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                //recyclerView.setItemViewCacheSize(productList.size());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        back2=(FloatingActionButton) findViewById(R.id.back2);
        back2.setOnClickListener(this);

        cart=(FloatingActionButton) findViewById(R.id.cart);
        cart.setOnClickListener(this);
        /*productList.add(new product("Tea",10,R.drawable.tea));
        productList.add(new product("Coffee",20,R.drawable.coffee));
        productList.add(new product("Milk",10,R.drawable.milk));
        productList.add(new product("Juice",30,R.drawable.juice));
        productList.add(new product("Shake",30,R.drawable.shake));
        productList.add(new product("Mocktail",50,R.drawable.mock));*/
        //recyclerView.setAdapter(adapter);
        // productList.add(new timepass("Tea",10,"https://firebasestorage.googleapis.com/v0/b/myfirebase-5a494.appspot.com/o/tea.jpg?alt=media&token=092daa49-f6a2-4b4a-bea8-49993f67a763"));
        // adapter=new ProductAdapter(this,productList);
        //recyclerView.setAdapter(adapter);
    }
    @Override
    public void onClick(View v) {
        if(v==back2)
        {
            startActivity(new Intent(this,ProfileActivity.class));
        }

        if(v==cart)
        {
            startActivity(new Intent(this,Cart.class));
        }

    }
}
