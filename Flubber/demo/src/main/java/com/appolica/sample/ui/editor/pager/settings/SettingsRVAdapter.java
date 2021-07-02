package com.appolica.sample.ui.editor.pager.settings;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.appolica.sample.R;
import com.appolica.sample.databinding.ListItemProgressBinding;
import com.appolica.sample.ui.animation.CustomAnimationBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SettingsRVAdapter extends RecyclerView.Adapter<SettingsRVAdapter.BindingHolder> {

    private List<SeekBarModel> models = new ArrayList<>();
    private Map<Observable, SeekBarModel> modelsMap = new LinkedHashMap<>();

    private Context context;

    private OnModelChangedCallback modelChangedCallback;

    public SettingsRVAdapter(Context context) {
        this.context = context;
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

    public void setAnimationBody(final CustomAnimationBody animationBody) {
        this.models = AnimationBodyModelUtil.generateFor(animationBody);
        for (SeekBarModel model : models) {

            modelsMap.put(model.getValue(), model);

            model.getValue().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(Observable observable, int i) {
                    if (modelChangedCallback != null) {
                        modelChangedCallback.onModelChanged(modelsMap.get(observable));
                    }
                }
            });
        }
        notifyDataSetChanged();
    }

    public void setModelChangedCallback(OnModelChangedCallback modelChangedCallback) {
        this.modelChangedCallback = modelChangedCallback;
    }

    public interface OnModelChangedCallback {
        void onModelChanged(SeekBarModel model);
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
}
