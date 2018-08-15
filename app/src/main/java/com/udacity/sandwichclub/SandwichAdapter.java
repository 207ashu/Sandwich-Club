package com.udacity.sandwichclub;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;

import java.util.ArrayList;

public class SandwichAdapter extends RecyclerView.Adapter<SandwichAdapter.MyViewHolder> {

    public ArrayList<Sandwich> myValues;
    Context ctx;

    public SandwichAdapter (Context ctx, ArrayList<Sandwich> myValues){
        this.myValues= myValues;
        this.ctx=ctx;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new MyViewHolder(listItem);    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.cardName.setText(myValues.get(position).getMainName());
        Picasso.with(ctx).load(myValues.get(position).getImage()).into(holder.cardImg);
        holder.cardImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_POSITION, position);
                ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myValues.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView cardName;ImageView cardImg;
        public MyViewHolder(View itemView) {
            super(itemView);

            cardName=itemView.findViewById(R.id.card_view_text);
            cardImg=itemView.findViewById(R.id.card_view_img);




        }
    }
}
