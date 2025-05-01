package com.example.newsapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Adapter.RelatedNewsAdapter;
import com.example.newsapp.Model.News;
import com.example.newsapp.R;

import java.util.Arrays;

public class FragmentNewsDetail extends Fragment {
    private static final String ARG_NEWS = "news";

    public static NewsDetailFragment newInstance(News news) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_NEWS, news);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_news_detail, container, false);

        News news = (News) getArguments().getSerializable(ARG_NEWS);

        ImageView imageView = view.findViewById(R.id.imageViewNews);
        TextView title = view.findViewById(R.id.textViewTitle);
        TextView desc = view.findViewById(R.id.textViewDescription);
        RecyclerView recyclerRelated = view.findViewById(R.id.recyclerRelated);

        imageView.setImageResource(news.getImageResId());
        title.setText(news.getTitle());
        desc.setText(news.getDescription());

        recyclerRelated.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerRelated.setAdapter(new RelatedNewsAdapter(Arrays.asList(
                new News("Related 1", R.drawable.img1, "related desc"),
                new News("Related 2", R.drawable.img2, "related desc")
        )));

        return view;
    }
}
