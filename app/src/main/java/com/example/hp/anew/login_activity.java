package com.example.hp.anew;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_activity extends AppCompatActivity implements View.OnClickListener {


    private Button buttonsignin;
    private EditText editTextemail;
    private EditText editTextpassword;
    private TextView textViewsignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    public String email;
    ViewFlipper fp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);


        firebaseAuth=FirebaseAuth.getInstance();
        int image[]={R.drawable.chilli,R.drawable.sand,R.drawable.mock,R.drawable.panner};
        progressDialog=new ProgressDialog(this);
        buttonsignin=(Button) findViewById(R.id.ButtonSignin);
        editTextemail=(EditText) findViewById(R.id.editTextEmail);
        editTextpassword=(EditText) findViewById(R.id.editTextPassword);
        textViewsignup=(TextView) findViewById(R.id.textviewSignup);
        fp=(ViewFlipper)findViewById(R.id.viewflipper);
        buttonsignin.setOnClickListener(this);
        textViewsignup.setOnClickListener(this);
        for(int images:image)
        {
            flipper(images);
        }
    }

    public void flipper(int image)
    {
        ImageView img=new ImageView(this);
        img.setBackgroundResource(image);
        fp.addView(img);
        fp.setAutoStart(true);
        fp.setFlipInterval(2500);
        fp.setOutAnimation(this,android.R.anim.slide_in_left);
        fp.setOutAnimation(this,android.R.anim.slide_out_right);
    }
    private void userLogin()
    {

        email=editTextemail.getText().toString().trim();
        String pass=editTextpassword.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            //email is empty
            Toast.makeText(this,"please enter the email",Toast.LENGTH_SHORT).show();
            //stop the function
            return;
        }
        if(TextUtils.isEmpty(pass))
        {
            //password is empty
            Toast.makeText(this,"please enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        //if validations are fullfilled
        progressDialog.setMessage("signing in please wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful())
                        {
                            // start profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));

                        }
                        else
                        {
                            progressDialog.hide();
                            Toast.makeText(login_activity.this,"Failed to Sign In",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onClick(View v)
    {
        if(v==buttonsignin)
        {
            //sign in user
            userLogin();
        }
        if(v==textViewsignup)
        {
            //go to register
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
