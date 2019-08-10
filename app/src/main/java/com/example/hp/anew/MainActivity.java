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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    ViewFlipper fp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int image[]={R.drawable.snacks,R.drawable.bhel,R.drawable.garlic,R.drawable.hara};
        fp=(ViewFlipper)findViewById(R.id.viewflipper);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser use=firebaseAuth.getInstance().getCurrentUser();
        if(use!=null)
        {
            startActivity(new Intent(this,ProfileActivity.class));
        }
        progressDialog=new ProgressDialog(this);
        buttonRegister=(Button) findViewById(R.id.ButtonRegister);
        editTextEmail=(EditText) findViewById(R.id.editTextEmail);
        editTextPassword=(EditText) findViewById(R.id.editTextPassword);
        textViewSignin=(TextView) findViewById(R.id.textviewSignin);
        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
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
    private void registeruser()
    {
        String email=editTextEmail.getText().toString().trim();
        String pass=editTextPassword.getText().toString().trim();

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
        progressDialog.setMessage("registering user...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        }
                        else
                        {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this,"Failed to Register",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onBackPressed() {

        return;
    }
    @Override
    public void onClick(View view)
    {
        if(view==buttonRegister)
        {
            registeruser();
        }
        if (view==textViewSignin)
        {
            //will open login activity
            startActivity(new Intent(this,login_activity.class));
        }
    }
}
