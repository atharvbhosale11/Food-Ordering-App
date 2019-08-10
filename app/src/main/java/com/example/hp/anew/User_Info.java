package com.example.hp.anew;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User_Info extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_name,edit_number,edit_email,edit_add;
    private Button cod,card;
    private ProgressDialog progressDialog;
    private DatabaseReference db;
    private user u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__info);
        edit_name=(EditText) findViewById(R.id.edit_name);
        edit_number=(EditText) findViewById(R.id.edit_number);
        edit_email=(EditText) findViewById(R.id.edit_email);
        edit_add=(EditText) findViewById(R.id.edit_add);
        cod=(Button) findViewById(R.id.cod);
        card=(Button) findViewById(R.id.card);
        progressDialog=new ProgressDialog(this);

        cod.setOnClickListener(this);
        card.setOnClickListener(this);
    }

    void check1()
    {
        String email=edit_email.getText().toString().trim();
        String name=edit_name.getText().toString().trim();
        String number=edit_number.getText().toString().trim();
        String address=edit_add.getText().toString().trim();
        int n=edit_number.length();


        if(TextUtils.isEmpty(name))
        {
            //password is empty
            Toast.makeText(this,"please enter name",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email))
        {
            //email is empty
            Toast.makeText(this,"please enter the email",Toast.LENGTH_SHORT).show();
            //stop the function
            return;
        }
        if(TextUtils.isEmpty(number))
        {
            //password is empty
            Toast.makeText(this,"please enter phone",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(address))
        {
            Toast.makeText(this,"please enter address",Toast.LENGTH_SHORT).show();
            return;
        }
        if(n!=10)
        {
            Toast.makeText(this,"phone should be 10 digits",Toast.LENGTH_SHORT).show();
            return;
        }
        long phone=Long.valueOf(number);
        db= FirebaseDatabase.getInstance().getReference();
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        u=new user(name,email,address,phone);
        if(user != null)
        {

            db.child(user.getUid()).child("details").setValue(u);

        }
        progressDialog.setMessage("order is being placed...");
        progressDialog.show();
        Toast.makeText(this,"ORDER IS PLACED SUCCESSFULL",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,ProfileActivity.class));



    }

    void check()
    {
        String email=edit_email.getText().toString().trim();
        String name=edit_name.getText().toString().trim();
        String number=edit_number.getText().toString().trim();
        String address=edit_add.getText().toString().trim();
        int n=edit_number.length();

        if(TextUtils.isEmpty(name))
        {
            //password is empty
            Toast.makeText(this,"please enter name",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email))
        {
            //email is empty
            Toast.makeText(this,"please enter the email",Toast.LENGTH_SHORT).show();
            //stop the function
            return;
        }
        if(TextUtils.isEmpty(number))
        {
            //password is empty
            Toast.makeText(this,"please enter phone",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(address))
        {
            Toast.makeText(this,"please enter address",Toast.LENGTH_SHORT).show();
            return;
        }
        if(n!=10)
        {
            Toast.makeText(this,"phone should be 10 digits",Toast.LENGTH_SHORT).show();
            return;
        }

        long phone=Long.valueOf(number);
        db= FirebaseDatabase.getInstance().getReference();
        FirebaseUser currentUser= FirebaseAuth.getInstance().getCurrentUser();
        u=new user(name,email,address,phone);
        if(currentUser != null)
        {

            db.child(currentUser.getUid()).child("details").setValue(u);

        }
        startActivity(new Intent(this,Payment.class));
    }
    @Override
    public void onClick(View v) {

        if(v==cod)
        {
            check1();

        }
        if(v==card)
        {
            check();
        }
    }
}
