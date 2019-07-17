package com.example.trips.Fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.trips.Models.Comment;
import com.example.trips.R;

import java.util.List;

public class PopupAdapter extends RecyclerView.Adapter<PopupAdapter.MyViewHolder> {

    Context context;
    List<Comment> comments;

    public PopupAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_popup_row, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        final Comment comment = comments.get(position);
        myViewHolder.commentUser.setText(comment.getUser().getPseudo());
        myViewHolder.commentContent.setText(comment.getName());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout commentRow;
        TextView commentUser, commentContent;


        public MyViewHolder(View itemView) {
            super(itemView);
            commentRow = itemView.findViewById(R.id.commentRow);
            commentUser = itemView.findViewById(R.id.commentUserName);
            commentContent = itemView.findViewById(R.id.commentContent);

        }
    }
}
