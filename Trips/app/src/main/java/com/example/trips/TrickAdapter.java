package com.example.trips;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.trips.Models.Trick;
import java.util.ArrayList;
import java.util.List;

public class TrickAdapter extends RecyclerView.Adapter<TrickAdapter.MyViewHolder> {

    Context context;
    List<Trick> tricks;
    TrickCustomClickListener listener;

    public TrickAdapter(Context context, ArrayList<Trick> tricks, TrickCustomClickListener listener) {
        this.context = context;
        this.tricks = tricks;
        this.listener = listener;
    }

    @Override
    public TrickAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_view_trick_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrickAdapter.MyViewHolder myViewHolder, int position) {
        final Trick trick = tricks.get(position);
        myViewHolder.rowTrickTitle.setText(trick.getName());
        myViewHolder.rowTrickCategoryName.setText("Ma catégorie");
        myViewHolder.rowTrickDescription.setText(trick.getDescription());
        myViewHolder.trickRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTrickItemClick(v, trick);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tricks.size();
    }

    class MyViewHolder extends ViewHolder {
        LinearLayout trickRow;
        TextView rowTrickTitle;
        TextView rowTrickCategoryName;
        TextView rowTrickDescription;

        public MyViewHolder(View itemView) {
            super(itemView);
            trickRow = itemView.findViewById(R.id.trickRow);
            rowTrickTitle = itemView.findViewById(R.id.rowTrickTitle);
            rowTrickCategoryName = itemView.findViewById(R.id.rowTrickCategoryName);
            rowTrickDescription = itemView.findViewById(R.id.rowTrickDescription);

        }
    }
}
