package com.appolica.sample.ui.editor.pager;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appolica.sample.R;
import com.appolica.sample.databinding.PagerFragmentBinding;


public abstract class BaseFragment<AdapterT extends RecyclerView.Adapter> extends Fragment {

    private PagerFragmentBinding binding;
    private AdapterT adapter;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_editor_page, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = getAdapterInstance();
        binding.recyclerView.setAdapter(adapter);
    }

    protected abstract AdapterT getAdapterInstance();

    public AdapterT getAdapter() {
        return adapter;
    }
}
