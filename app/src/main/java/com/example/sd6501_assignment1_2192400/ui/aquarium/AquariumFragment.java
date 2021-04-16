package com.example.sd6501_assignment1_2192400.ui.aquarium;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sd6501_assignment1_2192400.R;

public class AquariumFragment extends Fragment {

    private AquariumViewModel aquariumViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        aquariumViewModel =
                new ViewModelProvider(this).get(AquariumViewModel.class);
        View root = inflater.inflate(R.layout.fragment_aquarium, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        aquariumViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}