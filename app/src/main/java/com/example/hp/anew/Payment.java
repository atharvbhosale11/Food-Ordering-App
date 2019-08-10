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

public class Payment extends AppCompatActivity implements View.OnClickListener {

    private Button pay;
    private EditText number;
    private EditText month,year,cvv,name;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        pay=(Button) findViewById(R.id.pay);
        number=(EditText) findViewById(R.id.edit_text_card_number);
        month=(EditText) findViewById(R.id.edit_text_expiry_month);
        year=(EditText) findViewById(R.id.edit_text_expiry_year);
        cvv=(EditText) findViewById(R.id.edit_text_card_cvv);
        name=(EditText) findViewById(R.id.edit_text_name_on_card);
        progressDialog=new ProgressDialog(this);
        pay.setOnClickListener(this);
    }

    void check()
    {
        String num=number.getText().toString().trim();
        String mon=month.getText().toString().trim();
        String y=year.getText().toString().trim();
        String nam=name.getText().toString().trim();
        String cv=cvv.getText().toString().trim();
        int cvv_len=cvv.length();
        int card_len=number.length();
        int mon_len=month.length();
        int y_len=year.length();
        if(TextUtils.isEmpty(num))
        {
            //password is empty
            Toast.makeText(this,"please enter card number",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(mon))
        {
            //password is empty
            Toast.makeText(this,"please enter month expiry",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(y))
        {
            //password is empty
            Toast.makeText(this,"please enter year expiry",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(nam))
        {
            //password is empty
            Toast.makeText(this,"please enter name on card",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(cv))
        {
            //password is empty
            Toast.makeText(this,"please enter CVV",Toast.LENGTH_SHORT).show();
            return;
        }
        if(card_len!=16)
        {
            Toast.makeText(this,"Card Number should be 16 digits",Toast.LENGTH_SHORT).show();
            return;
        }
        if(mon_len!=2)
        {
            Toast.makeText(this,"MM should be proper",Toast.LENGTH_SHORT).show();
            return;
        }
        if(y_len!=4)
        {
            Toast.makeText(this,"YYYY should be proper",Toast.LENGTH_SHORT).show();
            return;
        }
        if(cvv_len!=3)
        {
            Toast.makeText(this,"CVV should be 3 digits",Toast.LENGTH_SHORT).show();
            return;
        }
        int curr_year=Integer.parseInt(y);
        int curr_mon=Integer.parseInt(mon);
        if(curr_year<2018)
        {
            Toast.makeText(this,"Card has expired",Toast.LENGTH_SHORT).show();
            return;
        }
        if(curr_year==2018)
        {
            if(curr_mon<10)
            {
                Toast.makeText(this,"Card has expired",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        progressDialog.setMessage("order is being placed...");
        progressDialog.show();
        Toast.makeText(this,"ORDER IS PLACED SUCCESSFULL",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,ProfileActivity.class));

    }
    @Override
    public void onClick(View v) {
        if(v==pay)
        {
            check();

        }
    }
}
