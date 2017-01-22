package com.example.queenabergen.memestudio;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by queenabergen on meme22/12/17.
 */

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ButtonHolder> {
    private ImageView imageView;
    private ButtonAdapter bAdapter;
    private Context mContext;
    private RecyclerView.LayoutManager layoutManager;

    private int[] memeChooserOptions = {
            R.drawable.classmeme, R.drawable.drawonmeme, R.drawable.borderedmeme
    };


    public class ButtonHolder extends RecyclerView.ViewHolder {
        public ImageView button_photo;


        public ButtonHolder(final View itemView) {
            super(itemView);
            button_photo = (ImageView) itemView.findViewById(R.id.button_reel_photo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    switch (position) {
                        case 0:
                            Intent intent = new Intent(v.getContext(), PaintOnImageActivity.class);
                            v.getContext().startActivity(intent);
                        case 1:
                            Intent intent1 = new Intent();

                        case 2:
                            Intent intent2 = new Intent();
                            break;
                    }
                }
            });
        }
    }


    @Override
    public ButtonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.buttonreel, parent, false);
        return new ButtonHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ButtonHolder holder, int i) {
        while(i<2){
            Picasso.with(mContext).load(memeChooserOptions[i]).resize(550, 550).into(holder.button_photo);
        }

    }

    @Override
    public int getItemCount() {
        return memeChooserOptions.length;
    }

}
