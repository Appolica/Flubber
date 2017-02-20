package com.appolica.sample.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appolica.flubber.AnimationBody;
import com.appolica.sample.R;
import com.appolica.sample.databinding.MainPanelBinding;


public class MainPanelFragment extends Fragment implements OnAnimationAddedCallback {

    public static final String TAG = "MainPanelFragment";
    private MainPanelBinding binding;
    private RVAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_panel, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new RVAdapter();
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAnimationAdded(AnimationBody animationBody) {
        adapter.add(animationBody);
    }
}
