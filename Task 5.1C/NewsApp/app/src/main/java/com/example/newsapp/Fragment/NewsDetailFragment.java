package com.example.newsapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.R;
import com.example.newsapp.Model.News;
import com.example.newsapp.Adapter.RelatedNewsAdapter;

import java.util.Arrays;

public class NewsDetailFragment extends Fragment {

    private static final String ARG_NEWS = "selected_news";

    public static NewsDetailFragment newInstance(News news) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_NEWS, news);
        fragment.setArguments(args);
        return fragment;
    }

    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_fragment_news_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        News news = (News) getArguments().getSerializable(ARG_NEWS);
        if (news == null) return;

        ImageView imageView = view.findViewById(R.id.imageViewNews);
        TextView titleView = view.findViewById(R.id.textViewTitle);
        TextView descView = view.findViewById(R.id.textViewDescription);
        RecyclerView relatedRecycler = view.findViewById(R.id.recyclerRelated);

        imageView.setImageResource(news.getImageResId());
        titleView.setText(news.getTitle());
        descView.setText(news.getDescription());

        relatedRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        relatedRecycler.setAdapter(new RelatedNewsAdapter(Arrays.asList(
                new News("Related 1", R.drawable.img1, "Some text"),
                new News("Related 2", R.drawable.img2, "Another one")
        )));
    }
}
