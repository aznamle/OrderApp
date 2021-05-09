package com.projects.finalproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {


    List<Model> modelList;
    Context context;

    public OrderAdapter(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        //set outputs on views

        String nameofItem = modelList.get(position).getmItemName();
        int images = modelList.get(position).getmItemPhoto();

        holder.mItemName.setText(nameofItem);
        holder.imageView.setImageResource(images);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //find views on which to fill in data

        TextView mItemName;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mItemName = itemView.findViewById(R.id.itemNameInfo);
            imageView = itemView.findViewById(R.id.itemImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if(position == 0) {
                //pass which selected
                Intent intent = new Intent(context, InfoActivity.class);
                context.startActivity(intent);
            }

            if(position == 1) {
                Intent intent = new Intent(context, Item2Activity.class);
                context.startActivity(intent);
            }
        }
    }
}
