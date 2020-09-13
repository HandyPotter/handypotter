package com.unity3d.player.Frag3;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import com.unity3d.player.R;

public class ThemeViewAdapter extends RecyclerView.Adapter<ThemeViewAdapter.Holder> {

    Context context;
    List<ThemeItem> items;
    int item_layout;

    public ThemeViewAdapter(Context context, List<ThemeItem> items, int item_layout) {
        this.context = context;
        this.items=items;
        this.item_layout=item_layout;
    }

    @NonNull
    @Override
    public ThemeViewAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
        Holder holder = new Holder(view);
        return holder;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull ThemeViewAdapter.Holder holder, int position) {

        final ThemeItem item=items.get(position);
        Drawable drawable=context.getResources().getDrawable(item.getImageURL());
        holder.thumbnail.setBackground(drawable);
        holder.title.setText(item.getTitle());

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,item.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, SLByThemeActivity.class);
                intent.putExtra("item", item.getTitle());
                context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return this.items.size(); // RecyclerView의 size return
    }

    public class Holder extends RecyclerView.ViewHolder{
        public TextView title;
        public ImageView thumbnail;
        public CardView cardview;


        public Holder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.theme_title);
            thumbnail = (ImageView) itemView.findViewById(R.id.theme_thumbnail);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
        }

    }
}
