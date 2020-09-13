package com.unity3d.player.Frag3;



import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.unity3d.player.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Frag3 extends Fragment {

    private EditText searchField;
    private ImageButton searchBtn;
    private RecyclerView recyclerView;
    private ArrayList<ThemeItem> list = new ArrayList<>();
    List<ThemeItem> items=new ArrayList<>();
    LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment, container, false);

        recyclerView=(RecyclerView) view.findViewById(R.id.recycler_theme_list);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        searchField = (EditText) view.findViewById(R.id.search_field);
        searchBtn = (ImageButton) view.findViewById(R.id.searchBtn);

        searchField.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                getActivity().startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });


        initData();

        return view;
    }

    private void initData(){

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<ThemeItem> ThemeItem = new ArrayList<ThemeItem>();
        ThemeItem[] item = new ThemeItem[3];

        item[0]=new ThemeItem("일상생활", R.drawable.theme1);
        item[1]=new ThemeItem("전문용어", R.drawable.theme2);
        item[2]=new ThemeItem("문화생활", R.drawable.theme3);

        for(int i=0; i<3; i++){
            items.add(item[i]);
        }

        recyclerView.setAdapter(new ThemeViewAdapter(getActivity(), items, R.layout.item_cardview));

    }

}