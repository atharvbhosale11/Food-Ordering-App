package com.example.hp.anew;

import android.app.Fragment;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity
{
    private FirebaseAuth firebaseAuth;

    private DrawerLayout mDrawerLayout;
    private NavigationView nv;
    private DatabaseReference db;
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        boolean mSlideState = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Home()).commit();
        firebaseAuth= FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null)
        {
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
        FirebaseUser user=firebaseAuth.getCurrentUser();

        //login_activity l=new login_activity();

        mDrawerLayout = findViewById(R.id.drawerlayout);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        nv = (NavigationView)findViewById(R.id.nav_view);
        nv.setCheckedItem(R.id.nav_home);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            public boolean onNavigationItemSelected( MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {

                    case R.id.nav_home:
                        mDrawerLayout.closeDrawer(Gravity.START, false);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Home()).commit();

                        break;

                    case R.id.nav_help:
                        Log.d(TAG, "Iam in switch ");
                        mDrawerLayout.closeDrawer(Gravity.START, false);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Help()
                        ).commit();
                        break;
                    case R.id.nav_contact:
                        mDrawerLayout.closeDrawer(Gravity.START, false);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Contact()).commit();
                        break;
                    case R.id.nav_log:
                        db= FirebaseDatabase.getInstance().getReference();
                        FirebaseUser user=firebaseAuth.getInstance().getCurrentUser();
                        db.child(user.getUid()).child("orders").setValue(null);
                        firebaseAuth.getInstance().signOut();
                        finish();
                        Toast.makeText(ProfileActivity.this,"Logged Out Successfully", Toast.LENGTH_LONG);
                        startActivity(new Intent(ProfileActivity.this,MainActivity.class));
                        break;
                    case R.id.nav_ord:
                        mDrawerLayout.closeDrawer(Gravity.START, false);
                        Intent in = new Intent(ProfileActivity.this, orders.class);
                        startActivity(in);
                        break;
                    case R.id.rate:
                        mDrawerLayout.closeDrawer(Gravity.START, false);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Rate()).commit();
                        break;
                    default:
                        return true;
                }
                return true;
            }}
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                mDrawerLayout.openDrawer(GravityCompat.START);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        Toast.makeText(ProfileActivity.this,"LOGOUT TO GO BACK",Toast.LENGTH_SHORT).show();
        return;
    }

}
