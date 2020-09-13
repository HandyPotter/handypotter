package com.unity3d.player.Frag3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.unity3d.player.R;

import java.util.List;

public class SearchListAdapter extends BaseAdapter {

    public String word;

    private Context context;
    private List<SignLanguage> signLanguageList;
    private List<SignLanguage> signLanguageForSearchList;

    public SearchListAdapter(Context context, List<SignLanguage> signLanguageList, List<SignLanguage> signLanguageForSearchList){
        this.context = context;
        this.signLanguageList = signLanguageList;
        this.signLanguageForSearchList = signLanguageForSearchList;
    }

    @Override
    public int getCount() {
        return signLanguageForSearchList.size();
    }

    @Override
    public Object getItem(int i) {
        word = signLanguageForSearchList.get(i).SL_WORD;
        return signLanguageForSearchList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View v, ViewGroup parent) {

        if(v == null){
            v = View.inflate(context, R.layout.search, null);
        }

        TextView SL_ID = (TextView)v.findViewById(R.id.SL_ID);
        TextView SL_WORD = (TextView)v.findViewById(R.id.SL_WORD);
        TextView SL_Theme = (TextView)v.findViewById(R.id.SL_Theme);

        SL_ID.setText(signLanguageForSearchList.get(i).getSL_ID());
        SL_WORD.setText(signLanguageForSearchList.get(i).getSL_WORD());
        SL_Theme.setText(signLanguageForSearchList.get(i).getSL_Theme());

        v.setTag(signLanguageForSearchList.get(i).getSL_ID());

        return v;
    }


    public void searchDict(String search){
        signLanguageForSearchList.clear();
        if(search.length()==0){
            signLanguageForSearchList.addAll(signLanguageList);
        }else{
            for(int i=0; i<signLanguageList.size(); i++){
                if(signLanguageList.get(i).getSL_WORD().contains(search)){
                    signLanguageForSearchList.add(signLanguageList.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }

}
