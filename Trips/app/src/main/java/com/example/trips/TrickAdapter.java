package com.example.trips;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.trips.Models.Trick;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TrickAdapter extends RecyclerView.Adapter<TrickAdapter.MyViewHolder> implements Filterable {

    Context context;
    List<Trick> tricks;
    List<Trick> filteredTricks;
    TrickCustomClickListener listener;

    public TrickAdapter(Context context, List<Trick> tricks, TrickCustomClickListener listener) {
        this.context = context;
        this.tricks = tricks;
        this.listener = listener;
        this.filteredTricks = tricks;
    }

    @Override
    public TrickAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_view_trick_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrickAdapter.MyViewHolder myViewHolder, int position) {
        final Trick trick = filteredTricks.get(position);
        if(trick.isSubscribed()){
            myViewHolder.trickRow.setBackgroundColor(context.getResources().getColor(R.color.lightGrey));
            myViewHolder.subscribeButton.setText("Voir");
        }
        else{
            myViewHolder.trickRow.setBackgroundColor(context.getResources().getColor(R.color.White));
            myViewHolder.subscribeButton.setText("Suivre");
        }

        myViewHolder.rowTrickTitle.setText(trick.getName());
        myViewHolder.rowTrickCategoryName.setText(trick.getCategory().getName());
        myViewHolder.rowTrickDescription.setText(trick.getDescription());
        myViewHolder.rowRatingBar.setRating((float) trick.getMark());
        myViewHolder.rowTrickAuthor.setText(trick.getUser().getPseudo());
        myViewHolder.rowTrickCreationDate.setText(trick.getCreationDate());

        myViewHolder.subscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTrickItemClick(v, trick);
            }
        });
        myViewHolder.trickRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredTricks.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                CharSequence charBegin = charSequence.subSequence(0, 3);
                String charString = charSequence.toString();
                charString = charString.substring(3);

                if (charString.isEmpty()) {

                    filteredTricks = tricks;
                } else {

                    List<Trick> filteredList = new ArrayList<>();

                    for (Trick trick : tricks) {

                        if ( charBegin.equals("str") && (trick.getName().toLowerCase().contains(charString) || trick.getCategory().getName().toLowerCase().contains(charString) || trick.getUser().getPseudo().toLowerCase().contains(charString))) {

                            filteredList.add(trick);
                        }
                        else if(charBegin.equals("cat") && trick.getCategory().getName().toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(trick);
                        }
                    }

                    filteredTricks = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredTricks;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredTricks = (ArrayList<Trick>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void sortByName(boolean ascending) {
        if(ascending){
            Collections.sort(filteredTricks,  (Trick l1, Trick l2) -> l1.getName().compareTo(l2.getName()));
        }
        else {
            Collections.reverse(filteredTricks);
        }
        notifyDataSetChanged();
    }

    public void sortByDate(boolean ascending) {
        if(ascending){
            Collections.sort(filteredTricks,  (l1, l2) -> {
                Date firstDate = null;
                Date secondDate = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    firstDate =  dateFormat.parse(l1.getCreationDate());
                    secondDate =  dateFormat.parse(l2.getCreationDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(firstDate != null && secondDate != null){
                    if (firstDate.compareTo(secondDate) < 0){
                        return 1;
                    } else if (firstDate.compareTo(secondDate) > 0) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
                return 0;
            });
        }
        else{
            Collections.sort(filteredTricks,  (l1, l2) -> {
                Date firstDate = null;
                Date secondDate = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    firstDate =  dateFormat.parse(l1.getCreationDate());
                    secondDate =  dateFormat.parse(l2.getCreationDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(firstDate != null && secondDate != null){
                    if (firstDate.compareTo(secondDate) > 0){
                        return 1;
                    } else if (firstDate.compareTo(secondDate) < 0) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
                return 0;
            });
        }

        notifyDataSetChanged();
    }

    public void sortByMark(boolean ascending) {
        if(ascending) {
            Collections.sort(filteredTricks, (l1, l2) -> {
                if(Double.compare(l1.getMark(), l2.getMark()) < 0) {
                    return 1;
                } else if (Double.compare(l1.getMark(), l2.getMark()) > 0) {
                    return -1;
                } else {
                    return 0;
                }
            });
        }
        else {
            Collections.sort(filteredTricks, (l1, l2) -> {
                if(Double.compare(l1.getMark(), l2.getMark()) > 0) {
                    return 1;
                } else if (Double.compare(l1.getMark(), l2.getMark()) < 0) {
                    return -1;
                } else {
                    return 0;
                }
            });
        }

        notifyDataSetChanged();
    }

    class MyViewHolder extends ViewHolder {
        LinearLayout trickRow;
        TextView rowTrickTitle;
        TextView rowTrickCategoryName;
        TextView rowTrickDescription;
        TextView rowTrickAuthor;
        RatingBar rowRatingBar;
        Button subscribeButton;
        TextView rowTrickCreationDate;


        public MyViewHolder(View itemView) {
            super(itemView);
            trickRow = itemView.findViewById(R.id.trickRow);
            rowTrickTitle = itemView.findViewById(R.id.rowTrickTitle);
            rowTrickCategoryName = itemView.findViewById(R.id.rowTrickCategoryName);
            rowTrickDescription = itemView.findViewById(R.id.rowTrickDescription);
            rowRatingBar = itemView.findViewById(R.id.trickListRatingBar);
            rowTrickAuthor = itemView.findViewById(R.id.trickListAuthor);
            subscribeButton = itemView.findViewById(R.id.subscribeButton);
            rowTrickCreationDate = itemView.findViewById(R.id.rowTrickCreationDate);
        }
    }
}
