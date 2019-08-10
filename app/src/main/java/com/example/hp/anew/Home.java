package com.example.hp.anew;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Home extends Fragment implements View.OnClickListener {

    private Button buttonDes;
    private Button buttonBev;
    private Button buttonSna,buttonmain,buttonstart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home, container, false);


        buttonDes=(Button) rootView.findViewById(R.id.buttonDes);
        buttonBev=(Button) rootView.findViewById(R.id.buttonBev);
        buttonSna=(Button) rootView.findViewById(R.id.buttonsna);
        buttonstart=(Button) rootView.findViewById(R.id.buttonstart);
        buttonmain=(Button) rootView.findViewById(R.id.buttonmain);

        buttonBev.setOnClickListener(this);
        buttonDes.setOnClickListener(this);
        buttonSna.setOnClickListener(this);
        buttonstart.setOnClickListener(this);
        buttonmain.setOnClickListener(this);

        return rootView;
    }
    @Override
    public void onClick(View v) {

        if(v==buttonstart)
        {
            //finish();
            Intent intent = new Intent(getActivity(), Star.class);
            startActivity(intent);
        }
        if(v==buttonmain)
        {
            startActivity(new Intent(getActivity(),MainCourse.class));
        }
        if(v==buttonDes) {
            //finish();
            startActivity(new Intent(getActivity(), Dessert.class));
        }
        if(v==buttonBev){
            //finish();
            startActivity(new Intent(getActivity(),Beverages.class));
        }
        if(v==buttonSna)
        {
            //finish();
            startActivity(new Intent(getActivity(),Snacks.class));
        }
    }
}
