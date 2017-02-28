package com.appolica.sample.ui.editor.pager.settings;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.appolica.flubber.AnimationBody;
import com.appolica.sample.R;
import com.appolica.sample.databinding.ListItemProgressBinding;

import java.util.ArrayList;
import java.util.List;

public class SettingsRVAdapter extends RecyclerView.Adapter<SettingsRVAdapter.BindingHolder> {

    private List<SeekBarModel> models = new ArrayList<>();

    private Context context;

//    private AnimationBody animationBody;

    private OnAnimationBodyChangedCallback animationBodyChangedCallback;

    public SettingsRVAdapter(Context context) throws NoSuchFieldException {
        this.context = context;
    }

    public class BindingHolder extends RecyclerView.ViewHolder {

        private ListItemProgressBinding binding;

        private BindingHolder(ListItemProgressBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindTo(SeekBarModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final LayoutInflater inflater = LayoutInflater.from(context);
        ListItemProgressBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.list_item_progress, parent, false);

        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        holder.bindTo(models.get(position));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public void setAnimationBody(final AnimationBody animationBody) {
        this.models = AnimationBodyModelUtil.generateFor(animationBody);
        notifyDataSetChanged();
    }

    private void initModelFor(String name, AnimationBody animationBody, SeekBarModel model) {
        model.getName().set(name);
        float value = -1;
        float maxValue = -1;
        float minValue = -1;
        switch (name) {
            case "force":
                value = animationBody.getForce();
                minValue = 1f;
                maxValue = 5f;
                break;
            case "damping":
                value = animationBody.getDamping();
                minValue = 0f;
                maxValue = 1f;
                break;
            case "velocity":
                value = animationBody.getVelocity();
                minValue = 0f;
                maxValue = 1f;
                break;
            case "scale":
                // In the sample app, both scales should be equal
                value = animationBody.getScaleX();
                minValue = 0f;
                maxValue = 5f;
                break;
            case "delay":
                value = animationBody.getDelay();
                minValue = 0f;
                maxValue = 5f;
                break;
            case "duration":
                value = animationBody.getDuration();
                minValue = 0.1f;
                maxValue = 5f;
                break;
        }

        if (value != -1) {
            model.getValue().set(value);
            model.setMinValue(minValue);
            model.setMaxValue(maxValue);
        }
    }

    public void setAnimationBodyChangedCallback(OnAnimationBodyChangedCallback animationBodyChangedCallback) {
        this.animationBodyChangedCallback = animationBodyChangedCallback;
    }

    public interface OnAnimationBodyChangedCallback {
        void onPropertyChanged(String name, float value);
    }
}
