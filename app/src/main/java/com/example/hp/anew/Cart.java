package com.example.hp.anew;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cart extends AppCompatActivity implements View.OnClickListener {

    SwipeMenuListView listView;
    FirebaseDatabase database;
    DatabaseReference db,db1;
    ArrayList<caart> clist;
    MyAdapter adapter;
    FirebaseAuth firebaseAuth;
    Button proceed;
    caart c;
    caart c1;
    private int sum,update;
    public TextView total;
    private static final String TAG = MainActivity.class.getSimpleName();//?????
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        proceed=(Button) findViewById(R.id.proceed);
        //sum=0;
        total=(TextView) findViewById(R.id.total);
        listView=(SwipeMenuListView) findViewById(R.id.cartlist);
        clist=new ArrayList<caart>();
        final FirebaseUser user=firebaseAuth.getInstance().getCurrentUser();
        database=FirebaseDatabase.getInstance();
        db=database.getReference(user.getUid());

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clist.clear();
                sum=0;
                for (DataSnapshot ds: dataSnapshot.child("orders").getChildren())
                {
                    c=ds.getValue(caart.class);
                    sum=sum+(c.getPrice()*c.getQty());
                    clist.add(c);
                    total.setText(String.valueOf(sum));
                }

                adapter=new MyAdapter(getApplicationContext(),clist);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        proceed.setOnClickListener(this);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete_black_24dp);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {

                if(index==0)
                {
                    c1=clist.get(position);
                    db1= FirebaseDatabase.getInstance().getReference();
                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                    db1.child(user.getUid()).child("orders").child(c1.getName()).setValue(null);
                    int price=c1.getPrice();
                    int quan=c1.getQty();
                    int update=sum-(price*quan);
                    Log.d(TAG, "onMenuItemClick: clicked item " + price+" "+quan+" "+update+" "+sum);
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v==proceed)
        {
            startActivity(new Intent(this,User_Info.class));
        }
    }
}
