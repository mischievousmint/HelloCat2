package com.example.hellocat2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hellocat2.CatAdapter;
import com.example.hellocat2.MySharedPreferences;
import com.example.hellocat2.R;


public class ListFragment extends Fragment {

    RecyclerView rvCat;


    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewList = inflater.inflate(R.layout.fragment_list, container, false);

        rvCat = viewList.findViewById(R.id.rvCat);
        rvCat.setLayoutManager(new LinearLayoutManager(getActivity()));

        CatAdapter mAdapter = new CatAdapter(MySharedPreferences.loadCatList());

        rvCat.setAdapter(mAdapter);

        return viewList;
    }

}
