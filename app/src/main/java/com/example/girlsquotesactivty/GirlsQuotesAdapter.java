package com.example.girlsquotesactivty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.GeneratedAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GirlsQuotesAdapter extends RecyclerView.Adapter<GirlsQuotesAdapter.MyViewHolder> {
    Context context;
    private ArrayList<String> category;

    GirlsQuotesInterface girlsQuotesInterface;

    public GirlsQuotesAdapter(Context context, ArrayList<String> category, GirlsQuotesInterface girlsQuotesInterface) {
        this.category = category;
        this.context = context;
        this.girlsQuotesInterface = girlsQuotesInterface;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.girls_quotes_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtname.setText(category.get(position));

        holder.txtname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girlsQuotesInterface.onListClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtname;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.txtname);
        }
    }
}
