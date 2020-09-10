package com.etdvlpr.gads_2020practiceproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.etdvlpr.gads_2020practiceproject.R;
import com.etdvlpr.gads_2020practiceproject.model.Learner;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.LeaderBoardViewHolder> {
    private ArrayList<Learner> learners;
    private String scoreType;
    public LeaderBoardAdapter (ArrayList<Learner> learners, String scoreType) {
        this.learners = learners;
        this.scoreType = scoreType;
    }

    @NonNull
    @Override
    public LeaderBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MaterialCardView v = (MaterialCardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.single_card_item, parent, false);
        LeaderBoardViewHolder vh = new LeaderBoardViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderBoardViewHolder holder, int position) {
        holder.bind(learners.get(position));
    }

    @Override
    public int getItemCount() {
        return learners.size();
    }

    public class LeaderBoardViewHolder extends RecyclerView.ViewHolder{
        private ImageView card_image;
        private TextView name, details;
        public LeaderBoardViewHolder(@NonNull View itemView) {
            super(itemView);
            card_image = itemView.findViewById(R.id.card_item_image);
            name = itemView.findViewById(R.id.card_item_name);
            details = itemView.findViewById(R.id.card_item_details);
        }

        public void bind(Learner learner) {
            name.setText(learner.name);
            if(scoreType.equals("Skill IQ")) {
                details.setText(String.format("%d skill IQ Score, %s", learner.score, learner.country));
            } else {
                details.setText(String.format("%d learning hours, %s", learner.hours, learner.country));
            }
            Picasso.get().load(learner.badgeUrl).into(card_image);
        }
    }
}
