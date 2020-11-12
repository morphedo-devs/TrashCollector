package com.example.trashcollector.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trashcollector.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NonSmart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NonSmart extends Fragment {



    public NonSmart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_non_smart, container, false);
    }
}