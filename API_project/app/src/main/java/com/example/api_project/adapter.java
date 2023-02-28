package com.example.api_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {

    ArrayList<DATA> list;
    public static OntiemClick onitemClick;
    Context context;

    ////Create Constructor
    public adapter(ArrayList<DATA> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.recyclerview_item_design,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DATA data=list.get(position);
        holder.name.setText(data.getName());
        holder.color.setText(data.getColor());
        holder.price.setText(data.getPrice());
        holder.size.setText(data.getTyps());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }


    ////Create a class and implements OnclickListener Interface--------------------->
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,color,price,size;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            color=itemView.findViewById(R.id.color);
            price=itemView.findViewById(R.id.price);
            size=itemView.findViewById(R.id.size);
        }
        @Override
        public void onClick(View view) {

        }
    }

    /////Create a interface-------------------------------------->

    public interface OntiemClick{
        void onclick(View view,int position);
    }
    public void OnitemClickListener(OntiemClick ontiemClick)
    {
        adapter.onitemClick=ontiemClick;
    }
}