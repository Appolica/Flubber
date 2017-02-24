package com.appolica.sample.ui.editor.pager.settings;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ObservableFloat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.appolica.flubber.AnimationBody;
import com.appolica.sample.R;
import com.appolica.sample.databinding.ListItemProgressBinding;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.util.ArrayList;
import java.util.List;

public class SettingsRVAdapter extends RecyclerView.Adapter<SettingsRVAdapter.BindingHolder> {

    private final String[] supportedFieldNames = {"duration", "delay", "force", "velocity", "damping", "scale"};
//    private final List<String> supportedFieldNames;

    private List<ListItemModel> models = new ArrayList<>();

    private Context context;

//    private AnimationBody animationBody;

    private OnAnimationBodyChangedCallback animationBodyChangedCallback;

    public SettingsRVAdapter(Context context) {
        this.context = context;
        for (int nameIndex = 0; nameIndex < supportedFieldNames.length; nameIndex++) {
            final String name = supportedFieldNames[nameIndex];
            final ListItemModel model = new ListItemModel();
            model.getName().set(supportedFieldNames[nameIndex]);
            models.add(model);

            model.getValue().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(Observable observable, int i) {
                    if (animationBodyChangedCallback != null) {
                        animationBodyChangedCallback.onPropertyChanged(name, ((ObservableFloat) observable).get());
                    }
                }
            });
        }
    }

    public class BindingHolder extends RecyclerView.ViewHolder {

        private ListItemProgressBinding binding;

        private BindingHolder(ListItemProgressBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindTo(ListItemModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final LayoutInflater inflater = LayoutInflater.from(context);
        ListItemProgressBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.list_item_progress, parent, false);

        binding.discreteSeekBar.setNumericTransformer(new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {
                return 0;
            }

            @Override
            public String transformToString(int value) {
                return "v" + value;
            }

            @Override
            public boolean useStringTransform() {
                return true;
            }
        });

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
//        this.animationBody = animationBody;

        for (ListItemModel model : models) {
            initModelFor(model.getName().get(), animationBody, model);
        }

        notifyDataSetChanged();
    }

    private void initModelFor(String name, AnimationBody animationBody, ListItemModel model) {
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
