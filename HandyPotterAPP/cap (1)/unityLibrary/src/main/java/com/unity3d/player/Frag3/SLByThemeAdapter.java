package com.unity3d.player.Frag3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.unity3d.player.R;

import java.util.ArrayList;

public class SLByThemeAdapter extends BaseAdapter {

    Context context = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<SignLanguage> slData;

    public SLByThemeAdapter(Context context, ArrayList<SignLanguage> data){
        context = context;
        slData = data;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return slData.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public SignLanguage getItem(int position) {
        return slData.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.item_themelist, null);

        TextView slID = (TextView)view.findViewById(R.id.SL_ID);
        TextView slWORD = (TextView)view.findViewById(R.id.SL_WORD);

        slID.setText(slData.get(position).getSL_ID());
        slWORD.setText(slData.get(position).getSL_WORD());

        return view;
    }
}
