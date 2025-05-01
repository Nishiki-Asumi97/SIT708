package com.example.newsapp.Adapter;

import android.content.Context;
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

public class HorizontalNewsAdapter extends RecyclerView.Adapter<HorizontalNewsAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(News news);
    }

    private List<News> newsList;
    private Context context;
    private OnItemClickListener listener;

    public HorizontalNewsAdapter(List<News> newsList, Context context, OnItemClickListener listener) {
        this.newsList = newsList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item_news_horizontal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.textViewTitle.setText(news.getTitle());
        holder.imageView.setImageResource(news.getImageResId());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(news));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.newsImage);
            textViewTitle = itemView.findViewById(R.id.newsTitle);
        }
    }
}
