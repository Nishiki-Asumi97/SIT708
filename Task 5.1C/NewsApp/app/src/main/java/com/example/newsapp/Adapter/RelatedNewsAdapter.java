package com.example.newsapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.Model.News;

import java.util.List;

public class RelatedNewsAdapter extends RecyclerView.Adapter<RelatedNewsAdapter.ViewHolder> {

    private List<News> relatedList;

    public RelatedNewsAdapter(List<News> relatedList) {
        this.relatedList = relatedList;
    }

    @NonNull
    @Override
    public RelatedNewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_news_verticle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedNewsAdapter.ViewHolder holder, int position) {
        News news = relatedList.get(position);
        holder.title.setText(news.getTitle());
        holder.description.setText(news.getDescription());
        holder.imageView.setImageResource(news.getImageResId());
    }

    @Override
    public int getItemCount() {
        return relatedList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, description;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.relatedImage);
            title = itemView.findViewById(R.id.relatedTitle);
            description = itemView.findViewById(R.id.relatedDesc);
        }
    }
}
