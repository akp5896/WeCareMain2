package com.example.wecaremain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.ParseObject;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChennlAdapter extends RecyclerView.Adapter<ChennlAdapter.ViewHolder> {

    Context context;
    List<String> channels;

    public ChennlAdapter(Context context, List<String> stories) {
        this.context = context;
        this.channels = stories;
    }

    @NonNull
    @NotNull
    @Override
    public ChennlAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View posts = LayoutInflater.from(context).inflate(R.layout.item_channel,parent,false);
        return new ViewHolder(posts);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ChennlAdapter.ViewHolder holder, int position) {
        String match = channels.get(position);
        holder.bind(match);
    }

    @Override
    public int getItemCount() {
        return channels.size();
    }

    public void clear() {
        channels.clear();
    }

    public void addAll(List<String> p) {
        channels.addAll(p);
    }

    public void add(String s) {
        channels.add((s));
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvStory;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvStory = itemView.findViewById(R.id.tvChannel);

        }

        public void bind(String story) {

            tvStory.setText(story);
            tvStory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent((Activity)context, Comments.class);
                    i.putExtra("cname", story);
                    context.startActivity(i);
                }
            });
        }
    }
}
