package com.unity3d.player;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class Frag2 extends Fragment {

    TextView textView_02;
    View view;
    String subtitle;

    public Frag2() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag2,container,false );
        textView_02 = view.findViewById(R.id.textView_02);

        textView_02.setText("");


      /*  if(subtitle != null){
            textView_02.setText(subtitle);
        }*/

        if(getArguments() != null)

        {
            subtitle = getArguments().getString("sub");
            textView_02.setText(subtitle);

        }


        return view;
    }

}
