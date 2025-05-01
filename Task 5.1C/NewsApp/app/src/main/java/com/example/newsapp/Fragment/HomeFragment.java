package com.example.newsapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Adapter.HorizontalNewsAdapter;
import com.example.newsapp.Model.News;
import com.example.newsapp.R;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerTopStories, recyclerNews;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home_fragment, container, false);

        recyclerTopStories = view.findViewById(R.id.recyclerTopStories);
        recyclerNews = view.findViewById(R.id.recyclerNews);

        List<News> newsList = Arrays.asList(
                new News("9NEWS", R.drawable.img1, "Some description here"),
                new News("7NEWS", R.drawable.img2, "Another news description")
        );

        HorizontalNewsAdapter adapter = new HorizontalNewsAdapter(newsList, getActivity(), selectedNews -> {
            NewsDetailFragment detailFragment = NewsDetailFragment.newInstance(selectedNews);
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, detailFragment)
                    .addToBackStack(null)
                    .commit();
        });

        recyclerTopStories.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerTopStories.setAdapter(adapter);

        recyclerNews.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerNews.setAdapter(adapter); // using same adapter for simplicity

        return view;
    }
}